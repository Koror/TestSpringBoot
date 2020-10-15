package org.testwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.testwork.domain.Device;
import org.testwork.domain.Project;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query("FROM Device a where a.project.id = ?1")
    List<Device> getDevicesByProjectId(Long projectId);
}
