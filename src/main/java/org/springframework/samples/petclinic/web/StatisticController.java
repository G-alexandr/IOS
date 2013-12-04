package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ThemeProgress;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeProgressRepository;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 21:04
 */
@Controller
@RequestMapping("statistic")
public class StatisticController extends AbstractBaseController {

    @Autowired
    private ThemeProgressRepository themeProgressRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String currentUserStatistics(Map<String, Object> model){
        List<ThemeProgress> progressList = themeProgressRepository.findByUser(UserService.getCurrentUser());
        model.put("progressList", progressList);
        return "progress/userStatistic";
    }

}
