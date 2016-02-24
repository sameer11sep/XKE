package trips;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    public List<Trip> getUserTrips(User user) throws NotLoggedInException {
        User loggedInUser = SecurityContext.getLoggedInUser();
        if (loggedInUser != null) {
            boolean isFriend = false;
            for (User friend : user.getFriends()) {
                if (loggedInUser.equals(friend)) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                TripDao.getTrips(user);
            }
        } else {
            throw new NotLoggedInException("You are not currently logged in..");
        }
        return new ArrayList<Trip>();
    }

}
