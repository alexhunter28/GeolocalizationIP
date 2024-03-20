package com.appgate.geolocalizationip.controller;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import com.appgate.geolocalizationip.entity.pojo.GeographicalInfo;
import com.appgate.geolocalizationip.service.FileIPReaderService;
import com.appgate.geolocalizationip.service.FindIPAddressService;
import com.appgate.geolocalizationip.service.IPAddressConversionService;
import com.appgate.geolocalizationip.service.UploadDataToDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GeolocalizationIPController {

   private final FileIPReaderService fileIPReaderService;
   private final UploadDataToDBService uploadDataToDBService;
   private final FindIPAddressService findIPAddressService;
    @Autowired
    public GeolocalizationIPController(FileIPReaderService fileIPReaderService, UploadDataToDBService uploadDataToDBService, IPAddressConversionService ipAddressConversionService, FindIPAddressService findIPAddressService) {
        this.fileIPReaderService = fileIPReaderService;
        this.uploadDataToDBService = uploadDataToDBService;
        this.findIPAddressService = findIPAddressService;
    }

    @PostMapping("/load/file")
    public ResponseEntity<List<FileBase>> loadFileToDB(){
        try {
            uploadDataToDBService.uploadData(fileIPReaderService.readFile());
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            return ResponseEntity.internalServerError().build();
        }

    }


    @GetMapping("/ip/{ipAddress}")
    public ResponseEntity<List<GeographicalInfo>> getGeographicalInfo(@PathVariable String ipAddress){
        List<GeographicalInfo> lstGeographicalInfo = findIPAddressService.findByIP(ipAddress);
        if(lstGeographicalInfo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lstGeographicalInfo);
    }


}
