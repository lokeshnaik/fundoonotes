package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.UserInformationdto;
import com.bridgelabz.fundoonotes.dto.UserLoginInformation;
import com.bridgelabz.fundoonotes.dto.UserUpdatePassword;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.UserException;

public interface UserServices {

	User userRegistration(UserInformationdto informationdto) throws UserException;

	User userLogin(UserLoginInformation information) throws UserException;

	boolean verify(String token) throws Exception;

	boolean updatePassword(UserUpdatePassword password, String token) throws UserException;

	public boolean isUserExist(String email) throws UserException;

	User getUserById(String token) throws UserException;

}