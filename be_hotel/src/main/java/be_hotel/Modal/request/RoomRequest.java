package be_hotel.Modal.request;

import be_hotel.Modal.entity.Room;
import lombok.Data;

import java.time.LocalDateTime;

import static be_hotel.utils.MethodConvert.convertDateTime;

@Data
public class RoomRequest {
    private String name;
    private Integer roomQuantity;
    private Integer bedQuantity;
    private Integer price;
    private String description;
    private String image;
    private String createdDate;
    private String modifiedDate;

    public Room asRoom() {
        Room room = new Room();
        room.setName(this.name);
        room.setRoomQuantity(this.roomQuantity);
        room.setBedQuantity(this.bedQuantity);
        room.setPrice(this.price);
        room.setDescription(this.description);
        room.setImage(this.image);
        room.setCreatedDate(LocalDateTime.now());
        room.setModifiedDate(convertDateTime(this.modifiedDate));
        return room;
    }
}
