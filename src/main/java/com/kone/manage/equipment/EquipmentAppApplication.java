package com.kone.manage.equipment;

import com.kone.manage.equipment.model.Address;
import com.kone.manage.equipment.model.Equipment;
import com.kone.manage.equipment.model.EquipmentStatus;
import com.kone.manage.equipment.repository.EquipmentStatusReportRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class EquipmentAppApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(EquipmentAppApplication.class, args);
    populateEquipments(context);
  }

  private static void populateEquipments(final ConfigurableApplicationContext context) {
    EquipmentStatusReportRepository repository = context.getBean(EquipmentStatusReportRepository.class);

    Equipment equipment1 = new Equipment();
    equipment1.setContractEndDate(LocalDate.now());
    equipment1.setContractStartDate(LocalDate.now());
    Address address1 = new Address();
    address1.setStreetName("kone street");
    address1.setCity("espoo");
    address1.setPostalCode("100");
    address1.setCountry("Finland");
    equipment1.setAddress(address1);
    equipment1.setStatus(EquipmentStatus.RUNNING);
    repository.save(equipment1);

    Equipment equipment2 = new Equipment();
    equipment2.setContractEndDate(LocalDate.now());
    equipment2.setContractStartDate(LocalDate.now());
    Address address2 = new Address();
    address2.setStreetName("kone street 2");
    address2.setCity("espoo");
    address2.setPostalCode("100");
    address2.setCountry("Finland");
    equipment2.setAddress(address2);
    equipment2.setStatus(EquipmentStatus.STOPPED);
    repository.save(equipment2);
  }

}
