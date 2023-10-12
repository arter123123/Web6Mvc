package ru.ulstu.is.sbapp.laba3.service;

public class TypeWarehouseNotFoundException extends RuntimeException{
    public TypeWarehouseNotFoundException(Long id) {
        super(String.format("TypeWarehouse with id [%s] is not found", id));
    }
}
