package com.kone.manage.equipment.restcontroller;

import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.model.Equipments;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/equipment")
public class EquipmentStatusReportController {

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
    return new Equipment();
  }
}
