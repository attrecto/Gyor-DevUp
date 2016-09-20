package hu.gyuuu.hrmanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.gyuuu.hrmanager.dto.user.ProfileDataDto;
import hu.gyuuu.hrmanager.security.AuthenticationDetails;
import hu.gyuuu.hrmanager.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @RequestMapping("/me")
    public ProfileDataDto getProfileDataForSelf(Authentication authentication) {
        AuthenticationDetails authDetails = (AuthenticationDetails) authentication.getDetails();
        return userService.getProfileData(authDetails.getUserId());
    }

    @RequestMapping("/{userId}")
    public ProfileDataDto getProfileData(@PathVariable("userId") Long userId) {
        return userService.getProfileData(userId);
    }
}
