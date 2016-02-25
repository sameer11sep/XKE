package trips;

import java.util.List;

/**
 * Created by sameer on 25/2/16.
 */
public interface Dao {

    List<Trip> tripsBy(User user);
}
