package edu.icet.repository;

import edu.icet.entity.HardwareItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HardwareItemRepository extends JpaRepository<HardwareItem,Integer> {
    List<HardwareItem> findByAvailability(boolean available);
}
