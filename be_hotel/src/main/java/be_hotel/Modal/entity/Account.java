package be_hotel.Modal.entity;

import be_hotel.Modal.entity.constants.Gender;
import be_hotel.Modal.entity.constants.Role;
import be_hotel.Modal.entity.constants.StatusAccount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`account`")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, unique = true)
    private Integer id;

    @Column(name = "`username`", nullable = false, unique = true)
    private String username;

    @Column(name = "`password`", nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "id_card", nullable = false)
    private String idCard;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAccount status;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Booking> bookings;
}
