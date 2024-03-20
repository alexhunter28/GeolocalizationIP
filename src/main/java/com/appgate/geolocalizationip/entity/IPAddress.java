package com.appgate.geolocalizationip.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class IPAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ipFrom;
    private Long ipTo;
    @ManyToOne
    @JoinColumn(name = "countryCode")
    private Location location;
}
