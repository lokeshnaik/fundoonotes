package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.NotesInformationdto;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.UserException;

public interface NotesServices
{
	Notes addNotes(NotesInformationdto notesInformationdto,String token) throws UserException;
}
