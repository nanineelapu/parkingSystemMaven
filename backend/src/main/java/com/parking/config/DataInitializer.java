package com.parking.config;

import com.parking.model.Slot;
import com.parking.model.VehicleType;
import com.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public void run(String... args) throws Exception {
        if(slotRepository.count() == 0) {
            for (int floor = 1; floor <= 2; floor++) {
                for (int s = 1; s <= 10; s++) {
                    Slot slot = new Slot();
                    slot.setFloorNumber(floor);
                    slot.setSlotNumber(s + ((floor - 1) * 10)); // Unique slot number e.g. 1-10, 11-20
                    slot.setIsAvailable(true);
                    slot.setSlotType(s <= 7 ? VehicleType.CAR : VehicleType.BIKE);
                    slotRepository.save(slot);
                }
            }
        }
    }
}
