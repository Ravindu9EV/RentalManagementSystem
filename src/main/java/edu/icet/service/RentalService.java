package edu.icet.service;

import edu.icet.model.CustomerModel;
import edu.icet.model.RentalModel;

import java.util.List;

public interface RentalService {
    RentalModel add(RentalModel rentalModel);
    RentalModel search(int rentID);
    boolean update(RentalModel rentalModel);
    List<RentalModel> searchByRentalDate(String date);
    List<RentalModel> searchByReturnDate(String date);
    List<RentalModel> searchByDueDate(String date);
    boolean remove(int rentID);
    List<RentalModel> getAll();
}
