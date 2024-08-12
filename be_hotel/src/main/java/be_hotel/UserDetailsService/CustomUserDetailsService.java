package be_hotel.UserDetailsService;

import be_hotel.Modal.entity.Account;
import be_hotel.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("Account not found");
        }
        return new User(username, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().name()));
    }
}
