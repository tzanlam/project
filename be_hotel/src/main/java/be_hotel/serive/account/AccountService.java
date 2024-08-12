package be_hotel.serive.account;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.request.AccountRequest;
import be_hotel.Modal.request.LoginRequest;
import be_hotel.Modal.response.AuthReponse;
import org.springframework.data.domain.Page;

public interface AccountService {
    public Account createAccount(AccountRequest accountRequest);

    public Page getAccount(int page, int size);

    public Account updateAccount(int id,AccountRequest accountRequest);

    public String deleteAccount(Integer id);

    public AuthReponse login(LoginRequest loginRequest);
}
