package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.response.CollaboratorResponse;
import com.bridgelabz.fundoonotes.service.CollaboratorService;

@RestController
public class collaboratorController 
{

	@Autowired
	private CollaboratorService collaboratorservice;
	
	
	
	@PostMapping("/Collaborator/Add")
	public ResponseEntity<CollaboratorResponse> addCollaborator(@RequestParam("email") String email,@RequestParam("noteId") long noteId,@RequestHeader("token") String token) throws UserException, NotesException
    {
		Notes notes=collaboratorservice.addCollaborator(noteId,email,token);
	  return ResponseEntity.status(HttpStatus.CREATED).body(new CollaboratorResponse("Colloborator is mapped",200,notes));
    }
	
	@DeleteMapping("/Collaborator/Remove")
	public ResponseEntity<CollaboratorResponse> removeCollaborator(@RequestParam("email") String email,@RequestParam("noteId") long noteId,@RequestHeader("token") String token) throws UserException, NotesException
    {
		Notes notes=collaboratorservice.removeCollaborator(noteId,email,token);
	  return ResponseEntity.status(HttpStatus.CREATED).body(new CollaboratorResponse("Colloborator is unmapped",200,notes));
    }
	
	@GetMapping("/Collaborator/GetAllCollaborators")
	public ResponseEntity<CollaboratorResponse> getAllCollaboratedNotes(@RequestHeader("token") String token) throws UserException, NotesException
    {
		List<Notes> notescollaborator=collaboratorservice.getAllCollaboratedNotes(token);
	  return ResponseEntity.status(HttpStatus.CREATED).body(new CollaboratorResponse("List of Colloborators",200,notescollaborator));
    }
	
}
