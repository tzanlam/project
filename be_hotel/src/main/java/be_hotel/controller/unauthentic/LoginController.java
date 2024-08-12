package be_hotel.controller.unauthentic;

import be_hotel.Modal.request.LoginRequest;
import be_hotel.serive.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest form) {
        return new ResponseEntity<>(accountService.login(form), HttpStatus.OK);
    }

}
