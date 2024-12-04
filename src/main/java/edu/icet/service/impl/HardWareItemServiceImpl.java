package edu.icet.service.impl;

import edu.icet.entity.HardwareItem;
import edu.icet.model.HardwareItemModel;
import edu.icet.repository.HardwareRepository;
import edu.icet.service.HardwareItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HardWareItemServiceImpl implements HardwareItemService {
    private final HardwareRepository repository;
    private final ModelMapper mapper;
    @Override
    public boolean add(HardwareItemModel hardwareItemModel) {
        if(hardwareItemModel.getItemID()!=null | hardwareItemModel.getName()!=null){

            return repository.save(mapper.map(hardwareItemModel, HardwareItem.class))!=null;

        }else{
            return false;
        }
    }

    @Override
    public HardwareItemModel search(int itemId) {
        if(itemId>0){
            HardwareItem entity=repository.findById(itemId).get();

            return mapper.map(entity,HardwareItemModel.class);
        }
        return null;
    }

    @Override
    public boolean update(HardwareItemModel model) {
        try{
            HardwareItem entity=mapper.map(model, HardwareItem.class);
            repository.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean remove(int itemId) {
        if(itemId>0){
            repository.deleteById(itemId);
            return true;
        }
        return false;
    }

    @Override
    public List<HardwareItemModel> findByAvailability(boolean availability) {
        List<HardwareItemModel> models=new ArrayList<>();
        for(HardwareItem entity: repository.findAll()){
            if(entity.isAvailability()){
                models.add(mapper.map(entity, HardwareItemModel.class));
            }
        }
        return models;
    }

    @Override
    public List<HardwareItemModel> getAll() {
        List<HardwareItemModel> models=new ArrayList<>();
        for(HardwareItem entity: repository.findAll()){
            if(entity!=null){
                models.add(mapper.map(entity, HardwareItemModel.class));
            }
        }
        return models;
    }
}
