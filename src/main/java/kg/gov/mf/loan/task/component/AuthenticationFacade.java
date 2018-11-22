package kg.gov.mf.loan.task.component;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();
    String getUser();
    String getIP();
}
