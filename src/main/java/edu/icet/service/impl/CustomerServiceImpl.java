package edu.icet.service.impl;

import edu.icet.entity.Customer;
import edu.icet.model.CustomerModel;
import edu.icet.repository.CustomerRepository;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper mapper;

    @Override
    public boolean add(CustomerModel customerModel) {

        try {
            if(customerModel.getCustomerID() != null | customerModel.getName() != null | customerModel.getCity() != null | customerModel.getContact() != null) {
                return repository.save(mapper.map(customerModel, Customer.class))!=null;
            } else {
                return false;
            }
        }catch (Exception e){
            log.info(e.toString());
        }
        return false;
    }

    @Override
    public CustomerModel search(int customerID) {
        try{
            return  mapper.map(repository.findById(customerID),CustomerModel.class);
        }catch (Exception e){
            log.info(e.toString());
        }
        return null;

    }

    @Override
    public boolean update(CustomerModel customerModel) {
        try{
            return mapper.map(repository.findById(customerModel.getCustomerID()),CustomerModel.class)!=null;

        }catch (Exception e){
            log.info(e.toString());
        }
        return false;
    }

    @Override
    public List<CustomerModel> searchByName(String name) {
        List<CustomerModel> customers=new ArrayList<>();
        try {

           for(Customer customer:repository.searchByName(name)){
               if(customer!=null){
                   customers.add(mapper.map(customer,CustomerModel.class));
               }
            }

        }catch (Exception e){
            log.info(e.toString());
        }
        return customers;
    }

    @Override
    public boolean remove(int customerID) {
        if(customerID>=1){
            repository.deleteById(customerID);
            return true;
        };
        return false;
    }

    @Override
    public List<CustomerModel> getAll() {
        List<CustomerModel> customers=new ArrayList<>();
        for (Customer customer:repository.findAll()){
            if(customer!=null){
                customers.add(mapper.map(customer,CustomerModel.class));
            }
        }
        return customers;
    }
}
