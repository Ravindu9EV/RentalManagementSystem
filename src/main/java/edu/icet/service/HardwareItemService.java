package edu.icet.service;

import edu.icet.model.HardwareItemModel;

import java.util.List;

public interface HardwareItemService {
    boolean add(HardwareItemModel hardwareItemModel);
    HardwareItemModel search(int itemId);
    HardwareItemModel update(HardwareItemModel model);
    boolean remove(int itemId);
    List<HardwareItemModel> findByAvailability(boolean availability);
    List<HardwareItemModel> getAll();
}
