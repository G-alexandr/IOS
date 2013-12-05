package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

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
    private Boolean lock;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    private List<TaskContent> taskContents;

    public List<TaskContent> getTaskContents() {
        return taskContents;
    }

    public void setTaskContents(List<TaskContent> taskContents) {
        this.taskContents = taskContents;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }
}
