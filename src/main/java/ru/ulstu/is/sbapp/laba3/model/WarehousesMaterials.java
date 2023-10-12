package ru.ulstu.is.sbapp.laba3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warehousesmaterials")
public class WarehousesMaterials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material material;
    @Column
    private Integer amount;
    public WarehousesMaterials() {
    }
    public WarehousesMaterials(Warehouse warehouse, Material material, Integer amount) {
        this.warehouse = warehouse;
        this.material = material;
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    public Integer getAmount(){return amount;}

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehousesMaterials warehousesMaterials = (WarehousesMaterials) o;
        return Objects.equals(id, warehousesMaterials.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "WarehousesMaterials{" +
                "id=" + id +
                ", warehousename='" + warehouse.getWarehouseName() + '\'' +
                ", materialname='" + material.getMaterialName() + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
