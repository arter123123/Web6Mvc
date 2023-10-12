package ru.ulstu.is.sbapp.laba3.service;

public class MaterialNotFoundException  extends RuntimeException{
    public MaterialNotFoundException(Long id) {
        super(String.format("Material with id [%s] is not found", id));
    }
}
