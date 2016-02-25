package trips;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by sameer on 25/2/16.
 */
public class TripServiceTest {

    public static final User GUEST = null;
    private static final User ADITYA = new User();
    private static final Trip TO_LADAKH = new Trip();
    private static final Trip TO_SIKKIM = new Trip();
    private static final User BRIJESH = new User();
    private User loggedInuser = null;
    TripService tripService;

    @Before
    public void init(){
        tripService = new TestableTripService();
    }

    @Test(expected = NotLoggedInException.class)
    public void shouldThrowNotLoggedInExceptionIfUserNotLoggedIn() {
        loggedInuser = GUEST;
        tripService.getUserTrips(loggedInuser);
    }

    @Test
    public void shouldReturnTripsIfUserIsFriendWithAnotherUser() {
        loggedInuser = ADITYA;
        User SOME_USER = UserBuilder.aUser()
                .withFriends(ADITYA, BRIJESH)
                .withTrips(TO_LADAKH, TO_SIKKIM)
                .build();
        List<Trip> trips = tripService.getUserTrips(SOME_USER);
        assertEquals(2, trips.size());
    }

    @Test
    public void shouldReturnNoTripsIfLoggedInUSerIsNotFriendOfAnotherUser(){
        loggedInuser=ADITYA;
        User SOME_USER = UserBuilder.aUser()
                .withTrips(TO_LADAKH, TO_SIKKIM)
                .build();
        List<Trip> trips = tripService.getUserTrips(SOME_USER);
        assertEquals(0, trips.size());
    }


    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInuser;
        }

        @Override
        protected List<Trip> tripsBy(User user) {
            return user.getTrips();
        }
    }

}