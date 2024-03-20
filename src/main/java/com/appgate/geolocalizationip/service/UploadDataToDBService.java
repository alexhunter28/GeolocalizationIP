package com.appgate.geolocalizationip.service;

import com.appgate.geolocalizationip.entity.pojo.FileBase;

import java.util.List;

public interface UploadDataToDBService {
    void uploadData(List<FileBase> lstFileBases);
}
