package be_hotel.serive.account;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.request.AccountRequest;
import be_hotel.Modal.request.LoginRequest;
import be_hotel.Modal.response.AuthReponse;
import be_hotel.Repository.AccountRepository;
import be_hotel.configs.Jwt.JwtTokenUltil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JwtTokenUltil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public Account createAccount(AccountRequest accountRequest) {
        Account account = accountRequest.asAccount();
        accountRepository.save(account);
        return account;
    }

    @Override
    public Page getAccount(int page, int size) {
        return accountRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Account updateAccount(int id, AccountRequest accountRequest) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account != null) {
            accountRequest.updateAcccount(account);
            accountRepository.save(account);
            return account;
        }
        return null;
    }


    @Override
    public String deleteAccount(Integer id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return "Delete Success";
        }
        return "Delete Fail";
    }

    @Override
    public AuthReponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder .getContext().setAuthentication(authentication);
        Account account = accountRepository.findByUsername(loginRequest.getUsername());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        AuthReponse authReponse = new AuthReponse(token, account);
        return authReponse;
    }
}
