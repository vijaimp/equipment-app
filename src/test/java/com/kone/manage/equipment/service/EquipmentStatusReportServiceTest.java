package com.kone.manage.equipment.service;

import com.kone.manage.equipment.exception.DuplicateEquipmentException;
import com.kone.manage.equipment.model.Address;
import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.model.EquipmentStatus;
import com.kone.manage.equipment.repository.EquipmentStatusReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentStatusReportServiceTest {
    @Mock
    private EquipmentStatusReportRepository equipmentStatusReportRepository;

  @Test
  public void testValidation() {
    when(equipmentStatusReportRepository.findAll()).thenReturn(Arrays.asList(mockEquipment("test")));
      EquipmentStatusReportService service = new EquipmentStatusReportService(equipmentStatusReportRepository);
      service.validate(mockEquipment("amDifferent"));
  }

    @Test
    public void testValidationWithDuplicateDate() {
      when(equipmentStatusReportRepository.findAll()).thenReturn(Arrays.asList(mockEquipment("test")));
      Assertions.assertThrows(DuplicateEquipmentException.class, () -> {
        EquipmentStatusReportService service = new EquipmentStatusReportService(equipmentStatusReportRepository);
        service.validate(mockEquipment("test"));
      });
    }

    private Equipment mockEquipment(String additionalRandomText) {
      Equipment equipment = new Equipment();
      equipment.setContractEndDate(LocalDate.now());
      equipment.setContractStartDate(LocalDate.now());
      Address address = new Address();
      address.setStreetName("kone street" + additionalRandomText);
      address.setCity("espoo");
      address.setPostalCode("100");
      address.setCountry("Finland");
      equipment.setAddress(address);
      equipment.setStatus(EquipmentStatus.RUNNING);
      return equipment;
    }

}
