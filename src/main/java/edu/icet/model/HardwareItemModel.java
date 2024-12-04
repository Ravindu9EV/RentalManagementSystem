package edu.icet.model;

import lombok.*;

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
}
