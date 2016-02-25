package trips;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<User> friends = new ArrayList<User>();

    private List<Trip> trips = new ArrayList<Trip>();

    public List<User> getFriends() {
        return friends;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public boolean isFriendWith(User anotherUser) {
        return this.friends.contains(anotherUser);
    }
}