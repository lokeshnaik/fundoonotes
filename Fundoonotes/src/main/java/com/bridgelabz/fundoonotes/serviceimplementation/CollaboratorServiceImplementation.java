package com.bridgelabz.fundoonotes.serviceimplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.CollaboratorService;
import com.bridgelabz.fundoonotes.utility.JWTOperations;

    
@Service
public class CollaboratorServiceImplementation implements CollaboratorService
{
	@Autowired
    private JWTOperations jwtop;
	
	@Autowired
    private UserRepository repository;
	
	@Autowired
    private NotesRepository notesRepository;
	
	@Autowired
    private LabelRepository labelRepository;

	@Transactional
	@Override
	public Notes addCollaborator(long noteId, String email, String token) throws UserException, NotesException 
	{
		// TODO Auto-generated method stub
		User collaborator = repository.getUser(email).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		long userId = (long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		Notes notes = notesRepository.findById(userId).orElseThrow(() -> new UserException("Note is not founded", HttpStatus.NOT_FOUND));
		collaborator.getCollaboratornotes().add(notes);
		return notes;
	}

@Override
	public Notes removeCollaborator(long noteId, String email, String token) throws UserException, NotesException
	{
		// TODO Auto-generated method stub
		User collaborator = repository.getUser(email).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		long userId = (long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		Notes notes = notesRepository.findById(userId).orElseThrow(() -> new UserException("Note is not founded", HttpStatus.NOT_FOUND));
		collaborator.getCollaboratornotes().remove(notes);
		return notes;
	}

	@Override
	public List<Notes> getAllCollaboratedNotes(String token) throws UserException
	{
		// TODO Auto-generated method stub
		long userId = (long) jwtop.parseJWT(token);
		User user = repository.getUserById(userId).orElseThrow(() -> new UserException("User is not founded", HttpStatus.NOT_FOUND));
		List<Notes>Collaboatornotes=user.getCollaboratornotes();
		return Collaboatornotes;
	}

	

}
