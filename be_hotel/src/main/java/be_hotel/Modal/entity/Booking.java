package be_hotel.Modal.entity;

import be_hotel.Modal.entity.constants.StatusDeleted;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Room room;  // Đổi tên thành room

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;  // Đổi tên thành account

    @Column(name = "checkin", nullable = false)
    private LocalDateTime checkin;
// now > checkin   va now < checkout
    @Column(name = "checkout", nullable = false)
    private LocalDateTime checkout;

    @Column(name = "deadline_date")
    private LocalDateTime deadlineDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusDeleted status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
}
