package com.bridgelabz.userservice.service;

import com.bridgelabz.userservice.dto.UserInformationdto;
import com.bridgelabz.userservice.dto.UserLoginInformation;
import com.bridgelabz.userservice.dto.UserUpdatePassword;
import com.bridgelabz.userservice.entity.User;
import com.bridgelabz.userservice.exception.UserException;

public interface UserServices {

	User userRegistration(UserInformationdto informationdto) throws UserException;

	User userLogin(UserLoginInformation information) throws UserException;

	boolean verify(String token) throws Exception;

	boolean updatePassword(UserUpdatePassword password, String token) throws UserException;

	public boolean isUserExist(String email) throws UserException;

	User getUserById(String token) throws UserException;

}
