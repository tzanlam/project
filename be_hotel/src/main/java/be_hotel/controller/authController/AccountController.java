package be_hotel.controller.authController;

import be_hotel.Modal.DTO.AccountDTO;
import be_hotel.Modal.entity.Account;
import be_hotel.Modal.request.AccountRequest;
import be_hotel.serive.account.AccountService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createAccount")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest form) {
        return new ResponseEntity<>(modelMapper.map(accountService.createAccount(form), AccountDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/getAllAccount/{page}/{size}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllAccount(@PathVariable int page, @PathVariable int size) {
        Page<Account> accountPage = accountService.getAccount(page, size);
        List<AccountDTO> accountDTOs = accountPage.getContent().stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
    }

    @PutMapping("updateAccount/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> updateAccount(@RequestBody AccountRequest form, @PathVariable Integer id) {
        return new ResponseEntity<>(modelMapper.map(accountService.updateAccount(id, form),AccountDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("deleteAccount/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }
}
