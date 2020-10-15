package org.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.domain.Device;
import org.test.domain.Event;

import java.util.List;

public interface DeviceRepo extends JpaRepository<Device, Long> {
    @Query("FROM Event a where a.device.id = ?1")
    List<Event> getEventsByDeviceId(Long deviceId);
}
