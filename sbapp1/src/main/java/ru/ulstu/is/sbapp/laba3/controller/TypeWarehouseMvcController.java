package ru.ulstu.is.sbapp.laba3.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.laba3.service.TypeWarehouseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/typeWarehouse")
public class TypeWarehouseMvcController {
    private final TypeWarehouseService typeWarehouseService;

    public TypeWarehouseMvcController(TypeWarehouseService typeWarehouseService) {
        this.typeWarehouseService = typeWarehouseService;
    }

    @GetMapping
    public String getTypeWarehouses(Model model) {
        model.addAttribute("typeWarehouses",
                typeWarehouseService.findAllTypeWarehouses().stream()
                        .map(TypeWarehouseDto::new)
                        .toList());
        return "typeWarehouse";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editTypeWarehouse(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("typeWarehouseDto", new TypeWarehouseDto());
        } else {
            model.addAttribute("typeWarehouseId", id);
            model.addAttribute("typeWarehouseDto", new TypeWarehouseDto(typeWarehouseService.findTypeWarehouse(id)));
        }
        return "typeWarehouse-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveTypeWarehouse(@PathVariable(required = false) Long id,
                                    @ModelAttribute @Valid TypeWarehouseDto typeWarehouseDto,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "typeWarehouse-edit";
        }
        if (id == null || id <= 0) {
            typeWarehouseService.addTypeWarehouse(typeWarehouseDto.getTypeWarehouseName());
        } else {
            typeWarehouseService.updateTypeWarehouse(id, typeWarehouseDto.getTypeWarehouseName());
        }
        return "redirect:/typeWarehouse";
    }

    @PostMapping("/delete/{id}")
    public String deleteTypeWarehouse(@PathVariable Long id) {
        typeWarehouseService.deleteTypeWarehouse(id);
        return "redirect:/typeWarehouse";
    }
}
