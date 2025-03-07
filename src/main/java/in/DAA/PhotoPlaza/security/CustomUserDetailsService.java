package in.DAA.PhotoPlaza.security;

import in.DAA.PhotoPlaza.entites.User;
import in.DAA.PhotoPlaza.entites.UserPrincipal;
import in.DAA.PhotoPlaza.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {
//        User user = userRepository.findByUserNameOrEmail(userNameOrEmail,userNameOrEmail)
//                .orElseThrow(()-> new UsernameNotFoundException(
//                        "User not found with username or email: " + userNameOrEmail));
        User user = userRepository.findByUserNameIgnoreCaseOrEmailIgnoreCase(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(user ==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }

}