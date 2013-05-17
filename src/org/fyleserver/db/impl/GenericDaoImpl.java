package org.fyleserver.db.impl;

import java.io.Serializable;
import java.util.List;


import org.fyleserver.db.GenericDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public abstract class GenericDaoImpl<E, ID extends Serializable> implements GenericDao<E, ID> {
 
    protected Session getSession() {
        return HibernateUtil.getSession();
    }
 
    public void save(E entity) {
    	try {
    		HibernateUtil.beginTransaction(); 
            Session hibernateSession = HibernateUtil.getSession();
            hibernateSession.saveOrUpdate(entity);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Handle your error here");
            HibernateUtil.rollbackTransaction();
        }
    }
 
    public void merge(E entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(E entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
    
    @SuppressWarnings("unchecked")
    public List<E> findMany(Query query) {
        List<E> t;
        t = (List<E>) query.list();
        return t;
    }
 
    @SuppressWarnings("unchecked")
	public E findOne(Query query) {
        E t;
        t = (E) query.uniqueResult();
        return t;
    }
    
    @SuppressWarnings("unchecked")
    public E findByID(Class<E> clazz, long id) {
        Session hibernateSession = this.getSession();
        E t = null;
        t = (E) hibernateSession.get(clazz, id);
        return t;
    }
    
    @SuppressWarnings("unchecked")
    public List<E> findAll(Class<E> clazz) {
        Session hibernateSession = this.getSession();
        List<E> T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
}