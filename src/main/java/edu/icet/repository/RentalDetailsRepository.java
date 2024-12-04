package edu.icet.repository;

import edu.icet.entity.RentalDetails;
import edu.icet.entity.RentalDetailsKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDetailsRepository extends JpaRepository<RentalDetails,RentalDetailsKey> {
}
