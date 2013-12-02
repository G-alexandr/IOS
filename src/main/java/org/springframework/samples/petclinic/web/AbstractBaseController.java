package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.User;

/**
 * @autor a.garelik
 * Date: 02/12/13
 * Time: 21:18
 */

public abstract class AbstractBaseController implements Constanns{

   private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    protected boolean isUserLogged(){
        if (currentUser != null)
            return true;
        return false;
    }

    protected String getLoginPage(){
        return "redirect:/login";
    }


}
