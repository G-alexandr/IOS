package org.springframework.samples.petclinic.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:36
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class User extends NamedEntity {

    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ThemeContent> finishedThemeContentList = new ArrayList<>();

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Task> finishedTasks = new ArrayList<>();

    private int score;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ThemeContent> getFinishedThemeContentList() {
        return finishedThemeContentList;
    }

    public void setFinishedThemeContentList(List<ThemeContent> finishedThemeContentList) {
        this.finishedThemeContentList = finishedThemeContentList;
    }

    public List<Task> getFinishedTasks() {
        return finishedTasks;
    }

    public void setFinishedTasks(List<Task> finishedTasks) {
        this.finishedTasks = finishedTasks;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
