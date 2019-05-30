package webproject.api.controllers;

import webproject.api.entities.User;
import webproject.api.models.UserRegRequest;
import webproject.api.models.UserRegResponse;
import webproject.api.repositories.UserRepository;
import webproject.api.repositories.impl.UserRepositoryImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new UserRepositoryImpl();
    }

    @PostMapping("/api/user/register")
    public UserRegResponse register(@RequestBody UserRegRequest regRequest) throws Exception {

        User newUser = new User();
        newUser.setEmail(regRequest.getEmail());
        newUser.setPhone(regRequest.getPhone());
        newUser.setPassword(regRequest.getPassword());
        newUser.setFirstName(regRequest.getFirstName());
        newUser.setSurname(regRequest.getSurname());
        newUser.setActive(true);
        User insertedUser = this.userRepository.insert(newUser);

        UserRegResponse regResponse = new UserRegResponse();
        regResponse.setUserId(insertedUser.getId());

        return regResponse;
    }
}