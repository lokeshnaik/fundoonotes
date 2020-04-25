package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.dto.Labeldto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.exception.LabelException;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.UserResponse;
import com.bridgelabz.fundoonotes.service.LabelServices;

@RestController
public class LabelController
{
	@Autowired
	LabelServices services;
	
	
	@PostMapping("/label/add")
	public ResponseEntity<UserResponse> createLabel(@RequestBody Labeldto labeldto, @RequestHeader("token") String token) throws UserException 
	{
		services.addLabel(labeldto, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse("Label  is Added ", 200, labeldto));

	}
	
	@PutMapping("/label/update")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) throws UserException, LabelException 
	{
		services.editLabel(label, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Label is  updated ", 200, label));

	}
	
	@DeleteMapping("/label/delete")
	public ResponseEntity<Response> deleteLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) throws UserException, NotesException, LabelException {
		services.deleteLabel(label, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Label is deleted ", 200, label));

	}
	@GetMapping("/label/getalllabels")
	public ResponseEntity<Response> getLabels(@RequestHeader("token") String token) {
		List<Label> labels = services.getLabel(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Getting all labels  ", 200, labels));
	}
	@PostMapping("/label/maplabeltonote")
	public ResponseEntity<UserResponse> maplabeltonote(@RequestBody Labeldto Labeldto, @RequestHeader("token") String token,@RequestParam("noteId") long noteId) throws LabelException, NotesException, UserException
	{
		Notes notes=services.maplabeltonote(Labeldto, token, noteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse("Label is created ", 200, notes));
	}
	
	@PostMapping("/label/addlabeltonote")
	public ResponseEntity<UserResponse> addlabel(@RequestParam("labelId") Long labelId,@RequestHeader("token") String token, @RequestParam("noteId") Long noteId) throws NotesException, LabelException, UserException {
		Notes notes=services.addingLabelToNote(labelId, token, noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("label added ", 200,notes));
	}
	@PostMapping("/label/removelabelfromnote")
	public ResponseEntity<UserResponse> removelabelfromnote(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token, @RequestParam("noteId") long noteId) throws NotesException, LabelException {
		Notes notes=services.removingLabelFromNote(labelId, token, noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("label removed ", 200,notes));

	}
}
