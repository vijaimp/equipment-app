package com.kone.manage.equipment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private @Id
    @GeneratedValue
    Long id;
    private String streetName;
    private String city;
    private String postalCode;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass())
            return false;
        Address that = (Address) o;
        return streetName.equals(that.streetName) &&
                city.equals(that.city) &&
                postalCode.equals(that.postalCode) &&
                country.equals(that.country);
    }
}
