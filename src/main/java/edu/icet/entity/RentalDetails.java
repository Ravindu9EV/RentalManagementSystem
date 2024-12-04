package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Reference;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalDetails {

    @EmbeddedId
    RentalDetailsKey id;

    @Column(name="rent_ID",insertable = false,updatable = false)
    private int rentID;

    @Column(name="item_ID",insertable = false,updatable = false)
    private int itemID;


    //    @Id
//    private int id;

//    @ManyToOne
//    @JoinColumn(name="rentID")
//    private Rental rental;
//
//    @ManyToOne
//    @JoinColumn(name="itemID")
//    private HardwareItem hardwareItem;

    private int qty;
    private Double totalItemCost;

}
