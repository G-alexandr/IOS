package org.springframework.samples.petclinic.model;

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

    @OneToMany(fetch = FetchType.EAGER)
    private List<ThemeContent> finishedThemeContentList = new ArrayList<>();

    public List<Task> getFinishedTaskList() {
        return finishedTaskList;
    }

    public void setFinishedTaskList(List<Task> finishedTaskList) {
        this.finishedTaskList = finishedTaskList;
    }

    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> finishedTaskList = new ArrayList<>();

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

}
