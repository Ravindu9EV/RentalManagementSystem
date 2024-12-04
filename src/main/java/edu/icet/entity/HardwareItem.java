package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HardwareItem {
    @Id
    private Integer itemID;
    private String name;
    private Integer rentalPerDay;
    private Integer finePerDay;
    private boolean availability;

}
