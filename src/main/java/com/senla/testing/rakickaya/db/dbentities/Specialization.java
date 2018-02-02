package com.senla.testing.rakickaya.db.dbentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "specialization")
public class Specialization {

    @Id
    private long specialization_id;
    private String name;

    public long getSpecialization_id() {
        return specialization_id;
    }

    public String getName() {
        return name;
    }

    public void setSpecialization_id(long specialization_id) {
        this.specialization_id = specialization_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
