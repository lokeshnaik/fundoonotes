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
import com.bridgelabz.fundoonotes.exception.NotesException;
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
		 
		 user.getNotes().add(notesInformation);
		  userRepository.registrationSave(user);
		  System.out.println("hey Lokesh");
		return notesInformation;
	}
	
	@Override
	@Transactional
	public Notes deleteNotes(Long notesId, String token) throws UserException, NotesException {

		Long userId = (Long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		Notes notes = user.getNotes().stream().filter((note) -> note.getNoteId().equals(notesId)).findFirst().orElseThrow(() -> new NotesException("Note is not found", HttpStatus.NOT_FOUND));
      	notes.setTrashed(true);
      	notes.setArchieved(false);
      	notes.setPinned(false);
		userRepository.registrationSave(user);
		
		return notes;
	}	
	
	@Transactional
	@Override
	public Notes pinNote(Long notesId, String token) throws UserException, NotesException {
		Long userId = (Long) jwtop.parseJWT(token);

		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
      	Notes notes = user.getNotes().stream().filter((note) -> note.getNoteId().equals(notesId)).findFirst()	.orElseThrow(() -> new NotesException("note not found", HttpStatus.NOT_FOUND));

		notes.setPinned(true);
		notes.setArchieved(false);
		notes.setTrashed(false);
		userRepository.registrationSave(user);

		return notes;
	}
	
	@Transactional
	@Override
	public Notes archieveNotes(long notesId, String token) throws UserException, NotesException 
	{
		Long userId = (Long) jwtop.parseJWT(token);

		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));

		Notes notes = user.getNotes().stream().filter((note) -> note.getNoteId().equals(notesId)).findFirst()	.orElseThrow(() -> new NotesException("note not found", HttpStatus.NOT_FOUND));

		notes.setPinned(false);
		notes.setArchieved(true);
		notes.setTrashed(false);
		userRepository.registrationSave(user);

		return notes;
	}
	@Transactional
	@Override
	public Notes trashNotes(long notesId, String token) throws UserException, NotesException 
	{
		Long userId = (Long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
		Notes notes = user.getNotes().stream().filter((note) -> note.getNoteId().equals(notesId)).findFirst()	.orElseThrow(() -> new NotesException("note not found", HttpStatus.NOT_FOUND));
		notes.setPinned(false);
		notes.setArchieved(false);
		notes.setTrashed(true);
		userRepository.registrationSave(user);

		return notes;
	}
	
	@Transactional
	@Override
	public Notes updateNotes(Notes information, String token) throws UserException, NotesException 
	{
		Long userId = (long) 0;
		userId = (Long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
		Notes notes = user.getNotes().stream().filter((note) -> note.getNoteId().equals(information.getNoteId())).findFirst().orElseThrow(() -> new NotesException("note not found", HttpStatus.NOT_FOUND));
		notes.setTitle(information.getTitle());
		notes.setDescription(information.getDescription());
		notes.setPinned(information.isPinned());
		notes.setTrashed(information.isTrashed());
		notes.setArchieved(information.isArchieved());
		notes.setUpDateAndTime(LocalDateTime.now());
		userRepository.registrationSave(user);
     	return notes;
	}
	
}
