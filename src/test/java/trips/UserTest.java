package trips;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sameer on 25/2/16.
 */
public class UserTest {

    private static final User ANOTHER_USER = new User();
    private static final User ADITYA = new User();
    private static final User RAHUL = new User();

    @Test
    public void shouldInformUserIfItIsNotAFriendWithAnotherUser() {
        User user = UserBuilder.aUser().build();
        assertFalse(user.isFriendWith(ANOTHER_USER));
    }

    @Test
    public void shouldInformUserIfFriendWithAnotherUser(){
        User user = UserBuilder.aUser().withFriends(ADITYA, RAHUL).build();
        assertTrue(user.isFriendWith(ADITYA));

    }

}