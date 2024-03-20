package com.appgate.geolocalizationip.service;

import com.appgate.geolocalizationip.entity.IPAddress;
import com.appgate.geolocalizationip.entity.pojo.GeographicalInfo;

import java.util.List;

public interface FindIPAddressService {
    List<GeographicalInfo> findByIP(String ip);
}
