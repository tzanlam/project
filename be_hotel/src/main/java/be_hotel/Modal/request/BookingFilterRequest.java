package be_hotel.Modal.request;

import lombok.Data;

@Data
public class BookingFilterRequest {
    private Integer roomId;
    private String checkinStart;
    private String checkinEnd;
    private String checkoutStart;
    private String checkoutEnd;
}
