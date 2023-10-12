package ru.ulstu.is.sbapp.laba3.controller;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;

import javax.validation.constraints.NotBlank;

public class TypeWarehouseDto {
    private Long id;
    @NotBlank
    private String typewarehousename;
    public TypeWarehouseDto() {
    }
    public TypeWarehouseDto(TypeWarehouse typeWarehouse) {
        this.id = typeWarehouse.getId();
        this.typewarehousename = typeWarehouse.getTypeWarehouseName();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public String getTypeWarehouseName() {
        return typewarehousename;
    }
    public void setTypeWarehouseName(String typeWarehouseName){
        this.typewarehousename = typeWarehouseName;
    }
}