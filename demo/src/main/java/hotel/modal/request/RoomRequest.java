package hotel.modal.request;

import hotel.modal.entity.Room;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class RoomRequest {
    private static final String IMAGE_DIRECTORY = "images/";
    private int bedQuantity;
    private int roomQuantity;
    private String nameRoom;
    private String description;
    private MultipartFile image;
    private double priceDay;
    private double priceNight;

    public Room asRoom() throws IOException {
        Room room = new Room();
        populateRoomFormRequest(room);
        return room;
    }

    public Room updateRoom(Room room) throws IOException {
        populateRoomFormRequest(room);
        room.setId(room.getId());
        return room;
    }


    private String saveImg(MultipartFile file) throws IOException {
        if (this.image ==null||this.image.isEmpty()){
            return null;
        }
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(IMAGE_DIRECTORY + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return filePath.toString();
    }
    private void populateRoomFormRequest(Room room) throws IOException {
        room.setBedQuantity(bedQuantity);
        room.setRoomQuantity(roomQuantity);
        room.setName(nameRoom);
        room.setDescription(description);
        room.setImage(saveImg(image));
        room.setPriceDay(priceDay);
        room.setPriceNight(priceNight);
    }
}
