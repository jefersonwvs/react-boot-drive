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
	private UUID id;
	private String username;
	private String imageLink;

	public UserProfile() {
	}

	public UserProfile(UUID id, String username, String imageLink) {
		this.id = id;
		this.username = username;
		this.imageLink = imageLink;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Optional<String> getImageLink() {
		return Optional.ofNullable(imageLink);
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserProfile that = (UserProfile) o;
		return Objects.equals(id, that.id)
				&& Objects.equals(username, that.username)
				&& Objects.equals(imageLink, that.imageLink);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, imageLink);
	}
}
