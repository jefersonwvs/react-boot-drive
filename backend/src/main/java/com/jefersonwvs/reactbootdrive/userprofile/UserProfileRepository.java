package com.jefersonwvs.reactbootdrive.userprofile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

	@Query(value = "SELECT * FROM tbl_user_profiles;", nativeQuery = true)
	List<UserProfile> getUserProfiles();

}
