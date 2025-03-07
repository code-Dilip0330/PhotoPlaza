package in.DAA.PhotoPlaza.services;

import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.entites.User;

public interface ProfileDetailsService {
    void CreateProfileDetails(ProfileDetailsDto profileDetailsDto, User user);

    void createDefaultProfileDetails(User user);

    ProfileDetailsDto getProfileDetailsById(Long userId);

    boolean hasProfile(Long userId);
}
