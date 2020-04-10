package com.bridgelabz.fundoonotes.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@Override
	public boolean deleteNotes(long notesId, long userId) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(notesId);
		Query query = session.createQuery("DELETE FROM Notes where note_id=:id").setParameter("id", notesId);		
		int result = query.executeUpdate();
		if (result >= 1) 
		{
			return true;
		}
		else
		{
		return false;
		}
	}
	
}
