package com.kone.manage.equipment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private @Id
    @GeneratedValue
    Long equipmentNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private EquipmentStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass())
            return false;
        Equipment that = (Equipment) o;
        return address.equals(that.address) &&
                contractStartDate.equals(that.contractStartDate) &&
                contractEndDate.equals(that.contractEndDate) &&
                status.equals(that.status);
    }
}
