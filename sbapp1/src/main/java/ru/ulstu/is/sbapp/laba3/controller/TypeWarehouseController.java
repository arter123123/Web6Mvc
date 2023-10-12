package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/typeWarehouse")
public class TypeWarehouseController {
    private final TypeWarehouseService typeWarehouseService;


    public TypeWarehouseController(TypeWarehouseService typeWarehouseService) {
        this.typeWarehouseService = typeWarehouseService;
    }

    @GetMapping("/{id}")
    public TypeWarehouseDto getTypeWarehouse(@PathVariable Long id) {
        return new TypeWarehouseDto(typeWarehouseService.findTypeWarehouse(id));
    }

    @GetMapping("/")
    public List<TypeWarehouseDto> getTypeWarehouses() {
        return typeWarehouseService.findAllTypeWarehouses().stream()
                .map(TypeWarehouseDto::new)
                .toList();
    }

    @PostMapping("/")
    public TypeWarehouseDto createTypeWarehouse(@RequestParam("Name") String Name) {
        return new TypeWarehouseDto(typeWarehouseService.addTypeWarehouse(Name));
    }

    @PatchMapping("/{id}")
    public TypeWarehouseDto updateTypeWarehouse(@PathVariable Long id,
                                                @RequestParam("Name") String Name) {
        return new TypeWarehouseDto(typeWarehouseService.updateTypeWarehouse(id, Name));
    }

    @DeleteMapping("/{id}")
    public TypeWarehouseDto deleteTypeWarehouse(@PathVariable Long id) {
        return new TypeWarehouseDto(typeWarehouseService.deleteTypeWarehouse(id));
    }
}
