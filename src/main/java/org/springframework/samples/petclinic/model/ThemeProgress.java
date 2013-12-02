package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:39
 */
@Entity
public class ThemeProgress extends BaseEntity {

    @ManyToOne
    private User user;
    @ManyToOne
    private Theme theme;

    private int score;
    private boolean completed;
}
