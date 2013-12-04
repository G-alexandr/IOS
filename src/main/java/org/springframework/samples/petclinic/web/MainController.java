/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.ThemeContent;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeContentRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeRepository;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class MainController extends AbstractBaseController{


    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ThemeContentRepository themeContentRepository;

    @RequestMapping("/main/tasks")
    public String showVetList(Map<String, Object> model, HttpServletRequest request) {
//        if(isUserLogged())
//            return getLoginPage();
//        Vets vets = new Vets();
////        vets.getVetList().addAll(this.clinicService.findVets());
//        model.put("tasks", vets);

        return "/main/tasks";
    }
    @RequestMapping(value = "/main/themes", method = RequestMethod.GET)
    public String getThemesList(Map<String, Object> model) {
        model.put("themeList", themeRepository.findAll());
        return "main/themes";
    }

    @RequestMapping(value = "/main/themes/{id}", method = RequestMethod.GET)
    public String getThemeContents(Map<String, Object> model, @PathVariable Integer id) {
        model.put("theme", themeRepository.findOne(id));
        model.put("contents", themeContentRepository.findByThemeId(id));
        List<Integer> ids = new ArrayList<>();
        for(ThemeContent themeContent:UserService.getCurrentUser().getFinishedThemeContentList()){
            ids.add(themeContent.getId());
        }
        model.put("userContents", ids);
        return "content/themeContent";
    }

    @RequestMapping(value = "/main/statistic", method = RequestMethod.GET)
    public String getStatistic() {
//        Pet pet = this.clinicService.findPetById(petId);
//        Visit visit = new Visit();
//        pet.addVisit(visit);
        //call tasklist service

//        model.put("visit", visit);
        return "main/statistic";
    }


}
