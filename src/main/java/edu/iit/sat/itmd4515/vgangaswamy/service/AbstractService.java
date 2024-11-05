package edu.iit.sat.itmd4515.vgangaswamy.service;

import edu.iit.sat.itmd4515.vgangaswamy.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public abstract class AbstractService<T> {
    @PersistenceContext(name = "itmd4515PU")
    protected EntityManager em;

    protected Class<T> entityClass;

    protected AbstractService(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public void create(T e){
        em.persist(e);
    }
    public T read(Long id){
       return em.find(entityClass, id);
    }

    public void update(T e){
        em.merge(e);
    }
    public void delete(T e){
        em.remove(em.merge(e));
    }

    protected List<T> readAll(String namedQueryName){
        return em.createNamedQuery(namedQueryName, entityClass).getResultList();
    }

}
