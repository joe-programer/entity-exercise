package se.lexicon.entityexercise.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.entityexercise.entity.AppUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class AppUserDAOImplTest {

    @Autowired
    AppUserDAO appUserDAO;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findById() {
        AppUser appUser = new AppUser("lyta", "Ly", "Ta", LocalDate.parse("1999-05-16"), "ly123456");

        entityManager.persist(appUser);
        AppUser findById = appUserDAO.findById(appUser.getUserId());

        assertNotNull(findById);
        assertEquals(appUser.getUserId(), findById.getUserId());
    }

    @Test
    void save() {
        AppUser appUser = new AppUser("lyta", "Ly", "Ta", LocalDate.parse("1999-05-16"), "ly123456");
        appUserDAO.save(appUser);
        assertNotEquals(0, appUser.getUserId());
    }

    @Test
    void delete() {
        AppUser appUser = new AppUser("lyta", "Ly", "Ta", LocalDate.parse("1999-05-16"), "ly123456");

        appUserDAO.save(appUser);
        appUserDAO.delete(appUser);
        AppUser findById = appUserDAO.findById(appUser.getUserId());
        assertNull(findById);

    }
}