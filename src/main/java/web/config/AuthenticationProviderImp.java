package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class AuthenticationProviderImp implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Incorrect password or username!");
        }
//     //   Set<Role> roles = userService.findUserByEmail(email).getRoles();
//        Set<GrantedAuthority> authorities = new HashSet();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
        return null;
                //new UsernamePasswordAuthenticationToken(email, user.getPassword(), authorities);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

