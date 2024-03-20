package com.appgate.geolocalizationip.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class FileBase {
    private String ipFrom;
    private String ipTo;
    private String countryCode;
    private String country;
    private String region;
    private String city;
    private String latitude;
    private String longitude;
    private String timeZone;
}
