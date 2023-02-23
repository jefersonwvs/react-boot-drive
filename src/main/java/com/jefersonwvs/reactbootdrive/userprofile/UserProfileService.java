package com.jefersonwvs.reactbootdrive.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {

	private final UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileService(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	List<UserProfile> getUserProfiles() {
		return userProfileRepository.getUserProfiles();
	}

	public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
		// 1. Check if image is not empty
		// 2. If file is an image
		// 3. The user exists in our database
		// 4. Grab some metadata from file if any
		// 5. Store the image in s3 and update database (userProfileImageLing) with s3 image link
	}
}
