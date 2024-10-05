package hotel.service.room;

import hotel.modal.entity.Room;
import hotel.modal.request.RoomRequest;
import hotel.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Page<Room> findAll(int page) {
        return roomRepository.findAll(PageRequest.of(page, 10));
    }

    @Override
    public Room create(RoomRequest request) throws IOException {
        Room room = request.asRoom();
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room update(int id, RoomRequest request) throws Exception {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));

        Room updatedRoom = request.updateRoom(existingRoom);
        updatedRoom.setId(id);
        roomRepository.save(updatedRoom);

        logger.info("Room updated successfully with id: {}", id);
        return updatedRoom;
    }


    @Override
    public Room delete(int id) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomQuantity(0);  // Soft delete
            roomRepository.save(room);
            return room;
        }).orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }

    @Override
    public Room findById(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }
}
