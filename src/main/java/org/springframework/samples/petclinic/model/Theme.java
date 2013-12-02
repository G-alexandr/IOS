package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:40
 */
@Entity
public class Theme extends NamedEntity{
    private String description;
}
