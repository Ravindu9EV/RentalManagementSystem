package edu.icet.model;

import edu.icet.entity.RentalDetails;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HardwareItemModel {
    private Integer itemID;
    private String name;
    private Integer rentalPerDay;
    private Integer finePerDay;
    private boolean availability;
    //private List<RentalModel> rentalModelList;
    //private List<RentalDetails> rentalDetails;
}
