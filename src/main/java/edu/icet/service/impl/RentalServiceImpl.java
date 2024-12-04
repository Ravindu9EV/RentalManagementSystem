package edu.icet.service.impl;

import edu.icet.entity.Rental;
import edu.icet.model.CustomerModel;
import edu.icet.model.RentalModel;
import edu.icet.repository.RentalRepository;
import edu.icet.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository repository;
    private final ModelMapper mapper;
    @Override
    public boolean add(RentalModel rentalModel) {
        try {
            if(rentalModel.getRentID()!=null  && rentalModel.getRentalDate()!=null && rentalModel.getReturnDate()!=null && rentalModel.getDueDate()!=null && rentalModel.getTotalCost()!=null){

                return repository.save(mapper.map(rentalModel, Rental.class))!=null;

            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public RentalModel search(int rentID) {
        if(rentID>=1){
            return mapper.map(repository.findById(rentID), RentalModel.class);
        }
        return null;
    }

    @Override
    public boolean update(RentalModel rentalModel) {
        return false;
    }

    @Override
    public List<RentalModel> searchByRentalDate(String date) {
        List<RentalModel> rentalModels=new ArrayList<>();
        for(RentalModel rental:getAll()){
            if(rental.getRentalDate().equals(date)){
                rentalModels.add(rental);
            }
        }
        return rentalModels;
    }

    @Override
    public List<RentalModel> searchByReturnDate(String date) {
        List<RentalModel> rentalModels=new ArrayList<>();
        for(RentalModel rental:getAll()){
            if(rental.getReturnDate().equals(date)){
                rentalModels.add(rental);
            }
        }
        return rentalModels;
    }

    @Override
    public List<RentalModel> searchByDueDate(String date) {
        List<RentalModel> rentalModels=new ArrayList<>();
        for(RentalModel rental:getAll()){
            if(rental.getDueDate().equals(date)){
                rentalModels.add(rental);
            }
        }
        return rentalModels;
    }

    @Override
    public boolean remove(int rentID) {
        if(rentID>0){
            repository.deleteById(rentID);
            return true;
        }else {
            return false;
        }
        }

    @Override
    public List<RentalModel> getAll() {
        List<RentalModel> rentalModels=new ArrayList<>();
        for (Rental rental: repository.findAll()){
            if(rental!=null){
                rentalModels.add(mapper.map(rental, RentalModel.class));
            }
        }
        return rentalModels;
    }
}
