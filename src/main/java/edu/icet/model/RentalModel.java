package edu.icet.model;


import edu.icet.entity.RentalDetails;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    //private List<HardwareItemModel> hardwareItemModels;
    private List<RentalDetailModel> rentalDetails;
}
