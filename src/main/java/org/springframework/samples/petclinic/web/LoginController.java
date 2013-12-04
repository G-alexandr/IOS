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
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.springdatajpa.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController extends AbstractBaseController{

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String checkLogin(ModelMap model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(USERNAME)) {
                    User user = userRepository.findByName(cookie.getName());
                    if(user != null){
                        model.put(UNAME, user);
                         //put in session
                        return "welcome";
                    }
                }
            }
        model.put(UNAME, new User());
        return "/login/login";
    }

    @RequestMapping("/login/createUser")
    public String createUser(@ModelAttribute(UNAME) User user, HttpServletRequest request) {

        User baseUser = userRepository.findByName(user.getName());
        if(baseUser != null){
            user.setId(baseUser.getId());
            user.setFinishedThemeContentList(baseUser.getFinishedThemeContentList());
        }
        user = userRepository.saveAndFlush(user);
        request.getSession().setAttribute(UNAME, user);

//        setCurrentUser(user);
        return "/welcome";
    }


}
