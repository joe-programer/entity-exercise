package se.lexicon.entityexercise.dao;

import se.lexicon.entityexercise.entity.AppUser;

public interface AppUserDAO {
    AppUser findById(int id);
    AppUser save(AppUser appUser);
    AppUser delete(AppUser appUser);

}
