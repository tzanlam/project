package be_hotel.serive.booking;

import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.request.BookingRequest;
import org.springframework.data.domain.Page;

public interface BookingService {
    public Booking createBooking(BookingRequest bookingRequest);

    public Page<Booking> getAllBooking(int page, int size);

    public Booking updateBooking(BookingRequest bookingRequest, Integer id);

    public String deleteBooking(Integer id);
}
