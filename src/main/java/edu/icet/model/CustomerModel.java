package edu.icet.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerModel {
    private Integer customerID;
    private String name;
    private String contact;
    private String city;
}
