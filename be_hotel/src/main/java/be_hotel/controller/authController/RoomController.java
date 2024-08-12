package be_hotel.controller.authController;

import be_hotel.Modal.request.RoomRequest;
import be_hotel.serive.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @GetMapping("/getAllRoom/{page}/{size}")
    @PreAuthorize("hasRole('ADMIN, USER')")
    public ResponseEntity<?> getAllRoom(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(roomService.getAllRoom(page, size), HttpStatus.OK);
    }
    @PostMapping("/createRoom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest form) {
        return new ResponseEntity<>(roomService.createRoom(form), HttpStatus.CREATED);
    }
    @PutMapping("/updateRoom/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRoom(@RequestBody RoomRequest form, @PathVariable Integer id) {
        return new ResponseEntity<>(roomService.updateRoom(form, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteRoom/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id) {
        return new ResponseEntity<>(roomService.deleteRoom(id), HttpStatus.OK);
    }
}
