package hu.gyuuu.hrmanager.service;

import hu.gyuuu.hrmanager.dto.user.ProfileDataDto;

public interface UserService {

    ProfileDataDto getProfileData(Long userId);

   
}
