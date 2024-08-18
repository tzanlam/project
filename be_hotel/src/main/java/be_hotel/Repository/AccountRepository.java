package be_hotel.Repository;

import be_hotel.Modal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);

    boolean existsByUsername(String s);

    boolean existsByEmail(String s);
}
