package org.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.test.domain.Device;
import org.test.domain.Project;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query("FROM Device a where a.project.id = ?1")
    List<Device> getDevicesByProjectId(Long projectId);
}
