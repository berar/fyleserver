package org.fyleserver.db;

import org.fyleserver.db.entity.User;

public interface UserDao extends GenericDao<User, Long>{
	public User findByUsername(String username);
	public User findByEmail(String email);
}
