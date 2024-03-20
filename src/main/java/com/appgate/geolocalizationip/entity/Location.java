package com.appgate.geolocalizationip.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    private String countryCode;
    private String country;
    private String region;
    private String city;
    private Double latitude;
    private Double longitude;
    private String timeZone;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<IPAddress> ipAddresses;
}
