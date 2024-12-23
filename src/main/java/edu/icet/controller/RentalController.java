package edu.icet.controller;

import edu.icet.model.HardwareItemModel;
import edu.icet.model.RentalModel;
import edu.icet.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    private RentalService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public RentalModel add(@RequestBody RentalModel model){
       // System.out.println(model);
        return service.add(model);
    }
    @GetMapping("/search-by-id/")
    public RentalModel search(@RequestParam int rentId){
        return service.search(rentId);
    }

    @PutMapping("/update")
    public boolean update(RentalModel model){
        return service.update(model);
    }

    @GetMapping("/get-all")
    public List<RentalModel> getAll(){
        return service.getAll();
    }
    @GetMapping("/search-by-name/")
    public List<RentalModel> searchByDueDate(@RequestParam String date){
        return service.searchByDueDate(date);
    }
}
