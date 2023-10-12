package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final TypeWarehouseService typeWarehouseService;

    public WarehouseController(WarehouseService warehouseService, TypeWarehouseService typeWarehouseService) {
        this.warehouseService = warehouseService;
        this.typeWarehouseService = typeWarehouseService;
    }

    @GetMapping("/{id}")
    public WarehouseDto getWarehouse(@PathVariable Long id) {
        return new WarehouseDto(warehouseService.findWarehouse(id));
    }

    @GetMapping("/")
    public List<WarehouseDto> getWarehouses() {
        return warehouseService.findAllWarehouses().stream()
                .map(WarehouseDto::new)
                .toList();
    }

    @PostMapping("/")
    public WarehouseDto createWarehouse(@RequestParam("name") String name,
                                        @RequestParam("TypeWarehouseID") Long TypeWarehouseId) {
        return new WarehouseDto(warehouseService.addWarehouse(name, TypeWarehouseId));
    }
    @PatchMapping("/{id}")
    public WarehouseDto updateWarehouse(@PathVariable Long id,
                                        @RequestParam("name") String name,
                                        @RequestParam("TypeWarehouseID") Long TypeWarehouseId) {
        final TypeWarehouse currenttypeWarehouse = typeWarehouseService.findTypeWarehouse(TypeWarehouseId);
        return new WarehouseDto(warehouseService.updateWarehouse(id, name, currenttypeWarehouse));
    }
    @DeleteMapping("/{id}")
    public WarehouseDto deleteWarehouse(@PathVariable Long id) {
        return new WarehouseDto(warehouseService.deleteWarehouse(id));
    }
}
