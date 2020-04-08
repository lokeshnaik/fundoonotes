package com.bridgelabz.fundoonotes.serviceimplementation;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NotesInformationdto;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NotesServices;
import com.bridgelabz.fundoonotes.utility.JWTOperations;

@Service
public class NotesServicesImplementation implements NotesServices
{ 
	@Autowired
	JWTOperations jwtop;
   
	@Autowired
	UserRepository repository;
	
	@Autowired
	UserRepository userRepository;
	 
     @Autowired
      NotesRepository notesRepository;
     
     @Autowired
     NotesServicesImplementation notesServicesImplementation;
  
	@Override
   @Transactional
	public Notes addNotes(NotesInformationdto notesInformationdto, String token) throws UserException {
		Long userId =(long )0;
		  Notes notesInformation=new Notes();
		  
		  userId =(long )jwtop.parseJWT(token);
		  
		  User user =repository.getUserById(userId).orElseThrow(() ->new UserException("User Not Found",HttpStatus.NOT_FOUND));
		  
		 BeanUtils.copyProperties(notesInformationdto, notesInformation);
		 notesInformation.setDataAndTimeCreated(LocalDateTime.now());
		 
		 
		  userRepository.registrationSave(user);
		return notesInformation;
	}}
