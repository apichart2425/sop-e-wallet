package sop.ewallet.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import sop.ewallet.account.model.Account;

@Repository
public interface AccountRepositories extends JpaRepository<Account, Integer> {

}