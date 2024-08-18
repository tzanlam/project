package be_hotel.controller.unauthentic;

import be_hotel.Modal.request.BookingFilterRequest;
import be_hotel.Modal.request.LoginRequest;
import be_hotel.serive.account.AccountService;
import be_hotel.serive.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UnAuthController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private BookingService bookingService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest form) {
        return new ResponseEntity<>(accountService.login(form), HttpStatus.OK);
    }
    @PostMapping("/checkAvailableRoom")
    public ResponseEntity<?> checkAvailableRoom(@RequestBody BookingFilterRequest bookingFilterRequest) {
        if (bookingService.isRoomAvailable(bookingFilterRequest)){
            return new ResponseEntity<>("Room Available", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Room Not Available", HttpStatus.OK);
        }
    }
}
