package se.lexicon.entityexercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.entityexercise.entity.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class AppUserDAOImpl implements AppUserDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public AppUser findById(int id) {
        if(id<=0) throw new IllegalArgumentException("Invalid Id");
        return entityManager.find(AppUser.class, id);
    }

    @Override
    @Transactional
    public AppUser save(AppUser appUser) {
        if(appUser==null) throw new IllegalArgumentException("appUser is null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser delete(AppUser appUser) {
        if(findById(appUser.getUserId())==null) throw new IllegalArgumentException(" Data not found");
        entityManager.remove(appUser);

        return appUser;
    }
}
