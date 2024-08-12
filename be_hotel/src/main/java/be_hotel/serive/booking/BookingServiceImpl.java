package be_hotel.serive.booking;

import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.request.BookingRequest;
import be_hotel.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public Booking createBooking(BookingRequest bookingRequest) {
        Booking booking = bookingRequest.asBooking();
        bookingRepository.save(booking);
        return booking;
    }

    @Override
    public Page<Booking> getAllBooking(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Booking updateBooking(BookingRequest bookingRequest, Integer id) {
        Booking booking = bookingRepository.findById(id).get();
        if (booking != null) {
            booking = bookingRequest.asBooking();
            bookingRepository.save(booking);
            return booking;
        }
        return null;
    }

    @Override
    public String deleteBooking(Integer id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return "Delete Success";
        }
        return "Delete Fail";
    }

    @Autowired
    private BookingRepository bookingRepository;
}
