package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.ThemeContent;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 2:04
 */
public interface ThemeContentRepository extends JpaRepository <ThemeContent, Integer> {
    List<ThemeContent> findByThemeId(Integer id);
}
