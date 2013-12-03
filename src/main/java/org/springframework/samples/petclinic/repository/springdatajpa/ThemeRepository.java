package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Theme;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 2:03
 */
public interface ThemeRepository extends JpaRepository <Theme, Integer> {
}
