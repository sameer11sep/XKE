package trips;

/**
 * Created by sameer on 25/2/16.
 */
public class UserBuilder {
    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withFriends(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        for (Trip trip : this.trips) {
            user.addTrip(trip);
        }
        for (User friend : this.friends) {
            user.addFriend(friend);
        }
        return user;
    }
}
