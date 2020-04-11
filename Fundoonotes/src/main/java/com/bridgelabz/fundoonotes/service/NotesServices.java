package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.NotesInformationdto;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;

public interface NotesServices
{
	Notes addNotes(NotesInformationdto notesInformationdto,String token) throws UserException;
	
	Notes deleteNotes(Long notesId, String token)  throws UserException, NotesException;
	Notes pinNote(Long notesId, String token)  throws UserException, NotesException;
	Notes archieveNotes(long notesId, String token)  throws UserException, NotesException;
	Notes trashNotes(long notesId, String token)  throws UserException, NotesException;
	
}
