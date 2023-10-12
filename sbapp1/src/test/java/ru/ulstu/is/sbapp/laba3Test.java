package ru.ulstu.is.sbapp;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.service.MaterialService;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehouseService;

@SpringBootTest
public class laba3Test {
    private static final Logger log = LoggerFactory.getLogger(JpaStudentTest.class);

    @Autowired
    private MaterialService materialService;
    @Autowired
    private TypeWarehouseService typeWarehouseService;
    @Autowired
    private WarehouseService warehouseService;
    ///3 лаба
    @Test
    void Add(){
        materialService.deleteAllMaterials();

        final Material m1 = materialService.addMaterial("Стол");
        log.info(m1.toString());
        final Material m2 = materialService.addMaterial("Шкаф");
        log.info(m2.toString());
        final Material m3 = materialService.addMaterial("Доска");
        log.info(m3.toString());
        final Material m4 = materialService.addMaterial("Клей");
        log.info(m4.toString());

        typeWarehouseService.deleteAllTypeWarehouses();

        final TypeWarehouse t1 = typeWarehouseService.addTypeWarehouse("Склад готовых изделий");
        log.info(t1.toString());
        final TypeWarehouse t2 = typeWarehouseService.addTypeWarehouse("Склад материалов");
        log.info(t2.toString());

        warehouseService.deleteAllWarehouses();

        final Warehouse w1 = warehouseService.addWarehouse(m1.getId(), t1.getId());
        log.info(w1.toString());
        final Warehouse w2 = warehouseService.addWarehouse(m2.getId(), t1.getId());
        log.info(w2.toString());
        final Warehouse w3 = warehouseService.addWarehouse(m3.getId(), t2.getId());
        log.info(w3.toString());
        final Warehouse w4 = warehouseService.addWarehouse(m4.getId(), t2.getId());
        log.info(w4.toString());
    }
}