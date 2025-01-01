package edu.icet.service.impl;

import edu.icet.entity.HardwareItem;
import edu.icet.entity.Rental;

import edu.icet.entity.RentalDetails;
import edu.icet.model.CustomerModel;
import edu.icet.model.HardwareItemModel;
import edu.icet.model.RentalDetailModel;
import edu.icet.model.RentalModel;

import edu.icet.repository.HardwareItemRepository;
import edu.icet.repository.RentalDetailsRepository;
import edu.icet.repository.RentalRepository;
import edu.icet.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.RollbackOn;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private static final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);
    private final RentalRepository repository;
    private final RentalDetailsRepository rentalDetailsRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final ModelMapper mapper;
    private final HardwareItemRepository hardwareItemRepository;
    private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public RentalModel add(RentalModel rentalModel) {
//        if(rentalModel!=null){
//            repository.save(mapper.map(rentalModel, Rental.class));
//            return true;
//        }
//        return false;
        DefaultTransactionDefinition deft=new DefaultTransactionDefinition();
        TransactionStatus status=platformTransactionManager.getTransaction(deft);
        Session session=null;
        Transaction transaction=null;

        try{
            session=sessionFactory.openSession();
            transaction=session.beginTransaction();

            //List<RentalDetails> rentalDetailsList=new ArrayList<>();
            Rental rental=mapper.map(rentalModel, Rental.class);
           rental= repository.save(rental);
            System.out.println(rental);
            List<RentalDetails> rentalDetails=new ArrayList<>();
            int count=0;
            List<RentalDetails> list=add(rentalModel.getRentalDetails());
            for(RentalDetails detail:list){
               // Optional<HardwareItem> item=hardwareItemRepository.findById(detail.getHardwareItem().getItemID());
                if (detail!=null& detail.getHardwareItem()!=null){
                    RentalDetails savedDetail=mapper.map(detail,RentalDetails.class);
                    savedDetail.setRental(rental);
                    List<RentalDetails> details=new ArrayList<>();

                    System.out.println("saved: "+savedDetail);
                           //savedDetail=rentalDetailsRepository.save(savedDetail);
                          // savedDetail= (RentalDetails) session.save(savedDetail);

                    //session.save(savedDetail);
                    savedDetail=rentalDetailsRepository.save(savedDetail);
                    rentalDetails.add(savedDetail);
                    System.out.println(savedDetail);
                    System.out.println();

                    savedDetail=null;
                }
            }
            rental.setRentalDetails(rentalDetails);
            if(rentalDetails.size()==rentalModel.getRentalDetails().size()){

                session.update(rental);
//                platformTransactionManager.commit(status);
                transaction.commit();
                return mapper.map(rental,RentalModel.class);
            }





        }catch (Exception e){
            log.info(e.toString());
            //platformTransactionManager.rollback(status);
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }


    private List<RentalDetails> add(List<RentalDetailModel> details){
        List<RentalDetails> rentalDetails=new ArrayList<>();
        for(RentalDetailModel model:details) {

            if (model != null) {
                Optional<HardwareItem> item=hardwareItemRepository.findById(model.getItemId());
                RentalDetails rentalDetail=mapper.map(model,RentalDetails.class);
                System.out.println(item);
                if(item.get().isAvailability()){
                    rentalDetail.setHardwareItem(item.get());
                    System.out.println(rentalDetail);
                    rentalDetails.add(rentalDetail);
                }
            }
        }
        for (RentalDetails r:rentalDetails){
            System.out.println("d: "+r);
        }
        return rentalDetails;
    }
//    @Override
//
//
//    public boolean add(RentalModel rentalModel) {
//        DefaultTransactionDefinition deft=new DefaultTransactionDefinition();
//        TransactionStatus status=platformTransactionManager.getTransaction(deft);
//        try {
//            if(rentalModel.getRentalDate()!=null && rentalModel.getReturnDate()!=null && rentalModel.getDueDate()!=null && rentalModel.getTotalCost()!=null){
//                Rental rental=mapper.map(rentalModel, Rental.class);
//
//                List<RentalDetails> details=new ArrayList<>();
//                boolean isDetailSaved=false;
//                boolean isRentalSaved=false;
//                rental=repository.save(rental);
//                System.out.println(rental);
//                isRentalSaved=true;
//                for(RentalDetails detail:rental.getRentalDetails()){
//
//                    if(detail!=null){
//                        detail.setId(new RentalDetailsKey(rental.getRentID(),detail.getItemID()));
//                        System.out.println(rental.getRentID());
//                        detail.setRentID(rental.getRentID());
//                        System.out.println(detail.getRentID());
//                        detail=rentalDetailsRepository.save(detail);
//                        details.add(detail);
//                        isDetailSaved=true;
//                    }
//                }
//                if(isDetailSaved & isRentalSaved){
//                    rental.setRentalDetails(details);
//
//                    platformTransactionManager.commit(status);
//                    return true;
//                }else {
//                    return false;}
//
//
//            }else {
//                return false;
//            }
//        }catch (Exception e){
//            System.out.println(e);
//            return false;
//        }
//
//    }

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
