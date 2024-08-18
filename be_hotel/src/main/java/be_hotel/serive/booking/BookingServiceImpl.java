package be_hotel.serive.booking;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.entity.Booking;
import be_hotel.Modal.entity.Room;
import be_hotel.Modal.request.BookingFilterRequest;
import be_hotel.Modal.request.BookingRequest;
import be_hotel.Repository.AccountRepository;
import be_hotel.Repository.BookingRepository;
import be_hotel.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static be_hotel.utils.MethodConvert.convertDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Page<Booking> getAllBooking(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Booking updateBooking(BookingRequest bookingRequest, Integer id) {
        Booking booking = bookingRepository.findById(id).get();
        if (booking != null) {
            booking = bookingRequest.updateBooking(booking);
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

    @Transactional(readOnly = true)
    @Override
    public boolean isRoomAvailable(BookingFilterRequest bookingFilterRequest) {
        Room room = roomRepository.findById(bookingFilterRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        LocalDateTime checkinStart = convertDateTime(bookingFilterRequest.getCheckinStart());
        LocalDateTime checkinEnd = convertDateTime(bookingFilterRequest.getCheckinEnd());
        LocalDateTime checkoutStart = convertDateTime(bookingFilterRequest.getCheckoutStart());
        LocalDateTime checkoutEnd = convertDateTime(bookingFilterRequest.getCheckoutEnd());

        int bookingCount = bookingRepository.countByCheckinBetween(room, checkinStart, checkinEnd, checkoutStart, checkoutEnd);

        return bookingCount < room.getRoomQuantity();
    }
    @Override
    public Booking createBooking( BookingRequest bookingRequest) {
        Account account = accountRepository.findById(bookingRequest.getAccountId()).get();
        Room room = roomRepository.findById(bookingRequest.getRoomId()).get();
            Booking booking = bookingRequest.asBooking();
            booking.setAccount(account);
            booking.setRoom(room);
            bookingRepository.save(booking);
            return booking;
        }
}
