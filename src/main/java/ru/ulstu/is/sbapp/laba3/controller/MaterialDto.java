package ru.ulstu.is.sbapp.laba3.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class MaterialDto {
    private long id;
    @NotBlank
    private String materialname;
    public MaterialDto() {
    }
    public MaterialDto(Material material) {
        this.id = material.getId();
        this.materialname = material.getMaterialName();
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    public String getMaterialName() {
        return materialname;
    }
    public void setMaterialName(String materialName){
        this.materialname = materialName;
    }
}
