package mate.service;

import java.util.Optional;
import mate.exception.AuthenticationException;
import mate.lib.Inject;
import mate.lib.Service;
import mate.model.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password)
            throws AuthenticationException {
        logger.info("login method was called. Params: login = {}", login);
        Optional<Driver> optionalDriver = driverService.findByLogin(login);
        if (optionalDriver.isPresent()
                && optionalDriver.get().getPassword().equals(password)) {
            return optionalDriver.get();
        }
        throw new AuthenticationException("UserName or Password is incorrect");
    }
}
