package be_hotel.Modal.response;

import be_hotel.Modal.entity.Account;
import lombok.Data;

@Data
public class AuthReponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Integer id;
    private String username;
    private String email;

    public AuthReponse(String accessToken, Account user) {
        this.accessToken = accessToken;
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
