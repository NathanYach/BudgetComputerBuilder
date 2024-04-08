package org.webapp.Repositories;

import org.webapp.Models.GPU;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPURepository extends CrudRepository<GPU,Long> {
}