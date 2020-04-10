package com.bridgelabz.fundoonotes.repository;

import com.bridgelabz.fundoonotes.entity.Notes;

public interface NotesRepository 
{
	Notes save(Notes notesInformation);
	boolean deleteNotes(long Id, long userId);
}
