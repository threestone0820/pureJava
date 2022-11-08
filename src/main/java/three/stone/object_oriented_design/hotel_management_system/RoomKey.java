package three.stone.object_oriented_design.hotel_management_system;

import java.util.Date;

public class RoomKey {
    private String keyId;
    private String barcode;
    private Date issuedAt;
    private boolean active;
    private boolean isMaster;

    public boolean assignRoom(Room room) {
        return true;
    }
    public boolean isActive() {
        return true;
    }
}
