package sop.service.transactions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sop.service.transactions.model.Log;

//import net.guides.springboot2.springboot2jpacrudexample.model.Employee;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{

}