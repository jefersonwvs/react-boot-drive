package com.jefersonwvs.reactbootdrive.userprofile;

import com.jefersonwvs.reactbootdrive.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;
	private final FileStore fileStore;
	private final String s3Bucket;

	@Autowired
	public UserProfileService(UserProfileRepository userProfileRepository, FileStore fileStore, String s3Bucket) {
		this.userProfileRepository = userProfileRepository;
		this.fileStore = fileStore;
		this.s3Bucket = s3Bucket;
	}

	List<UserProfile> getUserProfiles() {
		return userProfileRepository.getUserProfiles();
	}

	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
		// 1. Check if image is not empty
		isFileEmpty(file);
		// 2. If file is an image
		isImage(file);
		// 3. The user exists in our database
		UserProfile user = getUserProfileOrThrow(userProfileId);
		// 4. Grab some metadata from file if any
		Map<String, String> metadata = extractMetadata(file);
		// 5. Store the image in s3 and update database (userProfileImageLing) with s3 image link
		storeImage(file, user, metadata);

		userProfileRepository.save(user);
	}

	public byte[] downloadUserProfileImage(UUID userProfileId) {
		UserProfile user = getUserProfileOrThrow(userProfileId);
		String path = String.format("%s/%s", s3Bucket, user.getId());

		return user.getImageLink()
				.map(imageLink -> fileStore.download(path, imageLink))
				.orElse(new byte[0]);
	}

	private static void isFileEmpty(MultipartFile file) {
		if (file.isEmpty()) {
			throw new IllegalStateException("Cannot upload empty file");
		}
	}

	private static void isImage(MultipartFile file) {
		if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
			throw new IllegalStateException("File must be a image");
		}
	}

	private UserProfile getUserProfileOrThrow(UUID userProfileId) {
		return userProfileRepository
				.getUserProfiles()
				.stream()
				.filter(obj -> obj.getId().equals(userProfileId))
				.findFirst()
				.orElseThrow(
						() -> new IllegalStateException(String.format("User profile (ID %s) not found", userProfileId))
				);
	}

	private static Map<String, String> extractMetadata(MultipartFile file) {
		Map<String, String> metadata = new HashMap<>();
		metadata.put("Content-Type", file.getContentType());
		metadata.put("Content-Length", file.getSize() + "");
		return metadata;
	}

	private void storeImage(MultipartFile file, UserProfile user, Map<String, String> metadata) {
		String path = String.format("%s/%s", s3Bucket, user.getId());

		String[] fields = file.getOriginalFilename().split("[.]");
		// fields[0]: file name
		// fields[1]: file extension

		String filename = String.format("%s-%s.%s", fields[0], UUID.randomUUID(), fields[1]);

		try {
			fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
			user.setImageLink(filename);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
}
