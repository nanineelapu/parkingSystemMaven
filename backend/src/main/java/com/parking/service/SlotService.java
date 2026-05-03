package com.parking.service;

import com.parking.exception.NoSlotAvailableException;
import com.parking.model.Slot;
import com.parking.model.VehicleType;
import com.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {
    @Autowired
    private SlotRepository slotRepository;

    public Slot findFirstAvailableSlotSortedByFloorAndNumber(VehicleType type) {
        List<Slot> availableSlots = slotRepository.findByIsAvailableAndSlotTypeOrderByFloorNumberAscSlotNumberAsc(true, type);
        if (availableSlots.isEmpty()) {
            throw new NoSlotAvailableException("No available slot for vehicle type: " + type);
        }
        return availableSlots.get(0);
    }

    public Slot allocateSlot(Slot slot) {
        slot.setIsAvailable(false);
        return slotRepository.save(slot);
    }

    public void freeSlot(Long slotId) {
        Slot slot = slotRepository.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
        slot.setIsAvailable(true);
        slotRepository.save(slot);
    }

    public long getTotalSlots() {
        return slotRepository.count();
    }

    public long getFreeSlots() {
        return slotRepository.countByIsAvailable(true);
    }

    public long getOccupiedSlots() {
        return slotRepository.countByIsAvailable(false);
    }
}
