package ru.ulstu.is.sbapp.laba3.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.model.WarehousesMaterials;

import javax.validation.constraints.NotBlank;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class WarehousesMaterialsDto {
    private long id;
    @JsonProperty("warehouseId")
    private Long warehouse_id;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Warehouse warehouse;
    @JsonProperty("materialId")
    private Long material_id;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Material material;
    private Integer amount;
    public WarehousesMaterialsDto() {
    }
    public WarehousesMaterialsDto(WarehousesMaterials warehousesMaterials) {
        this.id = warehousesMaterials.getId();
        this.warehouse_id = warehousesMaterials.getWarehouse().getId();
        this.material_id = warehousesMaterials.getMaterial().getId();
        this.warehouse = warehousesMaterials.getWarehouse();
        this.material = warehousesMaterials.getMaterial();
        this.amount = warehousesMaterials.getAmount();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public Long getWarehouse_id() {
        return warehouse_id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Long getMaterial_id() {
        return material_id;
    }

    public Material getMaterial() {
        return material;
    }
    public Integer getAmount(){return amount;}

    public void setWarehouse_id(Long warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setMaterial_id(Long material_id) {
        this.material_id = material_id;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
