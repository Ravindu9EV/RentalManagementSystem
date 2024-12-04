package edu.icet.service;

import edu.icet.model.CustomerModel;

import java.util.List;

public interface CustomerService {
    boolean add(CustomerModel customerModel);
    CustomerModel search(int customerID);
    boolean update(CustomerModel customerModel);
    List<CustomerModel> searchByName(String name);
    boolean remove(int customerID);
    List<CustomerModel> getAll();

}
