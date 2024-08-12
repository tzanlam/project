package be_hotel.Modal.DTO;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private String birthDate;
    private String idCard;
    private String gender;
    private String phoneNumber;
    private String email;
    private String role;
    private String createdDate;
    private String modifiedDate;
}
