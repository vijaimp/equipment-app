package com.kone.manage.equipment.service;

import com.kone.manage.equipment.exception.DuplicateEquipmentException;
import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.repository.EquipmentStatusReportRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentStatusReportService {
    private EquipmentStatusReportRepository equipmentStatusReportRepository;

    public EquipmentStatusReportService(final EquipmentStatusReportRepository equipmentStatusReportRepository) {
        this.equipmentStatusReportRepository = equipmentStatusReportRepository;
    }

  /**
   * validate incoming equipment and return if there is duplicate
   * @param inputEquipment
   */
  public void validate(Equipment inputEquipment) {
        equipmentStatusReportRepository.findAll().forEach(equipment -> {
            if (inputEquipment.equals(equipment)) {
                throw new DuplicateEquipmentException("Duplicate equipment");
            }
        });
    }

}
