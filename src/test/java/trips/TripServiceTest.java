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
    private static final User RAHUL = new User();
    private static final Trip TO_LADAKH = new Trip();
    private static final Trip TO_SIKKIM = new Trip();
    private User loggedInuser = null;
    TripService tripService;

    @Before
    public void init(){
        tripService = new TestableTripService();
    }

    @After
    public void clean(){
        RAHUL.getTrips().clear();
        RAHUL.getFriends().clear();
    }

    @Test(expected = NotLoggedInException.class)
    public void shouldThrowNotLoggedInExceptionIfUserNotLoggedIn() {
        loggedInuser = GUEST;
        tripService.getUserTrips(loggedInuser);
    }

    @Test
    public void shouldReturnTripsIfUserIsFriendWithAnotherUser() {
        loggedInuser = ADITYA;
        RAHUL.getFriends().add(ADITYA);
        RAHUL.getTrips().add(TO_LADAKH);
        RAHUL.getTrips().add(TO_SIKKIM);
        List<Trip> trips = tripService.getUserTrips(RAHUL);
        assertEquals(2, trips.size());
    }

    @Test
    public void shouldReturnNoTripsIfLoggedInUSerIsNotFriendOfAnotherUser(){
        loggedInuser=ADITYA;
        RAHUL.getTrips().add(TO_LADAKH);
        RAHUL.getTrips().add(TO_SIKKIM);
        List<Trip> trips = tripService.getUserTrips(RAHUL);
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