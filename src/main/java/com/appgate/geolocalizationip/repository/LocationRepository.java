package com.appgate.geolocalizationip.repository;

import com.appgate.geolocalizationip.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location, String> {
}
