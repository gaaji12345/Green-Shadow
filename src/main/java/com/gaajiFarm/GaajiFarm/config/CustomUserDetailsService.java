package com.gaajiFarm.GaajiFarm.config;/*  gaajiCode
    99
    09/09/2024
    */



import com.gaajiFarm.GaajiFarm.util.enitity.User;
import com.gaajiFarm.GaajiFarm.util.exeptions.NotFoundException;
import com.gaajiFarm.GaajiFarm.util.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username)
                .orElseThrow(()-> new NotFoundException("User/ Email Not found"+username.toUpperCase()));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
