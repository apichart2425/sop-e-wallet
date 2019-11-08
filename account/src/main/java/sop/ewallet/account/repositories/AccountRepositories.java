package sop.ewallet.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sop.ewallet.account.model.Account;

@Repository
public interface AccountRepositories extends JpaRepository<Account, Integer> {
}
