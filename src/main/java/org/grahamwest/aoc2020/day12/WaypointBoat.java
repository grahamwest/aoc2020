package org.grahamwest.aoc2020.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.grahamwest.aoc2020.util.Coordinate;

@AllArgsConstructor
@ToString
@Getter
public class WaypointBoat {

    Coordinate location;
    Coordinate heading;

    public static final Coordinate NORTH = Coordinate.from(0,-1);
    public static final Coordinate EAST  = Coordinate.from(1,0);
    public static final Coordinate SOUTH = Coordinate.from(0,1);
    public static final Coordinate WEST  = Coordinate.from(-1,0);


    private Coordinate rotateWaypoint(int degrees) {
        int numRotations = ((360 + degrees) % 360) / 90;

        Coordinate waypoint = this.getHeading();
        for (int i = 0; i < numRotations; i++) {
            waypoint = waypoint.rotate90();
        }

        return waypoint;
    }


    public WaypointBoat execute(NavigationInstruction nav) {

        char cmd = nav.getCommand();
        int val = nav.getValue();

        if (cmd == 'L') {
            val = 360 - val;
        }

        if (cmd == 'R' || cmd == 'L') {
            return new WaypointBoat(location, rotateWaypoint(val));
        }

        if (cmd == 'F') {
            return new WaypointBoat( location.add(heading.multiply(val)), heading);
        }

        Coordinate dir = heading;
        if (cmd == 'N') dir = NORTH;
        if (cmd == 'E') dir = EAST;
        if (cmd == 'S') dir = SOUTH;
        if (cmd == 'W') dir = WEST;

        Coordinate offset = dir.multiply(val);

        return new WaypointBoat( location, heading.add(offset) );
    }

}
