package trips;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TripServiceTest {

    public static final User GUEST = null;
    private static final User ADITYA = new User();
    private static final Trip TO_LADAKH = new Trip();
    private static final Trip TO_SIKKIM = new Trip();
    private static final User BRIJESH = new User();
    private User loggedInUser = null;
    TripService tripService;

    @Before
    public void init() {
        Dao mockTripDao = new Dao() {
            public List<Trip> tripsBy(User user) {
                return user.getTrips();
            }
        };
        tripService = new TripService(mockTripDao);
    }

    @Test(expected = NotLoggedInException.class)
    public void shouldThrowNotLoggedInExceptionIfUserNotLoggedIn() {
        loggedInUser = GUEST;
        tripService.getUserTrips(ADITYA, loggedInUser);
    }

    @Test
    public void shouldReturnTripsIfUserIsFriendWithAnotherUser() {
        loggedInUser = ADITYA;
        User SOME_USER = UserBuilder.aUser()
                .withFriends(ADITYA, BRIJESH)
                .withTrips(TO_LADAKH, TO_SIKKIM)
                .build();
        List<Trip> trips = tripService.getUserTrips(SOME_USER, loggedInUser);
        assertEquals(2, trips.size());
    }

    @Test
    public void shouldReturnNoTripsIfLoggedInUSerIsNotFriendOfAnotherUser() {
        loggedInUser = ADITYA;
        User SOME_USER = UserBuilder.aUser()
                .withTrips(TO_LADAKH, TO_SIKKIM)
                .build();
        List<Trip> trips = tripService.getUserTrips(SOME_USER, loggedInUser);
        assertEquals(0, trips.size());
    }


}