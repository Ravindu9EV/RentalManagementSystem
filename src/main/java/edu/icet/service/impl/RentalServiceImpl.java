package edu.icet.service.impl;

import edu.icet.entity.Rental;
import edu.icet.entity.RentalDetails;
import edu.icet.entity.RentalDetailsKey;
import edu.icet.model.CustomerModel;
import edu.icet.model.RentalModel;
import edu.icet.repository.RentalDetailsRepository;
import edu.icet.repository.RentalRepository;
import edu.icet.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository repository;
    private final RentalDetailsRepository rentalDetailsRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final ModelMapper mapper;
    @Override


    public boolean add(RentalModel rentalModel) {
        DefaultTransactionDefinition deft=new DefaultTransactionDefinition();
        TransactionStatus status=platformTransactionManager.getTransaction(deft);
        try {
            if(rentalModel.getRentalDate()!=null && rentalModel.getReturnDate()!=null && rentalModel.getDueDate()!=null && rentalModel.getTotalCost()!=null){
                Rental rental=mapper.map(rentalModel, Rental.class);

                List<RentalDetails> details=new ArrayList<>();
                boolean isDetailSaved=false;
                boolean isRentalSaved=false;
                rental=repository.save(rental);
                System.out.println(rental);
                isRentalSaved=true;
                for(RentalDetails detail:rental.getRentalDetails()){

                    if(detail!=null){
                        detail.setId(new RentalDetailsKey(rental.getRentID(),detail.getItemID()));
                        System.out.println(rental.getRentID());
                        detail.setRentID(rental.getRentID());
                        System.out.println(detail.getRentID());
                        detail=rentalDetailsRepository.save(detail);
                        details.add(detail);
                        isDetailSaved=true;
                    }
                }
                if(isDetailSaved & isRentalSaved){
                    rental.setRentalDetails(details);

                    platformTransactionManager.commit(status);
                    return true;
                }else {
                    return false;}


            }else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
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
        /*for(RentalModel rental:getAll()){
            if(rental.getDueDate().equals(date)){
                rentalModels.add(rental);
            }
        }*/
        for(Rental rental:repository.findByDueDate(date)){
            if(rental!=null){
                rentalModels.add(mapper.map(rental, RentalModel.class));
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
