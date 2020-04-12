package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NotesInformationdto;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.response.NotesResponse;
import com.bridgelabz.fundoonotes.response.UserResponse;
import com.bridgelabz.fundoonotes.service.NotesServices;


@RestController
public class NotesController 
{
	@Autowired
	private NotesServices notesServices;
	
    @PostMapping("/note/create")
    public ResponseEntity<NotesResponse> addNotes(@RequestBody NotesInformationdto information , @RequestHeader("token") String token) throws UserException
    {
    	Notes createNotes=notesServices.addNotes(information ,token);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note is Created", 200 ,createNotes));
    }
    
    @DeleteMapping("/note/delete/{notesId}")
	public ResponseEntity<NotesResponse> deleteNotes(@PathVariable Long notesId, @RequestHeader("token") String token)	throws UserException, NotesException {
		Notes notes = notesServices.deleteNotes(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note Is Deleted", 200, notes));
	}
    
    @PutMapping("/note/pin/{notesId}")
	public ResponseEntity<NotesResponse> pinNote(@PathVariable("notesId") Long noteId, @RequestHeader("token") String token)throws UserException, NotesException
    {
		Notes notes = notesServices.pinNote(noteId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note is pinned", 200, notes));
	}
    
    @PutMapping("/note/archieve/{notesId}")
   	public ResponseEntity<NotesResponse> archieveNotes(@PathVariable("notesId") long notesId, @RequestHeader("token") String token)throws UserException, NotesException
    {
   		Notes notes = notesServices.archieveNotes(notesId, token);
   		return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note Is Achieved", 200, notes));
     }
   		@PutMapping("/note/trash/{notesId}")
   	   	public ResponseEntity<NotesResponse> trashNotes(@PathVariable("notesId") long notesId, @RequestHeader("token") String token)throws UserException, NotesException
   	{
   	   		Notes notes = notesServices.trashNotes(notesId, token);
   	   		return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note Is Trashed", 200, notes));
     }
   	 @PutMapping("/note/update")
     public ResponseEntity<NotesResponse> updateNotes(@RequestBody Notes notes , @RequestHeader("token") String token) throws UserException, NotesException
     {
     	notesServices.updateNotes(notes ,token);
     	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("Note is Updated", 200 ,notes));
     }
	
   	@GetMapping("/note/getallNotes")
    public ResponseEntity<NotesResponse> getallNotes( @RequestHeader("token") String token) throws UserException, NotesException
    {
    	List<Notes> list=notesServices.getallNotes(token);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("List of all Notes", 200 ,list));
    }
   	
 	@GetMapping("/note/getallTrashed")
    public ResponseEntity<NotesResponse> getalltrashed( @RequestHeader("token") String token) throws UserException, NotesException
    {
    	List<Notes> list=notesServices.getalltrashed(token);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("List of all trashed Notes", 200 ,list));
    }
 	
 	@GetMapping("/note/getallArchieve")
    public ResponseEntity<NotesResponse> getallArchieveNotes( @RequestHeader("token") String token) throws UserException, NotesException
    {
    	List<Notes> list=notesServices.getallachieve(token);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("List of all archieve Notes", 200 ,list));
    }
 	@GetMapping("/note/getallPinned")
    public ResponseEntity<NotesResponse> getallPinnedNotes( @RequestHeader("token") String token) throws UserException, NotesException
    {
    	List<Notes> list=notesServices.getAllPinnedNotes(token);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new NotesResponse("List of all pinned Notes", 200 ,list));
    }
    
}
