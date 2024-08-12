package be_hotel.Modal.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
