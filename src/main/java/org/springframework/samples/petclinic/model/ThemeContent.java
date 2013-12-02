package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:40
 */
@Entity
public class ThemeContent extends BaseEntity {
    @ManyToOne
    private Theme theme;

    private String contentBody;

    private int score;
}
