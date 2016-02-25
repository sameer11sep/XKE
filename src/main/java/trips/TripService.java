package trips;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getUserTrips(User user) throws NotLoggedInException {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            boolean isFriend = false;
            for (User friend : user.getFriends()) {
                if (loggedInUser.equals(friend)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                return tripsBy(user);
            }
        } else {
            throw new NotLoggedInException("You are not currently logged in..");
        }
        return new ArrayList<Trip>();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDao.getTrips(user);
    }

    protected User getLoggedInUser() {
        return SecurityContext.getLoggedInUser();
    }

}
