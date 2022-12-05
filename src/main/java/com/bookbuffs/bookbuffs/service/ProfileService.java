package com.bookbuffs.bookbuffs.service;

import com.bookbuffs.bookbuffs.model.Book;
import com.bookbuffs.bookbuffs.model.Profile;
import com.bookbuffs.bookbuffs.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private final AuthService authService;
    private final ProfileRepository profileRepository;

    public Page<Profile> listAll(Pageable pageable) {
        return profileRepository.findByUser(authService.getCurrentUser(), pageable);
    }

    public void save(Profile profile){
        profile.setUser(authService.getCurrentUser());
        profileRepository.save(profile);
    }

    public void replace(Profile profile){
        profileRepository.save(profile);
    }
}
