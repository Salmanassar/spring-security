package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import web.model.User;
import web.service.UserService;


@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

   private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userService.findUserByEmail(email);
        return SecurityUser.convertToUser(user);
    }
}
