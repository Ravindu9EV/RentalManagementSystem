package edu.icet.controller;

import edu.icet.model.CustomerModel;
import edu.icet.model.HardwareItemModel;
import edu.icet.service.HardwareItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
@CrossOrigin
public class HardwareItemController {
    @Autowired
    private HardwareItemService service;
    @PostMapping("/save")
    public boolean add(@RequestBody HardwareItemModel model){
        System.out.println(model);
        return service.add(model);
    }
    @GetMapping("/search-by-id/")
    public HardwareItemModel search(@RequestParam int itemId){
        return service.search(itemId);
    }

    @PutMapping("/update")
    public boolean update(HardwareItemModel model){
        return service.update(model);
    }

    @GetMapping("/get-all")
    public List<HardwareItemModel> getAll(){
        return service.getAll();
    }
    @GetMapping("/search-by-name/")
    public List<HardwareItemModel> searchByAvailability(@RequestParam boolean available){
        return service.findByAvailability(available);
    }
}
