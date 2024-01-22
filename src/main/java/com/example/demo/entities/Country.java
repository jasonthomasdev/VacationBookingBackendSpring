package com.example.demo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "countries")
@Data
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country_name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update;

    @OneToMany(mappedBy = "country")
    private Set<Division> divisions;
}
