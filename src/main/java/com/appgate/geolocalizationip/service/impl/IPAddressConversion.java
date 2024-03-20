package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.service.IPAddressConversionService;
import org.springframework.stereotype.Service;

@Service
public class IPAddressConversion implements IPAddressConversionService {
    @Override
    public long convertIPAddressToDecimal(String ipAddress) {
        String[] segments = ipAddress.split("\\.");
        if (segments.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format");
        }

        long decimalValue = 0;
        for (int i = 0; i < segments.length; i++) {
            int segmentValue = Integer.parseInt(segments[i]);
            if (segmentValue < 0 || segmentValue > 255) {
                throw new IllegalArgumentException("Invalid segment value in IP address");
            }
            decimalValue += segmentValue * Math.pow(256, 3 - i);
        }
        return decimalValue;
    }
}
