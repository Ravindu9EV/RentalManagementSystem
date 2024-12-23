package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalDetails {



//    @Column(name="rent_ID",insertable = false,updatable = false)
//    private int rentID;
//
//    @Column(name="item_ID",insertable = false,updatable = false)
//    private int itemID;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="rent_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name="item_id")
    private HardwareItem hardwareItem;

    private int qty;
    private Double totalItemCost;

}
