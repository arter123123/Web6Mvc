package ru.ulstu.is.sbapp.laba3.service;

public class WarehousesMaterialsNotFoundException extends RuntimeException{
    public WarehousesMaterialsNotFoundException(Long id) {
        super(String.format("WarehousesMaterials with id [%s] is not found", id));
    }
}
