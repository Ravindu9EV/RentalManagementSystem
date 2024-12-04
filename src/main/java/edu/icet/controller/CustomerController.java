package edu.icet.controller;

import edu.icet.model.CustomerModel;
import edu.icet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @PostMapping("/save")
    public boolean add(@RequestBody CustomerModel customerModel){
        return service.add(customerModel);
    }
    @GetMapping("/search-by-id/")
    public CustomerModel search(@RequestParam int customerId){
        return service.search(customerId);
    }

    @PutMapping("/update")
    public boolean update(CustomerModel model){
        return service.update(model);
    }
    
}
