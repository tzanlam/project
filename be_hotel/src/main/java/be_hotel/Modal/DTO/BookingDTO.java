package be_hotel.Modal.DTO;

import lombok.Data;

@Data
public class BookingDTO {
    private Integer roomId;
    private Integer accountId;
    private String checkin;
    private String checkout;
    private String deadlineDate;
    private String status;
}
