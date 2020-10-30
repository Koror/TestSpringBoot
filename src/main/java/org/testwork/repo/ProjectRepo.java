package org.testwork.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testwork.domain.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
