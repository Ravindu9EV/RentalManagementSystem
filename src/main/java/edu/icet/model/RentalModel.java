package edu.icet.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalModel {
    private Integer rentID;
    private String  rentalDate;
    private String returnDate;
    private String dueDate;
    private boolean fine;
    private Double totalCost;
}
