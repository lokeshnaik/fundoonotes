package com.bridgelabz.fundoonotes.serviceimplementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LabelUpdate;
import com.bridgelabz.fundoonotes.dto.Labeldto;
import com.bridgelabz.fundoonotes.entity.Label;
import com.bridgelabz.fundoonotes.entity.Notes;
import com.bridgelabz.fundoonotes.entity.User;
import com.bridgelabz.fundoonotes.exception.LabelException;
import com.bridgelabz.fundoonotes.exception.NotesException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.repository.NotesRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.LabelServices;
import com.bridgelabz.fundoonotes.utility.JWTOperations;

@Service
public class LabelServicesImplementation implements LabelServices
{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JWTOperations jwtop;
	@Autowired
	private LabelRepository labelRepository;
	@Autowired
	private NotesRepository notesrepository;
	
	
	
	
	@Transactional
	@Override
	public Label addLabel(Labeldto labeldto, String token) throws UserException 
	{
		Long userId = null;

		 userId = (Long) jwtop.parseJWT(token);
		Label labeladd = new Label();
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
		BeanUtils.copyProperties(labeldto, labeladd);
		user.getLabel().add(labeladd);
		userRepository.registrationSave(user);
		return labeladd;
	}
	
	
	@Transactional
	@Override
	public void editLabel(LabelUpdate labelupdate, String token) throws UserException, LabelException {
		Long id = null;
		id = (Long) jwtop.parseJWT(token);
		User user = userRepository.getUserById(id).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
	    Label label = labelRepository.fetchLabelById(labelupdate.getLabelId()).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
        label.setName(labelupdate.getLabelName());
		labelRepository.save(label);

	}
	@Transactional
	@Override
	public Label deleteLabel(LabelUpdate labelupdate, String token) throws UserException, NotesException,LabelException {
		Long id = null;
		id = (Long) jwtop.parseJWT(token);
		User user = userRepository.getUserById(id).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
		Label labels = user.getLabel().stream().filter((label) -> label.getLabelId().equals(labelupdate.getLabelId())).findFirst().orElseThrow(() -> new LabelException("label not found", HttpStatus.NOT_FOUND));;
		labelRepository.deleteLabelpermanently(labels.getLabelId());
		user.getLabel().remove(labels);
      return labels;
	}
	
	@Override
	@Transactional
	public List<Label> getLabel(String token) {
		Long userId = null;
		userId = (Long) jwtop.parseJWT(token);	
		List<Label> labels = labelRepository.getAllLabel(userId);
		return labels;
	}
	
	@Transactional
	@Override
	public Notes maplabeltonote(Labeldto labeldto, String token, long noteId)	throws LabelException, NotesException, UserException 
	{
		Label label = new Label();
		Long userId = null;
		userId = (Long) jwtop.parseJWT(token);
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserException("user not found", HttpStatus.NOT_FOUND));
		Notes note = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note not found", HttpStatus.NOT_FOUND));
		BeanUtils.copyProperties(labeldto, label);
		user.getLabel().add(label);
		userRepository.registrationSave(user);
		note.getLabel().add(label);
		notesrepository.save(note);
       return note;
	}
	
	@Transactional
	@Override
	public Notes addingLabelToNote(long labelId, String token, long noteId)	throws NotesException, LabelException, UserException {
		Long userId = null;
		userId = (Long) jwtop.parseJWT(token);
		User user = userRepository.getUserById(userId).orElseThrow(() -> new UserException("User is not found", HttpStatus.NOT_FOUND));
		Notes notes = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note is not found", HttpStatus.NOT_FOUND));
		Label label = labelRepository.fetchLabelById(labelId).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
		if (!user.getNotes().contains(notes)) 
		{
			throw new NotesException("Note is not found", HttpStatus.NOT_FOUND);
		}
		if (!user.getLabel().contains(label))
		{
			throw new LabelException("Label is not found", HttpStatus.NOT_FOUND);
		}
		notes.getLabel().add(label);
		notesrepository.save(notes);
		labelRepository.save(label);
		return notes;
	}
	
	@Transactional
	@Override
	public Notes removingLabelFromNote(long labelId, String token, long noteId) throws NotesException, LabelException
	{
		Notes notes = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note is not found", HttpStatus.NOT_FOUND));
		Label label = labelRepository.fetchLabelById(labelId).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
		notes.getLabel().add(label);
		notesrepository.save(notes);
		labelRepository.save(label);
		return notes;
	}

	
	
}
