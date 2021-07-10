package com.kone.manage.equipment.restcontroller;

import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.model.Equipments;
import com.kone.manage.equipment.repository.EquipmentStatusReportRepository;
import com.kone.manage.equipment.service.EquipmentStatusReportService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/equipment")
public class EquipmentStatusReportController {
  private EquipmentStatusReportRepository equipmentStatusReportRepository;
  private EquipmentStatusReportService equipmentStatusReportService;

  public EquipmentStatusReportController(final EquipmentStatusReportRepository equipmentStatusReportRepository,
                                         final EquipmentStatusReportService equipmentStatusReportService) {
    this.equipmentStatusReportRepository = equipmentStatusReportRepository;
    this.equipmentStatusReportService = equipmentStatusReportService;
  }

  @GetMapping("/search")
  Equipments searchEquipments(final @RequestParam("limit") Integer numberOfEquipments) {
    return new Equipments();
  }

  @GetMapping("/{equipmentNumber}")
  Equipment getEquipmentById(@PathVariable Long equipmentNumber) {
    return new Equipment();
  }

  @PostMapping
  Equipment newEquipment(@RequestBody Equipment equipment) {
    equipmentStatusReportService.validate(equipment);
    return equipmentStatusReportRepository.save(equipment);
  }
}
