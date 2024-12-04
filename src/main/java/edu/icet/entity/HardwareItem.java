package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HardwareItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =" itemID")
    private Integer itemID;
    private String name;
    private Integer rentalPerDay;
    private Integer finePerDay;
    private boolean availability;

}
