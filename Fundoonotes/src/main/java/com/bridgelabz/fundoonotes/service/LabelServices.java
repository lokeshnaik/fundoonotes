package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.dto.Labeldto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.LabelException;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;

public interface LabelServices
{
	Label addLabel(Labeldto labeldto, String token) throws UserException;
	void editLabel(LabelUpdate label, String token) throws UserException, LabelException;
	Label deleteLabel(LabelUpdate label, String token) throws UserException, NotesException, LabelException;
	List<Label> getLabel(String token);
	Notes maplabeltonote(Labeldto labeldto, String token, long id) throws LabelException, NotesException, UserException;
	Notes addingLabelToNote(long labelId, String token, long noteId) throws NotesException, LabelException, UserException;
	Notes removingLabelFromNote(long labelId, String token, long noteId) throws NotesException, LabelException;
} 
