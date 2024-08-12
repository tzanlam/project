package be_hotel.Modal.entity;

import be_hotel.Modal.entity.constants.StatusRoom;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "room_quantity", nullable = false)
    private Integer roomQuantity;

    @Column(name = "bed_quantity", nullable = false)
    private Integer bedQuantity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusRoom status;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Booking> bookingList;
}
