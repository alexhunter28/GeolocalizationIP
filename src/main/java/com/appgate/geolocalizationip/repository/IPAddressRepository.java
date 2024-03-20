package com.appgate.geolocalizationip.repository;

import com.appgate.geolocalizationip.entity.IPAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPAddressRepository extends JpaRepository<IPAddress, Long> {
    List<IPAddress> findByIpFromOrIpTo(Long ipFrom, Long ipTo);
}
