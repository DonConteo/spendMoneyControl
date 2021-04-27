package com.tsoyDmitriy.spendMoneyControl.service;

import com.tsoyDmitriy.spendMoneyControl.Dto.UserDto;
import com.tsoyDmitriy.spendMoneyControl.model.Role;
import com.tsoyDmitriy.spendMoneyControl.model.User;
import com.tsoyDmitriy.spendMoneyControl.repository.RoleRepo;
import com.tsoyDmitriy.spendMoneyControl.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> findAll() {
        List<User> list = new ArrayList<>(userRepo.findAll());
        List<UserDto> userDtos = new ArrayList<>();
        for(User x : list) {
            long id = x.getId();
            String username = x.getUsername();
            Set<Role> roles = roleRepo.getRolesForUser(id);
            String role = "";
            for(Role s : roles) {
                role += s.getName() + " ";
            }
            userDtos.add(new UserDto(id, username, role));
        }
        return userDtos;
    }

    public void saveUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(new Role(1L, "USER")));
        userRepo.save(user);
    }

    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
