package edu.fra.uas.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.fra.uas.model.user.User;
import edu.fra.uas.userdto.UserRegistrationDto;
//interface is used to handle later changes on methods easier and faster.Dependency Injection and IOC can be realized .
public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

}
