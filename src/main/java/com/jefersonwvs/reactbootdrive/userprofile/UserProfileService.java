package com.jefersonwvs.reactbootdrive.userprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
