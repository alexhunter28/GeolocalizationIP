package com.appgate.geolocalizationip.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeographicalInfo {
    private String countryCode;
    private String region;
    private String city;
    private String timeZone;
}
