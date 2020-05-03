package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;



public interface CollaboratorService
{
	Notes addCollaborator(long noteId,String email,String token) throws UserException,NotesException;
    Notes removeCollaborator(long noteId,String email,String token) throws UserException,NotesException;
	List<Notes> getAllCollaboratedNotes(String token) throws UserException;
}
