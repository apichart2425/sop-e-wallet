package sop.service.transactions.controller;

//https://www.javaguides.net/2019/01/springboot-postgresql-jpa-hibernate-crud-restful-api-tutorial.html

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sop.service.transactions.method.TransactionMethod;
import sop.service.transactions.model.*;
import sop.service.transactions.repositories.LogRepository;
import sop.service.transactions.repositories.ResourceNotFoundException;

//import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
//import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
//import net.guides.springboot2.springboot2jpacrudexample.repository.EmployeeRepository;

@RestController
@RequestMapping("/service/transaction")
public class TransactionsController {
    @Autowired
    private LogRepository logRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private TransactionMethod transactionMethod = new TransactionMethod() ;

    @GetMapping("/log")
    public List<Log> getAllLog() {
        return logRepository.findAll();
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<Log> getEmployeeById(@PathVariable(value = "id") long log_id)
            throws ResourceNotFoundException {
        Log log_data = logRepository.findById(log_id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found for this id :: " + log_id));
        return ResponseEntity.ok().body(log_data);
    }

    @PostMapping("/withdraw")
    public ActionTransaction withdraw(@Valid @RequestBody ActionTransaction obj) {
        return  transactionMethod.withdraw(obj);
//        return logRepository.save(log_witdraw);
    }

    @PostMapping("/deposit")
    public ActionTransaction deposit(@Valid @RequestBody ActionTransaction obj) throws IOException {
        return  transactionMethod.deposit(obj);
    }

    @PostMapping("/transfer")
    public AccountWallet transfer(@Valid @RequestBody AccountWallet obj) throws IOException {
        return  transactionMethod.transfer(obj);
    }


    @GetMapping("/test2")
    public MapRate test2(){
        String exchangeUrl = "http://localhost:3000/services/exchange?base="+ "EUR";
        MapRate response_currency = restTemplate.getForObject(exchangeUrl, MapRate.class);
        return response_currency;
    }
}


//    @DeleteMapping("/employees/{id}")
//    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }


//    @PutMapping("/deposit")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
//                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employee.setEmailId(employeeDetails.getEmailId());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setFirstName(employeeDetails.getFirstName());
//        final Employee updatedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }

//    @PutMapping("/transfer")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
//                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//
//        employee.setEmailId(employeeDetails.getEmailId());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setFirstName(employeeDetails.getFirstName());
//        final Employee updatedEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updatedEmployee);
//    }

