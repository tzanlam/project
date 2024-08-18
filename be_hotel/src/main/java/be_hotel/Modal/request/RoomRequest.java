package be_hotel.Modal.request;

import be_hotel.Modal.entity.Room;
import be_hotel.Modal.entity.constants.StatusRoom;
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
    private String status;

    public Room asRoom() {
        Room room = new Room();
        room.setName(this.name);
        room.setRoomQuantity(this.roomQuantity);
        room.setBedQuantity(this.bedQuantity);
        room.setPrice(this.price);
        room.setDescription(this.description);
        room.setImage(this.image);

        if (this.createdDate == null || this.createdDate.isEmpty()) {
            room.setCreatedDate(LocalDateTime.now());
        } else {
            room.setCreatedDate(convertDateTime(this.createdDate));
        }

        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            room.setModifiedDate(LocalDateTime.now());
        } else {
            room.setModifiedDate(convertDateTime(this.modifiedDate));
        }
        if (this.status == null || this.status.isEmpty()) {
            room.setStatus(StatusRoom.AVAILABLE);
        } else {
            room.setStatus(StatusRoom.valueOf(this.status));
        }
        return room;
    }

    public Room updateRoom(Room room) {
        room.setName(this.name);
        room.setRoomQuantity(this.roomQuantity);
        room.setBedQuantity(this.bedQuantity);
        room.setPrice(this.price);
        room.setDescription(this.description);
        room.setImage(this.image);
        room.setModifiedDate(LocalDateTime.now());
        return room;
    }
}
