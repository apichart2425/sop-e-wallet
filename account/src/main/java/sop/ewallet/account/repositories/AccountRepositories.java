package sop.ewallet.account.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import sop.ewallet.account.model.Account;

@Repository
public interface AccountRepositories extends JpaRepository<Account, Integer> {

  @Query("SELECT a FROM Account a WHERE a.userId = :id")
  Optional<Account> findAccountByUserId(@Param("id") Long userId);
}
