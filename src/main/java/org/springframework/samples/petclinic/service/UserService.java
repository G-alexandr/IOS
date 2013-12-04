package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.web.Constanns;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 22:53
 */

public class UserService {
    public static User getCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Object user = attr.getRequest().getSession(true).getAttribute(Constanns.UNAME);
        return (User) user;
    }
}
