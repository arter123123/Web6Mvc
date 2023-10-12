package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehouseService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseMvcController {
    private final WarehouseService warehouseService;
    private final TypeWarehouseService typeWarehouseService;

    public WarehouseMvcController(WarehouseService warehouseService, TypeWarehouseService typeWarehouseService) {
        this.warehouseService = warehouseService;
        this.typeWarehouseService = typeWarehouseService;
    }

    @GetMapping
    public String getWarehouses(Model model) {
        model.addAttribute("warehouses",
                warehouseService.findAllWarehouses().stream()
                        .map(WarehouseDto::new)
                        .toList());
        return "warehouse";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editWarehouse(@PathVariable(required = false) Long id,
                                    Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("warehouseDto", new WarehouseDto());
            model.addAttribute("typeWarehouses",
                    typeWarehouseService.findAllTypeWarehouses().stream()
                            .map(TypeWarehouseDto::new)
                            .toList());
        } else {
            model.addAttribute("warehouseId", id);
            model.addAttribute("typeWarehouses",
                    typeWarehouseService.findAllTypeWarehouses().stream()
                            .map(TypeWarehouseDto::new)
                            .toList());
            model.addAttribute("warehouseDto", new WarehouseDto(warehouseService.findWarehouse(id)));
        }
        return "warehouse-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveWarehouse(@PathVariable(required = false) Long id,
                                @RequestParam(value = "typeWarehouseId") Long typeWarehouse_id,
                                @ModelAttribute @Valid WarehouseDto warehouseDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "warehouse-edit";
        }
        warehouseDto.setTypeWarehouse_id(typeWarehouse_id);
        warehouseDto.setTypeWarehouse(typeWarehouseService.findTypeWarehouse(warehouseDto.getTypeWarehouse_id()));
        if (id == null || id <= 0) {
            warehouseService.addWarehouse(warehouseDto.getWarehouseName(), warehouseDto.getTypeWarehouse().getId());
        } else {
            warehouseService.updateWarehouse(id, warehouseDto.getWarehouseName(), warehouseDto.getTypeWarehouse());
        }
        return "redirect:/warehouse";
    }
    @PostMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return "redirect:/warehouse";
    }
}
