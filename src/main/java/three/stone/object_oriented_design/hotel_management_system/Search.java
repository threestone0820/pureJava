package three.stone.object_oriented_design.hotel_management_system;

import java.util.Date;
import java.util.List;

public interface Search {
    List<Room> search(RoomStyle style, Date startDate, int duration);
}
