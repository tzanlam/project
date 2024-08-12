package be_hotel.Modal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;  // Đổi tên thành room

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;  // Đổi tên thành account

    @Column(name = "checkin", nullable = false)
    private LocalDateTime checkin;

    @Column(name = "checkout", nullable = false)
    private LocalDateTime checkout;

    @Column(name = "deadline_date", nullable = false)
    private LocalDateTime deadlineDate;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;
}
