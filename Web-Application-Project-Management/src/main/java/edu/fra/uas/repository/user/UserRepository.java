package edu.fra.uas.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import edu.fra.uas.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	//extends JpaRepository to use their function Methods
	
	//Find by Email to identify the User by Login
	User findByEmail(String email);
	
	
}
