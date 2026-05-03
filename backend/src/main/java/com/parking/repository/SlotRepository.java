package com.parking.repository;

import com.parking.model.Slot;
import com.parking.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    List<Slot> findByIsAvailableAndSlotTypeOrderByFloorNumberAscSlotNumberAsc(Boolean isAvailable, VehicleType slotType);
    long countByIsAvailable(Boolean isAvailable);
}
