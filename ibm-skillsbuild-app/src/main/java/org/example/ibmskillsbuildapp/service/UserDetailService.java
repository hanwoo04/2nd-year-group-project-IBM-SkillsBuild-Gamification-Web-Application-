package org.example.ibmskillsbuildapp.service;

import java.util.ArrayList;
import java.util.List;
import org.example.ibmskillsbuildapp.model.User;
import org.example.ibmskillsbuildapp.model.UserRoles;
import org.example.ibmskillsbuildapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        List<GrantedAuthority> loginAuthorities = new ArrayList<>();
        for (UserRoles userRoles : user.getUserRoles()) {
            loginAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoles.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
            user.getPassword(), true, true, true, true, loginAuthorities);
    }
}
