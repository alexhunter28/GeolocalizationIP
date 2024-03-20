package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.IPAddress;
import com.appgate.geolocalizationip.entity.pojo.GeographicalInfo;
import com.appgate.geolocalizationip.repository.IPAddressRepository;
import com.appgate.geolocalizationip.service.FindIPAddressService;
import com.appgate.geolocalizationip.service.IPAddressConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindIPAddress implements FindIPAddressService {
    private final IPAddressConversionService ipAddressConversionService;
    private final IPAddressRepository ipAddressRepository;
    @Autowired
    public FindIPAddress(IPAddressConversionService ipAddressConversionService, IPAddressRepository ipAddressRepository) {
        this.ipAddressConversionService = ipAddressConversionService;
        this.ipAddressRepository = ipAddressRepository;
    }

    @Override
    public List<GeographicalInfo> findByIP(String ip) {
        long decimalIP = ipAddressConversionService.convertIPAddressToDecimal(ip);
        return convertIPAddressListToGeographicalInfoList(ipAddressRepository.findByIpFromOrIpTo(decimalIP,decimalIP));
    }

    private List<GeographicalInfo> convertIPAddressListToGeographicalInfoList(List<IPAddress> lstIpAddresses) {
        List<GeographicalInfo> lstGeographicalInfo = new ArrayList<>();
        for (IPAddress ipAddress : lstIpAddresses) {
            GeographicalInfo geographicalInfo = new GeographicalInfo(ipAddress.getLocation().getCountryCode(),
                    ipAddress.getLocation().getRegion(), ipAddress.getLocation().getCity(), ipAddress.getLocation().getTimeZone());
            lstGeographicalInfo.add(geographicalInfo);
        }
        return lstGeographicalInfo;
    }
}
