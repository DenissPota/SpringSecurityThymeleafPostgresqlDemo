package ee.moontego.crud.thymeleaf.service;

import ee.moontego.crud.thymeleaf.entity.auth.authority.Authority;
import ee.moontego.crud.thymeleaf.entity.auth.authority.AuthorityRepository;
import ee.moontego.crud.thymeleaf.entity.auth.user.User;
import ee.moontego.crud.thymeleaf.entity.auth.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.get();
    }

}
