package sop.ewallet.transactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sop.ewallet.transactions.model.Log;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT l FROM Log l WHERE l.account_source = :source_id OR l.account_destination = :source_id")
    List<Log> findTransactionsByUser(@Param("source_id") Long sourceId);
}