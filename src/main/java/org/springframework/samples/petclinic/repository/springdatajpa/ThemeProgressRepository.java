package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Theme;
import org.springframework.samples.petclinic.model.ThemeProgress;
import org.springframework.samples.petclinic.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 21:10
 */
public interface ThemeProgressRepository extends JpaRepository<ThemeProgress, Integer> {
    List<ThemeProgress> findByUserId(Integer id);

    List<ThemeProgress> findByUser (User user);

    ThemeProgress findByUserAndTheme(User user, Theme theme);
}
