package be_hotel.Modal.DTO;

import lombok.Data;

@Data
public class RoomDTO {
    private String name;
    private String roomQuantity;
    private String bedQuantity;
    private String price;
    private String description;
    private String image;
    private String status;
}
