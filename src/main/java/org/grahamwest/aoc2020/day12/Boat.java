package org.grahamwest.aoc2020.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.grahamwest.aoc2020.util.Coordinate;

@AllArgsConstructor
@ToString
@Getter
public class Boat {

    Coordinate location;
    Coordinate heading;

    public static final Coordinate NORTH = Coordinate.from(0,-1);
    public static final Coordinate EAST  = Coordinate.from(1,0);
    public static final Coordinate SOUTH = Coordinate.from(0,1);
    public static final Coordinate WEST  = Coordinate.from(-1,0);

    public Boat execute(NavigationInstruction nav) {

        char cmd = nav.getCommand();
        int val = nav.getValue();

        if (cmd == 'L') {
            val = 360 - val;
        }
        if (cmd == 'R' || cmd == 'L') {

            Coordinate h = this.getHeading();
            if ( SOUTH.equals(h) ) val += 90;
            if ( WEST.equals(h) ) val += 180;
            if ( NORTH.equals(h) ) val += 270;

            int degrees = val % 360;

            Coordinate newHeading = EAST;
            if (degrees == 90) newHeading = SOUTH;
            if (degrees == 180) newHeading = WEST;
            if (degrees  == 270) newHeading = NORTH;

            return new Boat(location, newHeading);
        }

        Coordinate dir = heading;
        if (cmd == 'N') dir = NORTH;
        if (cmd == 'E') dir = EAST;
        if (cmd == 'S') dir = SOUTH;
        if (cmd == 'W') dir = WEST;

        Coordinate offset = dir.multiply(val);
        Coordinate newLocation = location.add(offset);

        return new Boat( newLocation, heading );

    }

}
