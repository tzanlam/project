package be_hotel.controller.authController;

import be_hotel.Modal.DTO.BookingDTO;
import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.request.BookingFilterRequest;
import be_hotel.Modal.request.BookingRequest;
import be_hotel.serive.booking.BookingService;
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
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/getAllBooking/{page}/{size}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllBooking(@PathVariable int page, @PathVariable int size) {
        Page<Booking> bookingPage = bookingService.getAllBooking(page, size);
        List<BookingDTO> bookingDTOs = bookingPage.getContent().stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingDTOs, HttpStatus.OK);
    }
    @PostMapping("/createBooking")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest form) {
        return new ResponseEntity<>(modelMapper.map(bookingService.createBooking(form), BookingDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/updateBooking/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public BookingDTO updateBooking(@RequestBody BookingRequest form, @PathVariable Integer id) {
        return modelMapper.map(bookingService.updateBooking(form, id), BookingDTO.class);
    }

    @DeleteMapping("/deleteBooking/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String deleteBooking(@PathVariable Integer id) {
        return bookingService.deleteBooking(id);
    }
}
