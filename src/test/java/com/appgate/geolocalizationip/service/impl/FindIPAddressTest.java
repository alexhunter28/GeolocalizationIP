package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.IPAddress;
import com.appgate.geolocalizationip.entity.Location;
import com.appgate.geolocalizationip.entity.pojo.GeographicalInfo;
import com.appgate.geolocalizationip.repository.IPAddressRepository;
import com.appgate.geolocalizationip.service.IPAddressConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindIPAddressTest {

    @Mock
    private IPAddressConversionService ipAddressConversionService;

    @Mock
    private IPAddressRepository ipAddressRepository;

    @InjectMocks
    private FindIPAddress findIPAddress;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByIP() {
        String ip = "192.168.1.1";
        long decimalIP = 3232235777L; // Decimal representation of 192.168.1.1
        List<IPAddress> lstIPAddress = new ArrayList<>();
        IPAddress ipAddress = new IPAddress();
        Location location = new Location("US", "United States", "California", "San Francisco", 37.7749, -122.4194, "PST",lstIPAddress);
        ipAddress.setLocation(location);
        lstIPAddress.add(ipAddress);

        // Stubbing
        when(ipAddressConversionService.convertIPAddressToDecimal(ip)).thenReturn(decimalIP);
        when(ipAddressRepository.findByIpFromOrIpTo(decimalIP, decimalIP)).thenReturn(lstIPAddress);

        // Call the method under test
        List<GeographicalInfo> result = findIPAddress.findByIP(ip);

        // Verify the result
        assertEquals(1, result.size());
        assertEquals("US", result.get(0).getCountryCode());
        assertEquals("California", result.get(0).getRegion());
        assertEquals("San Francisco", result.get(0).getCity());
        assertEquals("PST", result.get(0).getTimeZone());

        // Verify interactions
        verify(ipAddressConversionService, times(1)).convertIPAddressToDecimal(ip);
        verify(ipAddressRepository, times(1)).findByIpFromOrIpTo(decimalIP, decimalIP);
    }


}