package org.webapp.Repositories;

import org.webapp.Models.CPU;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPURepository extends CrudRepository<CPU, Long> {
}