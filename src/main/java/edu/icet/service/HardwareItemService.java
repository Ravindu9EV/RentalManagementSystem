package edu.icet.service;

import edu.icet.model.HardwareItemModel;

import java.util.List;

public interface HardwareItemService {
    boolean add(HardwareItemModel hardwareItemModel);
    HardwareItemModel search(int itemId);
    boolean update(HardwareItemModel model);
    boolean remove(int itemId);
    List<HardwareItemModel> getAll();
}
