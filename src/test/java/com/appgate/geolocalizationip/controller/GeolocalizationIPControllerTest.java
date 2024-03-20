package com.appgate.geolocalizationip.controller;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import com.appgate.geolocalizationip.entity.pojo.GeographicalInfo;
import com.appgate.geolocalizationip.service.FileIPReaderService;
import com.appgate.geolocalizationip.service.FindIPAddressService;
import com.appgate.geolocalizationip.service.UploadDataToDBService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class GeolocalizationIPControllerTest {

    @Mock
    private FileIPReaderService fileIPReaderService;

    @Mock
    private UploadDataToDBService uploadDataToDBService;

    @Mock
    private FindIPAddressService findIPAddressService;

    @InjectMocks
    private GeolocalizationIPController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadFileToDB_Success() throws FileNotFoundException {
        doNothing().when(uploadDataToDBService).uploadData(any());
        when(fileIPReaderService.readFile()).thenReturn(new ArrayList<>());

        ResponseEntity<List<FileBase>> response = controller.loadFileToDB();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testLoadFileToDB_FileNotFound() throws FileNotFoundException {
        when(fileIPReaderService.readFile()).thenThrow(new FileNotFoundException());

        ResponseEntity<List<FileBase>> response = controller.loadFileToDB();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testGetGeographicalInfo_Success() {

        List<GeographicalInfo> geographicalInfoList = new ArrayList<>();
        geographicalInfoList.add(new GeographicalInfo("Country", "Region", "City", "Timezone"));
        when(findIPAddressService.findByIP(anyString())).thenReturn(geographicalInfoList);

        ResponseEntity<List<GeographicalInfo>> response = controller.getGeographicalInfo("127.0.0.1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetGeographicalInfo_NotFound() {
        when(findIPAddressService.findByIP(anyString())).thenReturn(new ArrayList<>());

        ResponseEntity<List<GeographicalInfo>> response = controller.getGeographicalInfo("127.0.0.1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}