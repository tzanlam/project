package be_hotel.serive.room;

import be_hotel.Modal.entity.Room;
import be_hotel.Modal.request.RoomRequest;
import org.springframework.data.domain.Page;

public interface RoomService {
    public Room createRoom(RoomRequest roomRequest);
    Page<Room> getAllRoom(int page, int size);
    public Room updateRoom(RoomRequest roomRequest, Integer id);
    public String deleteRoom(Integer id);
}
