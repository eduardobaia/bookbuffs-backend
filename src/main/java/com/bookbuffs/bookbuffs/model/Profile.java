package com.bookbuffs.bookbuffs.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long profileId;
    @OneToOne(fetch = LAZY)
    private User user;

}
