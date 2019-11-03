package sop.ewallet.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sop.ewallet.account.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
