package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @autor a.garelik
 * Date: 04/12/13
 * Time: 19:51
 */
@Entity
public class TaskContent extends BaseEntity {

    private String content;
    private int score;
    @ManyToOne
    private Task task;

    public TaskContent() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
