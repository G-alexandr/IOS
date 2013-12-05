package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.service.UserService;

/**
 * @autor a.garelik
 * Date: 02/12/13
 * Time: 21:18
 */

public abstract class AbstractBaseController implements Constanns{

    protected String getLoginPage(){
        return "redirect:/login";
    }

    public boolean isUserLogged(){
        return null != UserService.getCurrentUser();
    }
}
