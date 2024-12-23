package edu.icet.model;

import edu.icet.entity.HardwareItem;
import edu.icet.entity.Rental;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailModel {




//    @Column(name="rent_ID",insertable = false,updatable = false)
//    private int rentID;
//
//    @Column(name="item_ID",insertable = false,updatable = false)
//    private int itemID;



        private int id;


        private Integer rentalId;


        private Integer itemId;

        private int qty;
        private Double totalItemCost;


}
