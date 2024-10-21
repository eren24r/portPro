package org.web.implDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.entity.Person;
import org.web.entity.Project;

import java.util.ArrayList;

public interface ProjectImpl extends JpaRepository<Project, Integer> {
}
