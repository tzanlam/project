package be_hotel.Modal.request;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.entity.Room;
import be_hotel.Modal.entity.constants.StatusDeleted;
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
    private String status;
    private String createDate;
    private String modifiedDate;

    public Booking asBooking() {
        Room room = new Room();
        room.setId(this.roomId);
        Account account = new Account();
        account.setId(this.accountId);
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setAccount(account);
        booking.setCheckin(convertDateTime(this.checkin));
        booking.setCheckout(convertDateTime(this.checkout));
        LocalDateTime createDateTime;
        if (this.createDate == null || this.createDate.isEmpty()) {
            createDateTime = LocalDateTime.now();
            booking.setCreateDate(createDateTime);
        } else {
            createDateTime = convertDateTime(this.createDate);
            booking.setCreateDate(createDateTime);
        }
        LocalDateTime deadlineDateTime = createDateTime.plusDays(3);
        booking.setDeadlineDate(deadlineDateTime);
        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            booking.setModifiedDate(LocalDateTime.now());
        } else {
            booking.setModifiedDate(convertDateTime(this.modifiedDate));
        }
        booking.setStatus(StatusDeleted.ACTIVE);
        return booking;
    }

    public Booking updateBooking(Booking booking){
        Room room = new Room();
        room.setId(this.roomId);
        booking.setRoom(room);
        booking.setCheckin(convertDateTime(this.checkin));
        booking.setCheckout(convertDateTime(this.checkout));
        booking.setModifiedDate(LocalDateTime.now());
        if (this.status != null && !this.status.isEmpty()) {
            booking.setStatus(StatusDeleted.valueOf(this.status));
        }
        else {
            booking.setStatus(StatusDeleted.ACTIVE);
        }
        return booking;
    }
}