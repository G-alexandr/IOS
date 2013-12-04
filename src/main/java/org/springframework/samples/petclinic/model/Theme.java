package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 01.12.13
 * Time: 21:40
 */
@Entity
public class Theme extends NamedEntity{
    private String description;

    @OneToMany(mappedBy = "theme")
    private List<ThemeContent> themeContents = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ThemeContent> getThemeContents() {
        return themeContents;
    }

    public void setThemeContents(List<ThemeContent> themeContents) {
        this.themeContents = themeContents;
    }
}
