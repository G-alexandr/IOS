package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 23:19
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
