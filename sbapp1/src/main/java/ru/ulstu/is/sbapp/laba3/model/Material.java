package ru.ulstu.is.sbapp.laba3.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.*;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "materialname")
    private String materialname;

    public Material() {
    }

    public Material(String materialname) {
        this.materialname=materialname;
    }

    public Long getId() {
        return id;
    }
    public String getMaterialName() {
        return materialname;
    }
    public void setMaterialName(String materialname) {
        this.materialname = materialname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Objects.equals(id, material.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", materialname='" + materialname + '\'' +
                '}';
    }
}
