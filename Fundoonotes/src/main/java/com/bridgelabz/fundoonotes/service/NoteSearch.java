package com.bridgelabz.fundoonotes.service;


import java.io.IOException;
import java.util.List;

import com.bridgelabz.fundoonotes.entity.Notes;


public interface NoteSearch {
	
	public String creatingNote(Notes note) throws IOException ;
	public String updatingNote(Notes note) throws Exception;
	public String deletingNote(String id) throws IOException;	
	public Notes findNoteById(String id) throws Exception;
	 List<Notes> searchingByTitle(String text);

}
