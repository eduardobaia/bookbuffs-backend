package com.bookbuffs.bookbuffs.repository;

import com.bookbuffs.bookbuffs.model.Profile;
import com.bookbuffs.bookbuffs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Page<Profile> findByUser(User user, Pageable pageable);

}
