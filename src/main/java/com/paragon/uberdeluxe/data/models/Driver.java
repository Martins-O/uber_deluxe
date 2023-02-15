package com.paragon.uberdeluxe.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phoneNumber;
    @OneToOne
    private Address address;
    @Enumerated
    private Gender gender;
    private String licenseID;
    private int age;
    @OneToOne
    private BankInformation accountDetails;
    @OneToOne
    private AppUser userDetails;

}
