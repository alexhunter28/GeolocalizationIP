package com.appgate.geolocalizationip.service;

import com.appgate.geolocalizationip.entity.pojo.FileBase;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileIPReaderService {
    List<FileBase> readFile() throws FileNotFoundException;
}
