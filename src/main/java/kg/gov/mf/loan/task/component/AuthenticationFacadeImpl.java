package kg.gov.mf.loan.task.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        else
        {
            return authentication;
        }
    }

    @Override
    public String getUser() {

        if(getAuthentication().isAuthenticated())
        {
            return ((UserDetails) getAuthentication().getPrincipal()).getUsername();
        }
        return "System";
    }

    @Override
    public String getIP() {

        return ((WebAuthenticationDetails)getAuthentication().getDetails()).getRemoteAddress();
    }
}
