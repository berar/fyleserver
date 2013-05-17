package org.fyleserver.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
 
public interface GenericDao<E, ID extends Serializable> {
 
    public void save(E entity);
 
    public void merge(E entity);
 
    public void delete(E entity);
 
    public List<E> findMany(Query query);
 
    public E findOne(Query query);
 
    public List<E> findAll(Class<E> clazz);
 
    public E findByID(Class<E> clazz, long id);
}