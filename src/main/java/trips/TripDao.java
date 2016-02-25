package trips;

import java.util.List;

public class TripDao implements Dao {
    public static List<Trip> getTrips(User user) {
        throw new IllegalStateException("Please don't call this method from a dependent class");
    }

    public List<Trip> tripsBy(User user) {
        throw new IllegalStateException("Please don't call this method from a dependent class");
    }
}