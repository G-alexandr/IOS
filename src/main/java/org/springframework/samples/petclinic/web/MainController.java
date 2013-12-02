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
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
//@SessionAttributes("visit")
public class MainController extends AbstractBaseController{

    private final ClinicService clinicService;


    @Autowired
    public MainController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping("/main/tasks")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("tasks", vets);
        return "/main/tasks";
    }
    @RequestMapping(value = "/main/themes", method = RequestMethod.GET)
    public String getThemesList() {
//        Pet pet = this.clinicService.findPetById(petId);
//        Visit visit = new Visit();
//        pet.addVisit(visit);
        //call tasklist service

//        model.put("visit", visit);
        return "main/themes";
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
