package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:40
 */
@Entity
public class Task extends NamedEntity {
    private String type;
    private String description;

}
