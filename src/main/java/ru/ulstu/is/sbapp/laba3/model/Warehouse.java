package ru.ulstu.is.sbapp.laba3.model;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "warehousename")
    private String warehousename;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "typeWarehouse_id")
    private TypeWarehouse typeWarehouse;

    public Warehouse() {
    }

    public Warehouse(String warehousename, TypeWarehouse typeWarehouse) {
        this.warehousename = warehousename;
        this.typeWarehouse = typeWarehouse;

    }

    public Long getId() {
        return id;
    }
    public String getWarehouseName(){
        return warehousename;
    }
    public void setWarehouseName(String name){
        this.warehousename = name;
    }
    public TypeWarehouse getTypeWarehouse(){
        return typeWarehouse;
    }
    public void setTypeWarehouse(TypeWarehouse typeWarehouse){
        this.typeWarehouse = typeWarehouse;
    }
    public void update(Warehouse warehouse){
        this.warehousename = warehouse.warehousename;
        this.typeWarehouse = warehouse.getTypeWarehouse();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", warehousename='" + warehousename + '\'' +
                ", typeWarehouse='" + typeWarehouse + '\'' +
                '}';
    }
}
