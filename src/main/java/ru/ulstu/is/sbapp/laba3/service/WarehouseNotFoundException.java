package ru.ulstu.is.sbapp.laba3.service;

public class WarehouseNotFoundException extends RuntimeException{
    public WarehouseNotFoundException(Long id) {
        super(String.format("Warehouse with id [%s] is not found", id));
    }
}
