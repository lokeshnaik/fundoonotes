package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import com.bridgelabz.fundoonotes.entity.Notes;

public interface NotesRepository 
{
	Notes save(Notes notesInformation);
	boolean deleteNotes(long Id, long userId);
	List<Notes> getPinnedNotes(long userid);
	List<Notes> getArchievedNotes(long userid);
	List<Notes> getTrashedNotes(long userid);
	boolean updateNotes(Notes information,long id);
}
