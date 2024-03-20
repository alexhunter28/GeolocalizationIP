package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import com.appgate.geolocalizationip.entity.IPAddress;
import com.appgate.geolocalizationip.entity.Location;
import com.appgate.geolocalizationip.repository.IPAddressRepository;
import com.appgate.geolocalizationip.repository.LocationRepository;
import com.appgate.geolocalizationip.service.UploadDataToDBService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UploadDataToDB implements UploadDataToDBService {

    private final LocationRepository locationRepository;
    private final IPAddressRepository ipAddressRepository;

    @Autowired
    public UploadDataToDB(LocationRepository locationRepository, IPAddressRepository ipAddressRepository) {
        this.locationRepository = locationRepository;
        this.ipAddressRepository = ipAddressRepository;
    }

    @Transactional
    @Override
    public void uploadData(List<FileBase> lstFileBases) {
        List<Location> lstLocations = new ArrayList<>();
        List<IPAddress> lstIpAddresses = new ArrayList<>();

        for (FileBase fileBase : lstFileBases) {
            Location location = convertDataToLocation(fileBase);
            lstLocations.add(location);

            IPAddress ipAddress = convertDataToIPAddress(fileBase, location);
            lstIpAddresses.add(ipAddress);
        }
        locationRepository.saveAllAndFlush(lstLocations);
        ipAddressRepository.saveAllAndFlush(lstIpAddresses);
    }

    private Location convertDataToLocation(FileBase fileBase) {
        Location location = new Location();
        location.setCountryCode(fileBase.getCountryCode());
        location.setCountry(fileBase.getCountry());
        location.setRegion(fileBase.getRegion());
        location.setCity(fileBase.getCity());
        location.setLatitude(Double.parseDouble(fileBase.getLatitude()));
        location.setLongitude(Double.parseDouble(fileBase.getLongitude()));
        location.setTimeZone(fileBase.getTimeZone());
        return location;
    }

    private IPAddress convertDataToIPAddress(FileBase fileBase, Location location) {
        IPAddress ipAddress = new IPAddress();
        ipAddress.setIpFrom(Long.parseLong(fileBase.getIpFrom()));
        ipAddress.setIpTo(Long.parseLong(fileBase.getIpTo()));
        ipAddress.setLocation(location);
        return ipAddress;
    }
}
