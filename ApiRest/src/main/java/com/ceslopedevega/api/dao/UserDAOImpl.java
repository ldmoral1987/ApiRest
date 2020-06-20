package com.ceslopedevega.api.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ceslopedevega.api.entity.User;

//En esta clase se implementa la interfaz, se añade la anotación 
//@Repository que indica que es un DAO,y mediante la anotación 
//@Autowired se inyecta la dependencia EntityManager, que será
//utilizada para crear una sesión y poder enviar las peticiones
//a la base de datos, en cada uno de los métodos implementados

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public User findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		User user = currentSession.get(User.class, id);
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = entityManager.unwrap(Session.class);
		Transaction txn = currentSession.beginTransaction();
		currentSession.saveOrUpdate(user);	
		txn.commit();
	}

	@Override
	public void deleteById(int id) {		
		Session currentSession = entityManager.unwrap(Session.class);
		Transaction txn = currentSession.beginTransaction();
		Query<User> theQuery = currentSession.createQuery("delete from User where id=:idUser");
		theQuery.setParameter("idUser", id);
		theQuery.executeUpdate();
		txn.commit();
	}
}
