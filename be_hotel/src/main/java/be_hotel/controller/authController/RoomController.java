package be_hotel.controller.authController;

import be_hotel.Modal.DTO.RoomDTO;
import be_hotel.Modal.entity.Room;
import be_hotel.Modal.request.RoomRequest;
import be_hotel.serive.room.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getAllRoom/{page}/{size}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> getAllRoom(@PathVariable int page, @PathVariable int size) {
        Page<Room> roomPage = roomService.getAllRoom(page, size);
        List<RoomDTO> roomDTOs = roomPage.getContent().stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roomDTOs, HttpStatus.OK);
    }
    @PostMapping("/createRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest form) {
        return new ResponseEntity<>(modelMapper.map(roomService.createRoom(form),RoomDTO.class) , HttpStatus.CREATED);
    }
    @PutMapping("/updateRoom/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateRoom(@RequestBody RoomRequest form, @PathVariable Integer id) {
        return new ResponseEntity<>(modelMapper.map(roomService.updateRoom(form, id),RoomDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/deleteRoom/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }
}
