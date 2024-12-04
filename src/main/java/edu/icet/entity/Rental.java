package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentID")
    private Integer rentID;
    private String  rentalDate;
    private String returnDate;
    private String dueDate;
    private boolean fine;
    private Double totalCost;
    @OneToMany
    private List<RentalDetails> rentalDetails;
}
