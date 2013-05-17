package org.fyleserver.db.impl;

import org.fyleserver.db.UserDao;
import org.fyleserver.db.entity.User;
import org.hibernate.Query;

public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	@Override
	public User findByUsername(String username) {
		User user = null;
		String sql = "SELECT u FROM User u WHERE u.username = :username";
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery(sql).setParameter("username", username);
		user = findOne(query);
		HibernateUtil.commitTransaction();
		return user;
	}
	
	@Override
	public User findByEmail(String email){
		User user = null;
		String sql = "SELECT u FROM User u WHERE u.email = :email";
		HibernateUtil.beginTransaction();
		Query query = HibernateUtil.getSession().createQuery(sql).setParameter("email", email);
		user = findOne(query);
		HibernateUtil.commitTransaction();
		return user;
	}
}
