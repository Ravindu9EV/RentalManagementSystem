package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Setter

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
@ToString
public class HardwareItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer itemID;
    private String name;
    private Integer rentalPerDay;
    private Integer finePerDay;
    private boolean availability;

//    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinTable(name="rental_details",joinColumns =  @JoinColumn(name="item_id"),
//    inverseJoinColumns = @JoinColumn(name="rent_id"))
//    private List<Rental> rentals;

    @OneToMany(mappedBy ="hardwareItem" )
    @ToString.Exclude
    private List<RentalDetails> rentalDetails;
}