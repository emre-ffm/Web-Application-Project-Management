package edu.fra.uas.service.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.fra.uas.model.user.Role;
import edu.fra.uas.model.user.User;
import edu.fra.uas.repository.user.UserRepository;
import edu.fra.uas.userdto.UserRegistrationDto;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired

	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	
	//its used to get the findbyEmail method from the userRepository
	private UserServiceimpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}
	
//Create a new User and Encode the password and set the Role as ROLE_USER
	@Override
	public User save(UserRegistrationDto registrationDto) {

		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));

		return userRepository.save(user);
	}

	//Check if the User exist and login is the same as the entered value.Email is used as username here because this is a Unique variable
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	
	//Getting the Role for the User.
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}

}
