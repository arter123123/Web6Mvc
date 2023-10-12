package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.WarehousesMaterials;
import ru.ulstu.is.sbapp.laba3.service.MaterialService;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehousesMaterialsService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/warehousesmaterials")
public class WarehousesMaterialsController {
    private final WarehousesMaterialsService warehousesMaterialsService;
    private final WarehouseService warehouseService;
    private final MaterialService materialService;
    public WarehousesMaterialsController(WarehousesMaterialsService warehousesMaterialsService,WarehouseService warehouseService, MaterialService materialService){
        this.warehousesMaterialsService = warehousesMaterialsService;
        this.warehouseService = warehouseService;
        this.materialService = materialService;
    }
    @GetMapping("/{id}")
    public WarehousesMaterialsDto WarehousesMaterials(@PathVariable Long id) {
        return new WarehousesMaterialsDto(warehousesMaterialsService.findWarehousesMaterials(id));
    }
    @GetMapping("/")
    public List<WarehousesMaterialsDto> getWarehousesMaterials() {
        return warehousesMaterialsService.findAllWarehousesMaterials().stream()
                .map(WarehousesMaterialsDto::new)
                .toList();
    }
    @PostMapping("/")
    public WarehousesMaterialsDto createWarehousesMaterials(@RequestParam("amount") Integer amount,
                                        @RequestParam("WarehouseID") Long WarehouseId,
                                        @RequestParam("MaterialID") Long MaterialId) {
        return new WarehousesMaterialsDto(warehousesMaterialsService.addWarehousesMaterials(WarehouseId, MaterialId, amount));
    }
    @PatchMapping("/{id}")
    public WarehousesMaterialsDto updateWarehousesMaterials(@PathVariable Long id,
                                        @RequestParam("amount") Integer amount,
                                        @RequestParam("WarehouseID") Long WarehouseId,
                                        @RequestParam("MaterialID") Long MaterialId) {
        return new WarehousesMaterialsDto(warehousesMaterialsService.updateWarehousesMaterials(id, WarehouseId, MaterialId, amount));
    }
    @DeleteMapping("/{id}")
    public WarehousesMaterialsDto deleteWarehousesMaterials(@PathVariable Long id) {
        return new WarehousesMaterialsDto(warehousesMaterialsService.deleteWarehousesMaterials(id));
    }
}
