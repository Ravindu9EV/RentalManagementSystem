package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer rentID;
    private String  rentalDate;
    private String returnDate;
    private String dueDate;
    private boolean fine;
    private Double totalCost;
//    @ManyToMany(mappedBy = "rentals")
//    private List<HardwareItem> hardwareItems;
    @OneToMany(mappedBy = "rental")
    @ToString.Exclude
    private List<RentalDetails> rentalDetails;
}
