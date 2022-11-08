package three.stone.object_oriented_design.hotel_management_system;

import java.util.Date;

public abstract class RoomCharge {
    public Date issueAt;
    public boolean addInvoiceItem(Invoice invoice) {
        return true;
    }
}
