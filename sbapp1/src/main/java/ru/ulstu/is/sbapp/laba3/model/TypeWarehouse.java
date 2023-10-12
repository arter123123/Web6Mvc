package ru.ulstu.is.sbapp.laba3.model;

import javax.persistence.*;
import java.util.*;
import java.util.Objects;

@Entity
@Table(name = "typewarehouse")
public class TypeWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "typewarehousename")
    private String typewarehousename;

    public TypeWarehouse(){
    }

    public TypeWarehouse(String typewarehousename) {
        this.typewarehousename = typewarehousename;
    }
    public Long getId() {
        return id;
    }
    public String getTypeWarehouseName() {
        return typewarehousename;
    }
    public void setTypeWarehouseName(String name) {
        this.typewarehousename = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeWarehouse typeWarehouse = (TypeWarehouse) o;
        return Objects.equals(id, typeWarehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TypeWarehouse{" +
                "id=" + id +
                ", typewarehousename='" + typewarehousename + '\'' +
                '}';
    }
}
