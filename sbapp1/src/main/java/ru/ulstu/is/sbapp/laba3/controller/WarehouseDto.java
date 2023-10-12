package ru.ulstu.is.sbapp.laba3.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class WarehouseDto {
    private long id;
    @NotBlank
    private String warehousename;
    @JsonProperty("typeWarehouseId")
    private Long typeWarehouse_id;
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private TypeWarehouse typeWarehouse;

    public WarehouseDto() {}
    public WarehouseDto(Warehouse warehouse) {
        this.id = warehouse.getId();
        this.typeWarehouse_id = warehouse.getTypeWarehouse().getId();
        this.warehousename = warehouse.getWarehouseName();
        this.typeWarehouse = warehouse.getTypeWarehouse();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }
    public String getWarehouseName(){
        return warehousename;
    }
    public TypeWarehouse getTypeWarehouse(){
        return typeWarehouse;
    }

    public Long getTypeWarehouse_id() {
        return typeWarehouse_id;
    }

    public void setWarehouseName(String warehouseName){
        this.warehousename = warehouseName;
    }
    public void setTypeWarehouse(TypeWarehouse typeWarehouse){
        this.typeWarehouse = typeWarehouse;
    }
    public void setTypeWarehouse_id(Long typeWarehouse_id) {
        this.typeWarehouse_id = typeWarehouse_id;
    }
}
