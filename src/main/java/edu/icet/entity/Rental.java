package edu.icet.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Rental {
    private Integer rentID;
    private String  rentalDate;
    private String returnDate;
    private String dueDate;
    private boolean fine;
    private Double totalCost;
}
