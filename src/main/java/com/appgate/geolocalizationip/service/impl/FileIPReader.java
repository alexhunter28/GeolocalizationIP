package com.appgate.geolocalizationip.service.impl;

import com.appgate.geolocalizationip.entity.pojo.FileBase;
import com.appgate.geolocalizationip.service.FileIPReaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileIPReader implements FileIPReaderService {

    @Override
    public List<FileBase> readFile() {

        List<FileBase> lstFileBase = new ArrayList<>();

        try (InputStream inputStream = FileIPReader.class.getClassLoader().getResourceAsStream("ipgeo.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while((line = br.readLine()) != null){

                line = line.replaceAll("\"", "");
                String[] fields = line.split(",");
                FileBase filebase = new FileBase(fields[0], fields[1], fields[2], fields[3],
                        fields[4], fields[5], fields[6], fields[7], fields[8]);

                lstFileBase.add(filebase);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return lstFileBase;
    }
}
