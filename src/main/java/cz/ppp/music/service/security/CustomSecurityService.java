package cz.ppp.music.service.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class CustomSecurityService implements SecurityService{
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public CustomSecurityService(AuthenticationManager authenticationManager, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String currentUserUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetails instanceof UserDetails))
            return null;
        return ((UserDetails)userDetails).getUsername();
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if(token.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(token);
    }

}
