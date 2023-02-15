package com.paragon.uberdeluxe.data.models;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String phonenumber;
    @Enumerated(value = STRING)
    private Gender gender;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private AppUser UserDetails;

}
