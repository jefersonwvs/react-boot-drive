package com.jefersonwvs.reactbootdrive.userprofile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "tbl_user_profiles")
public class UserProfile {

	@Id
	private UUID userProfileId;
	private String username;
	private String userProfileImageLink;

	public UserProfile() {
	}

	public UserProfile(UUID userProfileId, String username, String userProfileImageLink) {
		this.userProfileId = userProfileId;
		this.username = username;
		this.userProfileImageLink = userProfileImageLink;
	}

	public UUID getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(UUID userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Optional<String> getUserProfileImageLink() {
		return Optional.ofNullable(userProfileImageLink);
	}

	public void setUserProfileImageLink(String userProfileImageLink) {
		this.userProfileImageLink = userProfileImageLink;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserProfile that = (UserProfile) o;
		return Objects.equals(userProfileId, that.userProfileId)
				&& Objects.equals(username, that.username)
				&& Objects.equals(userProfileImageLink, that.userProfileImageLink);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userProfileId, username, userProfileImageLink);
	}
}
