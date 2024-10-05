package hotel.controller;

import hotel.modal.entity.Booking;
import hotel.modal.entity.Room;
import hotel.modal.reponse.DTO.BookingDTO;
import hotel.modal.reponse.DTO.RoomDTO;
import hotel.modal.request.BookingRequest;
import hotel.modal.request.LoginRequest;
import hotel.service.booking.BookingService;
import hotel.service.global.AuthService;
import hotel.service.room.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/all")
public class AllController {
    @Autowired
    private AuthService authService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
    @GetMapping("/findRooms/{page}")
    public ResponseEntity<?> findAllRoom(@PathVariable int page) {
        Page<Room> rooms = roomService.findAll(page);

        // Sử dụng ModelMapper để chuyển từ Room sang RoomDTO
        List<RoomDTO> roomDTOS = rooms.getContent().stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(roomDTOS, HttpStatus.OK);
    }

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        return new ResponseEntity<>(modelMapper.map(bookingService.create(request), BookingDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/findRoomById/{id}")
    public ResponseEntity<?> findRoomById(@PathVariable int id) {
        return new ResponseEntity<>(modelMapper.map(roomService.findById(id),RoomDTO.class), HttpStatus.OK);
    }
}
