package trips;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<User> friends = new ArrayList<User>();

    private List<Trip> trips = new ArrayList<Trip>();

    public List<User> getFriends() {
        return friends;
    }

}