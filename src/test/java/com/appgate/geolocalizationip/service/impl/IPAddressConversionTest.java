package com.appgate.geolocalizationip.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPAddressConversionTest {

    private IPAddressConversion ipAddressConversion;

    @BeforeEach
    public void setUp() {
        ipAddressConversion = new IPAddressConversion();
    }

    @Test
    public void testConvertIPAddressToDecimal_ValidIPAddress() {
        String ipAddress = "192.168.1.1";
        long expected = 3232235777L;
        long result = ipAddressConversion.convertIPAddressToDecimal(ipAddress);
        assertEquals(expected, result);
    }

    @Test
    public void testConvertIPAddressToDecimal_InvalidFormat() {
        String ipAddress = "192.168.1";
        assertThrows(IllegalArgumentException.class, () -> {
            ipAddressConversion.convertIPAddressToDecimal(ipAddress);
        });
    }

    @Test
    public void testConvertIPAddressToDecimal_InvalidSegmentValue() {
        String ipAddress = "192.168.1.256";
        assertThrows(IllegalArgumentException.class, () -> {
            ipAddressConversion.convertIPAddressToDecimal(ipAddress);
        });
    }

}