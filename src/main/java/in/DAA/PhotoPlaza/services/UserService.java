package in.DAA.PhotoPlaza.services;

import in.DAA.PhotoPlaza.dto.UserDto;
import in.DAA.PhotoPlaza.entites.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    void registerUser(UserDto userDto);

//    User findByEmail(String email);

    void saveUser(UserDto userDto);

    Optional<User> findUserByEmail(String email);


    List<UserDto> findAllUsers();

    User getUserById(Long id);

    UserDto findUserById(Long id);
}
