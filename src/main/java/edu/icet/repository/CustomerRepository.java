package edu.icet.repository;

import edu.icet.entity.Customer;
import edu.icet.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> searchByName(String name);
}
