package com.bookbuffs.bookbuffs.controller;

import com.bookbuffs.bookbuffs.model.Book;
import com.bookbuffs.bookbuffs.model.Profile;
import com.bookbuffs.bookbuffs.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController {

    private  final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Page<Profile>> list(Pageable pageable) {
        //  log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(profileService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity saveProfile(@RequestBody Profile profile){
        profileService.save(profile);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateProfile(@RequestBody Profile profile){
        profileService.save(profile);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
