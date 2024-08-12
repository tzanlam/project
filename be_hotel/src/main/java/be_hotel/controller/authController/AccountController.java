package be_hotel.controller.authController;

import be_hotel.Modal.request.AccountRequest;
import be_hotel.serive.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    @PreAuthorize("hasRole('ADMIN, USER')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest form) {
        return new ResponseEntity<>(accountService.createAccount(form).getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/getAllAccount/{page}/{size}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAccount(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(accountService.getAccount(page, size), HttpStatus.OK);
    }

    @PutMapping("updateAccount/{id}")
    @PreAuthorize("hasRole('ADMIN, USER')")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest form, @PathVariable Integer id) {
        return new ResponseEntity<>(accountService.updateAccount(id, form), HttpStatus.OK);
    }

    @DeleteMapping("deleteAccount/{id}")
    @PreAuthorize("hasRole('ADMIN, USER')")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }
}
