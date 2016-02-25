package trips;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private Dao tripDao;

    public TripService(Dao tripDao) {
        this.tripDao = tripDao;
    }

    public static final ArrayList<Trip> no_trips = new ArrayList<Trip>();

    public List<Trip> getUserTrips(User user, User loggedInUser) throws NotLoggedInException {
        if (loggedInUser == null) {
            throw new NotLoggedInException("You are not currently logged in..");
        }
        return user.isFriendWith(loggedInUser) ? tripDao.tripsBy(user) : no_trips;
    }

}
