package web.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.model.Permission;
import web.model.Roles;
import web.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SecurityUser implements UserDetails {
    private final String email;
    private final String password;
    private final List<SimpleGrantedAuthority> simpleGrantedAuthorityList;
    private final boolean isAdmin;

    public SecurityUser(String email, String password, List<SimpleGrantedAuthority> simpleGrantedAuthorityList, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.simpleGrantedAuthorityList = simpleGrantedAuthorityList;
        this.isAdmin = isAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAdmin;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAdmin;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAdmin;
    }

    @Override
    public boolean isEnabled() {
        return isAdmin;
    }

    public static UserDetails convertToUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().equals(Roles.ADMIN.name()),
                user.getRoles().equals(Roles.ADMIN.name()),
                user.getRoles().equals(Roles.ADMIN.name()),
                user.getRoles().equals(Roles.ADMIN.name()),
                user.getRoles().getAuthorities());
    }
}
