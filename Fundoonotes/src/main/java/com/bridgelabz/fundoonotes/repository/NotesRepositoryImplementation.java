package com.bridgelabz.fundoonotes.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.entity.Notes;

@Component
public class NotesRepositoryImplementation implements NotesRepository 
{
	@Autowired
	EntityManager entityManager;

	@Override
	  public Notes save(Notes notesInformation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(notesInformation);
		return notesInformation;
	}
}
