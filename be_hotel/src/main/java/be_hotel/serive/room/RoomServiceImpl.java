package be_hotel.serive.room;

import be_hotel.Modal.entity.Room;
import be_hotel.Modal.request.RoomRequest;
import be_hotel.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public Room createRoom(RoomRequest roomRequest) {
        return null;
    }

    @Override
    public Page<Room> getAllRoom(int page, int size) {
        return roomRepository.findAll(PageRequest.of(page, size));
    }
    @Override
    public Room updateRoom(RoomRequest roomRequest, Integer id) {
        if (roomRepository.existsById(id)) {
            Room room = roomRequest.asRoom();
            room.setId(id);
            roomRepository.save(room);
            return room;
        }
        return null;
    }

    @Override
    public String deleteRoom(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return "Delete Success";
        }
        return "Delete Fail";
    }
}
