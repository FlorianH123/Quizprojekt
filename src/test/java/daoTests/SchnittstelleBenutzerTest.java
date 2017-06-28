package daoTests;

import dao.SchnittstelleBenutzer;
import exception.DataNotFoundException;
import model.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Florian on 23.06.2017.
 * Testklasse um die Klasse dao.Schnittstellebenutzer zu testen
 */
public class SchnittstelleBenutzerTest {
    private User aUser1 = new User(1        ,"testaccount1@mail.de", "passwort1", "link.link", "testaccount1");
    private User aUser2 = new User(2        ,"testaccount2@mail.de", "passwort2", "link.link", "testaccount2");
    private User aUser3 = new User(3        ,"testaccount3@mail.de", "passwort3", "link.link", "testaccount3");
    private User aUser4 = new User(99999999 ,"testaccount4@mail.de", "passwort4", "link.link", "testaccount4");

    private SchnittstelleBenutzer schnittBenutzer = new SchnittstelleBenutzer();
    private boolean equals;


    //Tests für getUserByID Methode
    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void getUserByIDTest() throws Exception {
        equals = schnittBenutzer.getUserByID(1).getE_mail().equals(aUser1.getE_mail()) &&
                 schnittBenutzer.getUserByID(1).getPasswort().equals(aUser1.getPasswort()) &&
                 schnittBenutzer.getUserByID(1).getAvatar_link().equals(aUser1.getAvatar_link()) &&
                 schnittBenutzer.getUserByID(1).getName().equals(aUser1.getName());
        assertTrue(equals);
    }

    @Test
    public void getUserByIDTest2() throws Exception {
        equals = schnittBenutzer.getUserByID(2).getE_mail().equals(aUser2.getE_mail()) &&
                 schnittBenutzer.getUserByID(2).getPasswort().equals(aUser2.getPasswort()) &&
                 schnittBenutzer.getUserByID(2).getAvatar_link().equals(aUser2.getAvatar_link()) &&
                 schnittBenutzer.getUserByID(2).getName().equals(aUser2.getName());
        assertTrue(equals);
    }

    @Test
    public void getUserByIDTest3() throws Exception {
        equals = schnittBenutzer.getUserByID(3).getE_mail().equals(aUser3.getE_mail()) &&
                 schnittBenutzer.getUserByID(3).getPasswort().equals(aUser3.getPasswort()) &&
                 schnittBenutzer.getUserByID(3).getAvatar_link().equals(aUser3.getAvatar_link()) &&
                 schnittBenutzer.getUserByID(3).getName().equals(aUser3.getName());
        assertTrue(equals);

    }

    @Test
    public void getUserByIDTest4() throws Exception {
        equals = schnittBenutzer.getUserByID(3).getE_mail().equals(aUser2.getE_mail()) &&
                schnittBenutzer.getUserByID(3).getPasswort().equals(aUser2.getPasswort()) &&
                schnittBenutzer.getUserByID(3).getAvatar_link().equals(aUser2.getAvatar_link()) &&
                schnittBenutzer.getUserByID(3).getName().equals(aUser2.getName());
        assertFalse(equals);
    }

    @Test (expected = DataNotFoundException.class)
    public void getUserByID_with_exceptionTest() throws Exception {
        schnittBenutzer.getUserByID(aUser4.getId());
    }

    @Test (expected = IllegalArgumentException.class)
    public void getUserByID_with_exceptionTest2() throws Exception {
        schnittBenutzer.getUserByID(-1);
    }


    // Tests für getPasswordByID Methode
    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void getPasswordByIDTest() throws Exception {
        equals = schnittBenutzer.getPasswordByID(1).equals(aUser1.getPasswort());
        assertTrue(equals);
    }

    @Test
    public void getPasswordByIDTest2() throws Exception {
        equals = schnittBenutzer.getPasswordByID(2).equals(aUser2.getPasswort());
        assertTrue(equals);
    }

    @Test
    public void getPasswordByIDTest3() throws Exception {
        equals = schnittBenutzer.getPasswordByID(3).equals(aUser3.getPasswort());
        assertTrue(equals);
    }

    @Test
    public void getPasswordByIDTest4() throws Exception {
        equals = schnittBenutzer.getPasswordByID(3).equals(aUser2.getPasswort());
        assertFalse(equals);
    }

    @Test (expected = DataNotFoundException.class)
    public void getPasswordByIDTest_with_exceptionTest() throws Exception {
        schnittBenutzer.getPasswordByID(aUser4.getId());
    }

    @Test (expected = IllegalArgumentException.class)
    public void getPasswordByIDTest_with_exceptionTest2() throws Exception {
        schnittBenutzer.getPasswordByID(-1);
    }


    //Tests fuer checkID() Methode
    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void checkIDTest1() throws Exception {
        equals = schnittBenutzer.checkID(aUser1.getId());
        assertTrue(equals);
    }

    @Test
    public void checkIDTest2() throws Exception {
        equals = schnittBenutzer.checkID(aUser2.getId());
        assertTrue(equals);
    }

    @Test
    public void checkIDTest3() throws Exception {
        equals = schnittBenutzer.checkID(aUser3.getId());
        assertTrue(equals);
    }

    @Test
    public void checkIDTest4() throws Exception {
        equals = schnittBenutzer.checkID(aUser4.getId());
        assertFalse(equals);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkIDTest_with_exception() throws Exception {
        equals = schnittBenutzer.checkID(-1);
    }


    //Tests fuer checkEMail() Methode
    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void checkEmailTest1() throws Exception {
        equals = schnittBenutzer.checkEmail(aUser1.getE_mail());
        assertTrue(equals);
    }

    @Test
    public void checkEmailTest2() throws Exception {
        equals = schnittBenutzer.checkEmail(aUser2.getE_mail());
        assertTrue(equals);
    }

    @Test
    public void checkEmailTest3() throws Exception {
        equals = schnittBenutzer.checkEmail(aUser3.getE_mail());
        assertTrue(equals);
    }

    @Test
    public void checkEmailTest4() throws Exception {
        equals = schnittBenutzer.checkEmail(aUser4.getE_mail());
        assertFalse(equals);
    }

    @Test (expected = IllegalArgumentException.class)
    public void checkEmailTest_with_exception() throws Exception {
        equals = schnittBenutzer.checkEmail("");
    }
}