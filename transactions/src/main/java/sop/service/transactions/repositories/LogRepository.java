package sop.service.transactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sop.service.transactions.model.Log;

import java.util.List;

//import net.guides.springboot2.springboot2jpacrudexample.model.Employee;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{
    @Query(value="SELECT * FROM Log t where t.account_source = :id",nativeQuery = true)
    List<Log> findTransectionProfileById(Long id);
}