package be_hotel.serive.booking;

import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.entity.Room;
import be_hotel.Modal.request.BookingFilterRequest;
import be_hotel.Modal.request.BookingRequest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface BookingService {
    public Page<Booking> getAllBooking(int page, int size);

    public Booking updateBooking(BookingRequest bookingRequest, Integer id);

    public String deleteBooking(Integer id);

    public boolean isRoomAvailable(BookingFilterRequest bookingFilterRequest);


    public Booking createBooking(BookingRequest bookingRequest);
}
