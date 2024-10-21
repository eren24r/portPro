package org.web.implDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.entity.Person;

public interface PersonImpl extends JpaRepository<Person, Integer> {
}
