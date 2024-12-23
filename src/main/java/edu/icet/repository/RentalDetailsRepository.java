package edu.icet.repository;

import edu.icet.entity.RentalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDetailsRepository extends JpaRepository<RentalDetails,Integer> {
}
