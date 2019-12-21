package budjettisovellus.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Test
    public void getUsernameWorks() {
        user = new User(0, "test_user");
        assertEquals("test_user", user.getUsername());

    }

}
