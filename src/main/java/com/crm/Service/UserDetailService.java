package com.crm.Service;

import com.crm.Form.AuthForm;
import com.crm.Security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private JWTService jwtService;

    private final UserDetails userDetails = User.builder()
            .username("sucesso@passei.com.br")
            .password(new BCryptPasswordEncoder().encode("SucessoEMerito@123"))
            .authorities("ADMIN")
            .credentialsExpired(false)
            .build();

    @Override
    public UserDetails loadUserByUsername(String email) {
        if (!email.equalsIgnoreCase(userDetails.getUsername())) throw new IllegalArgumentException("Email desconhecido!");
        return userDetails;
    }

    public UserDetails authenticate(AuthForm userForm) throws IllegalArgumentException {
        boolean passwordOK = encoder.matches(userForm.getPassword(), userDetails.getPassword());
        if (passwordOK) {
            String token = jwtService.generateToken(userDetails);
            return userDetails;
        }
        throw new RuntimeException("User or password invalid!");
    }
}
