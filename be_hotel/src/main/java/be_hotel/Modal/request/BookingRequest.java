package be_hotel.Modal.request;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.entity.Room;
import lombok.Data;

import java.time.LocalDateTime;

import static be_hotel.utils.MethodConvert.convertDateTime;

@Data
public class BookingRequest {
    private Integer roomId;
    private Integer accountId;
    private String checkin;
    private String checkout;
    private String deadlineDate;
    private String createDate;
    private String modifiedDate;

    public Booking asBooking() {
        Room room = new Room();
        room.setId(this.roomId);  // Set ID cho Room
        Account account = new Account();
        account.setId(this.accountId);  // Set ID cho Account
        Booking booking = new Booking();
        booking.setRoom(room);  // Gán Room vào Booking (Sửa tên phương thức set)
        booking.setAccount(account);  // Gán Account vào Booking (Sửa tên phương thức set)
        booking.setCheckin(convertDateTime(this.checkin));  // Chuyển đổi và gán thời gian checkin
        booking.setCheckout(convertDateTime(this.checkout));  // Chuyển đổi và gán thời gian checkout
        booking.setDeadlineDate(convertDateTime(this.deadlineDate));  // Chuyển đổi và gán deadlineDate
        if (this.createDate == null || this.createDate.isEmpty()) {
            booking.setCreateDate(LocalDateTime.now());
        } else {
            booking.setCreateDate(convertDateTime(this.createDate));
        }
        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            booking.setModifiedDate(LocalDateTime.now());
        } else {
            booking.setModifiedDate(convertDateTime(this.modifiedDate));
        }

        return booking;
    }
}
