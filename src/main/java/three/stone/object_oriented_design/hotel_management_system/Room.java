package three.stone.object_oriented_design.hotel_management_system;

import java.util.Date;
import java.util.List;

public class Room  implements Search{
    private String roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;

    private List<RoomKey> keys;
    private List<RoomHouseKeeping> houseKeepingLog;

    public boolean isRoomAvailable() {
        return true;
    }
    public boolean checkIn() {
        return true;
    }
    public boolean checkOut() {
        return true;
    }

    public List<Room> search(RoomStyle style, Date startDate, int duration) {
        // return all rooms with the given style and availability
        return null;
    }
}
