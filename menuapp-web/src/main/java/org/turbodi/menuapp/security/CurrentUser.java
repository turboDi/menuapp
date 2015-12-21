package org.turbodi.menuapp.security;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.turbodi.menuapp.data.model.User;

/**
 * @author Dmitriy Borisov
 * @created 12/19/2015
 */
public class CurrentUser  extends org.springframework.security.core.userdetails.User {

    @Getter
    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().toString()));
        this.user = user;
    }

}
