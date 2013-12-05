package org.springframework.samples.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Theme;
import org.springframework.samples.petclinic.model.ThemeContent;
import org.springframework.samples.petclinic.model.ThemeProgress;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeContentRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeProgressRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 22:49
 */
@Service
@Transactional
public class ContentService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeProgressRepository themeProgressRepository;

    @Autowired
    private ThemeContentRepository themeContentRepository;

    public void handleFinishReading(Integer id){
        User user = UserService.getCurrentUser();
        ThemeContent themeContent = themeContentRepository.findOne(id);
        List<ThemeContent> contentList = user.getFinishedThemeContentList();

        contentList.add(themeContent);
        Theme theme = themeContent.getTheme();

        ThemeProgress themeProgress = themeProgressRepository.findByUserAndTheme(user, theme);

        if(themeProgress == null){
            themeProgress = new ThemeProgress();
            themeProgress.setTheme(theme);
            themeProgress.setUser(user);
        }

        int contentScore = themeContent.getScore();

        themeProgress.setScore(themeProgress.getScore() + contentScore);
        user.setScore(user.getScore() + contentScore);

        if(user.getFinishedThemeContentList().containsAll(theme.getThemeContents())){
            themeProgress.setCompleted(true);
        }

        themeProgressRepository.save(themeProgress);
        userRepository.save(user);
    }
}
