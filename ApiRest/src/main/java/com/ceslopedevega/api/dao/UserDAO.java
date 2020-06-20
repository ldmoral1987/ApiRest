package com.ceslopedevega.api.dao;

import java.util.List;
import com.ceslopedevega.api.entity.User;

// Esta interfaz declara los métodos que se usarán para conectarse
// a la base de datos de la aplicación.
public interface UserDAO {
	public List<User> findAll();
	public User findById(int id);
	public void save(User user);
	public void deleteById(int id);
}