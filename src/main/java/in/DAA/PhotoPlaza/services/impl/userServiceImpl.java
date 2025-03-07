package in.DAA.PhotoPlaza.services.impl;

import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.Role;
import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.repositories.RoleRepository;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import in.DAA.PhotoPlaza.services.ProfileDetailsService;
import in.DAA.PhotoPlaza.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class userServiceImpl implements UserService {

    private  UserRepository userRepository;
    private  RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;
    private  ModelMapper modelMapper;
    private ProfileDetailsService profileDetailsService;

    public static String generateUserName(UserDto userDto){
        String firstName = userDto.getFirstName();
        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        return firstName.toLowerCase()+randomNumber;
    }
    @Override
    public void saveUser(UserDto userDto) {
        User user;
        user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setContact(userDto.getContact());
        user.setEmail(userDto.getEmail());
        user.setUserName(generateUserName((userDto)));
        LocalDate currentDate = LocalDate.now();
        user.setRegistrationDate(currentDate);
//        Encrypted password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        user.setPassword(userDto.getPassword());
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("User role not found"));
        if(role == null){
            role = checkRoleExist();
        }
//        set roles with typecasting
//        user.setRoles((java.util.Set<Role>) Arrays.asList(role));

        // Correct way to set roles without typecasting
        user.setRoles(Collections.singleton(role)); // Using singleton set
        // OR
        // user.setRoles(new HashSet<>(Arrays.asList(role))); // Using HashSet
        userRepository.save(user);

//        // Create a default profile for the user
//        profileDetailsService.createDefaultProfileDetails(user);
    }
    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("USER");
        return roleRepository.save(role);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setContact(user.getContact());
        userDto.setEmail(user.getEmail());
//        String roleName = user.getRoles().isEmpty() ? "" : user.getRoles().set[0].getName();
        String roleName = user.getRoles().isEmpty() ? "" : user.getRoles().iterator().next().getName();
        userDto.setRole(roleName);
        return userDto;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            return userDto;
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
