package com.bridgelabz.userservice.serviceimplementation;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.userservice.dto.UserInformationdto;
import com.bridgelabz.userservice.dto.UserLoginInformation;
import com.bridgelabz.userservice.dto.UserUpdatePassword;
import com.bridgelabz.userservice.entity.User;
import com.bridgelabz.userservice.exception.UserException;
import com.bridgelabz.userservice.repository.UserRepository;
//import com.bridgelabz.userservice.response.MailResponse;
import com.bridgelabz.userservice.response.MailingandResponseOperation;
import com.bridgelabz.userservice.service.UserServices;
import com.bridgelabz.userservice.utility.JWTOperations;



@Service
public class UserServiceImplementation implements UserServices {

	@Autowired
	UserRepository repository;




	@Autowired
	JWTOperations jwtop;

	@Autowired
	MailingandResponseOperation response;
	@Autowired
	private RestTemplate restTemplate;

	private BCryptPasswordEncoder encryption=new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	
	
	@Override
	@Transactional
	public User userRegistration(UserInformationdto informationdto) throws UserException {
		User user = new User();
		if (userRepository.getUser(informationdto.getEmail()).isPresent() == false) {

			BeanUtils.copyProperties(informationdto, user);
           
			String password = user.getPassword();
			String encrptPassword = encryption.encode(password);
			
			user.setCreatedDate(LocalDateTime.now());
		
			user.setPassword(encrptPassword);
			user.setVerified(false);
			repository.registrationSave(user);
	
			restTemplate.exchange("http://localhost:8000/user/regVerification/"+user.getEmail(),HttpMethod.GET,null, String.class).getBody();

//			mail.setEmail(user.getEmail());
//			mail.setMessage(mailResponse);
//			mail.setSubject("verification");
//			messageService.sendEmail(user.getEmail(),"verification",mailResponse);
			
		} else {
			throw new UserException("Email id already exists", HttpStatus.FORBIDDEN);
		}
		return user;
	}

	@Override
	@Transactional
	public User userLogin(UserLoginInformation information) throws UserException {

		User info = repository.loginValidate(information)
				.orElseThrow(() -> new UserException("Email not registered", HttpStatus.NOT_FOUND));

		if (encryption.matches(information.getPassword(), info.getPassword())) {

			return info;

		} else 
		{
			throw new UserException("Password not matched", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	@Transactional
	public boolean verify(String token) throws Exception 
	{
	
		Long id = (long) jwtop.parseJWT(token);
		repository.verify(id);
		return true;
	}

	@Override
	@Transactional
	public boolean updatePassword(UserUpdatePassword updatePassword, String token) throws UserException {
		Long id = (long) 0;
		boolean updatePasswordFlag = false;

		id = (Long) jwtop.parseJWT(token);
		User user;

		user = userRepository.getUserById(id)
				.orElseThrow(() -> new UserException("User not registered", HttpStatus.NOT_FOUND));

		if (encryption.matches(updatePassword.getOldPassword(), user.getPassword())) {
			String epassword = encryption.encode(updatePassword.getConfirmPassword());
			updatePassword.setConfirmPassword(epassword);
			updatePasswordFlag = repository.updatePassword(updatePassword, id);
		} else {
			throw new UserException("Old password is invalid", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}

		return updatePasswordFlag;
	}

	@Override
	@Transactional
	public boolean isUserExist(String email) throws UserException {
		boolean userExist = false;
		
			
			User user = repository.getUser(email)
					.orElseThrow(() -> new UserException("User not registered", HttpStatus.NOT_FOUND));
			if (user.isVerified() == true && user != null) {
				Random random = new Random();

				String password = String.valueOf(random.nextInt());
				String encodedPassword = encryption.encode(password);
				repository.updateRandomPassword(encodedPassword, user.getUserId());
				System.out.println("---------" + user.getUserId());
				
				userExist = true;
			}

		
		return userExist;

	}

	@Override
	@Transactional
	public User getUserById(String token) throws UserException {
		User user=new User();
		long id = (long) 0;
		

		id = jwtop.parseJWT(token);

		user = repository.getUserById(id)
				.orElseThrow(() -> new UserException("User not present", HttpStatus.NOT_FOUND));

		return user;

	}
}
