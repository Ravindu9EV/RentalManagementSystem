package edu.icet.repository;

import edu.icet.entity.Rental;
import edu.icet.model.CustomerModel;
import edu.icet.model.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Integer> {
    List<Rental> findByReturnDate(String date);
    List<Rental> findByRentalDate(String date);
    List<Rental> findByDueDate(String date);


}
