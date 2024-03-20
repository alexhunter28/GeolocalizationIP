package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class FileIPReaderTest {


    @Test
    public void testReadFile() throws Exception {

        String testData = "value1,value2,value3,value4,value5,value6,value7,value8,value9\n";


        // Call the method under test
        FileIPReader fileIPReader = new FileIPReader();
        List<FileBase> result = fileIPReader.readFile();


        // Verify the result
        assertEquals(500000, result.size());
        assertEquals("0", result.get(0).getIpFrom());
        assertEquals("0", result.get(0).getIpTo());
        assertEquals("**", result.get(0).getCountryCode());
        assertEquals("RESERVED/PRIVATE", result.get(0).getCountry());
        assertEquals("RESERVED/PRIVATE", result.get(0).getRegion());
        assertEquals("RESERVED", result.get(0).getCity());
        assertEquals("0", result.get(0).getLatitude());
        assertEquals("0", result.get(0).getLongitude());
        assertEquals("?", result.get(0).getTimeZone());

    }



}