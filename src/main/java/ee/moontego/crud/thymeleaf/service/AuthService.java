package ee.moontego.crud.thymeleaf.service;

import ee.moontego.crud.thymeleaf.entity.auth.authority.Authority;
import ee.moontego.crud.thymeleaf.entity.auth.user.User;

import java.util.List;

public interface AuthService {

    User getUserByUsername(String username);
}
