package be_hotel.controller.authController;

import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.request.BookingRequest;
import be_hotel.serive.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping("/getAllBooking/{page}/{size}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Booking> getAllBooking(@PathVariable int page, @PathVariable int size) {
        return bookingService.getAllBooking(page, size);
    }

    @PostMapping("/createBooking")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    public Booking createBooking(@RequestBody BookingRequest form) {
        return bookingService.createBooking(form);
    }

    @PutMapping("/updateBooking/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    public Booking updateBooking(@RequestBody BookingRequest form, @PathVariable Integer id) {
        return bookingService.updateBooking(form, id);
    }

    @DeleteMapping("/deleteBooking/{id}")
    @PreAuthorize("hasAuthority('ADMIN, USER')")
    public String deleteBooking(@PathVariable Integer id) {
        return bookingService.deleteBooking(id);
    }
}
