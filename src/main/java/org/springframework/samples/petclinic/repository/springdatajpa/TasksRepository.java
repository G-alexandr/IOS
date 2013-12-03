package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Task;

/**
 * @autor a.garelik
 * Date: 04/12/13
 * Time: 00:14
 */

public interface TasksRepository extends JpaRepository<Task, Integer> {

//    Collection<TasksRepository> findAll() throws DataAccessException;

}
