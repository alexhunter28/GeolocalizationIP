package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import com.appgate.geolocalizationip.repository.IPAddressRepository;
import com.appgate.geolocalizationip.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;



class UploadDataToDBTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private IPAddressRepository ipAddressRepository;

    @InjectMocks
    private UploadDataToDB uploadDataToDB;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadData() {
        // Given
        List<FileBase> lstFileBases = new ArrayList<>();
        lstFileBases.add(new FileBase("1","2","3","4", "5", "6","7","8", "9"));
        lstFileBases.add(new FileBase("1","2","3","4", "5", "6","7","8", "9"));
        lstFileBases.add(new FileBase("1","2","3","4", "5", "6","7","8", "9"));

        // When
        uploadDataToDB.uploadData(lstFileBases);

        // Then
        verify(locationRepository, times(1)).saveAllAndFlush(anyList());
        verify(ipAddressRepository, times(1)).saveAllAndFlush(anyList());
    }

}