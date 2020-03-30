package com.bridgelabz.fundoonotes.repository;

import java.util.Optional;

import com.bridgelabz.fundoonotes.dto.UserLoginInformation;
import com.bridgelabz.fundoonotes.dto.UserUpdatePassword;
import com.bridgelabz.fundoonotes.entity.User;

public interface UserRepository {
	User registrationSave(User information);
	Optional<User> loginValidate(UserLoginInformation information);
	boolean verify(Long id);

	public boolean updatePassword(UserUpdatePassword updatePassword, Long id);
	Optional<User> getUser(String email); 
	
	boolean updateRandomPassword(String password,long l);
	Optional<User> getUserById(long id);
	

}
