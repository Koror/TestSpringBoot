package org.testwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testwork.domain.Device;


public interface DeviceRepo extends JpaRepository<Device, Long> {

}
