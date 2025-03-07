package in.DAA.PhotoPlaza.services.impl;

import in.DAA.PhotoPlaza.dto.ProfileDetailsDto;
import in.DAA.PhotoPlaza.entites.ProfileDetails;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.repositories.ProfileDetailsRepository;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileDetailsServiceImpl implements ProfileDetailsService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private ProfileDetailsRepository profileDetailsRepository;

    @Override
    public void CreateProfileDetails(ProfileDetailsDto profileDetailsDto, User user) {
        // Map the DTO to the entity
        ProfileDetails profileDetails = modelMapper.map(profileDetailsDto, ProfileDetails.class);

        // Set the image bytes if available
        if (profileDetailsDto.getImageBytes() != null) {
            profileDetails.setImage(profileDetailsDto.getImageBytes());
        }

        // Check if the user already has a profile
        Optional<ProfileDetails> existingProfile = profileDetailsRepository.findByUser(user);

        if (existingProfile.isPresent()) {
            // UPDATE EXISTING PROFILE
            ProfileDetails profile = existingProfile.get();
            profile.setBio(profileDetails.getBio());
            profile.setImage(profileDetails.getImage()); // Update image if needed
            profileDetailsRepository.save(profile);
        } else {
            // CREATE NEW PROFILE
//            profileDetails.setUser(user);
            profileDetailsRepository.save(profileDetails);
        }
    }
//    @Override
//    public void CreateProfileDetails(ProfileDetailsDto profileDetailsDto,User user) {
//        ProfileDetails profileDetails = modelMapper.map(profileDetailsDto,ProfileDetails.class);
//        profileDetails.setImage(profileDetailsDto.getImageBytes());
//
//        Optional<ProfileDetails> existingProfile = profileDetailsRepository.findByUser(user);
//
////        validate userId os not null
//        if(profileDetailsDto.getUserId() == null){
//            throw  new IllegalArgumentException(" User Id must not be null ");
//        }
//
////        Ensure a valid user is assigned
//         user = userRepository.findById(profileDetailsDto.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (existingProfile.isPresent()) {
//            // UPDATE EXISTING PROFILE
//            ProfileDetails profile = existingProfile.get();
//            profile.setBio(profileDetails.getBio());
//            profile.setImage(profileDetails.getImage()); // Update image logic as needed
//            profileDetailsRepository.save(profile);
//        } else {
//            // CREATE NEW PROFILE
//            profileDetails.setUser(user);
//            profileDetailsRepository.save(profileDetails);
//        }
////        profileDetails.setUser(user);
////        profileDetailsRepository.save(profileDetails);
//    }
    @Override
    public void createDefaultProfileDetails(User user) {
        Optional<ProfileDetails> profileDetailsOptional = profileDetailsRepository.findByUserId(user.getId());
        if (profileDetailsOptional.isEmpty()) {
            ProfileDetails profileDetails = new ProfileDetails();
            profileDetails.setUser(user);
            profileDetailsRepository.save(profileDetails);
        }
    }
    @Override
    public boolean hasProfile(Long userId) {
        return profileDetailsRepository.findByUserId(userId).isPresent();
    }

    @Override
    public ProfileDetailsDto getProfileDetailsById(Long userId) {
        Optional<ProfileDetails> profileDetailsOptional = profileDetailsRepository.findByUserId(userId);
        if (profileDetailsOptional.isPresent()) {
            ProfileDetails profileDetails = profileDetailsOptional.get();
            ProfileDetailsDto profileDetailsDto = modelMapper.map(profileDetails, ProfileDetailsDto.class);

            // Check if the image is not null and encode it to Base64
            if (profileDetails.getImage() != null) {
                profileDetailsDto.setImageBase64(Base64.getEncoder().encodeToString(profileDetails.getImage()));
            }

            return profileDetailsDto;
        } else {
            // Return a new ProfileDetailsDto if no profile is found
            return new ProfileDetailsDto();
        }
    }

//    @Override
//    public ProfileDetailsDto getProfileDetailsById(Long userId) {
//        ProfileDetails profileDetails = profileDetailsRepository.findByUserId(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for user ID: " + userId));
//        ProfileDetailsDto profileDetailsDto = modelMapper.map(profileDetails,ProfileDetailsDto.class);
//        if (profileDetails.getImage()!=null){
//            profileDetailsDto.setImageBase64(Base64.getEncoder().encodeToString(profileDetails.getImage()));
//        }
//
//        return profileDetailsDto;
//    }
}
