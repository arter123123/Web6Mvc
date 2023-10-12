package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.laba3.service.MaterialService;
import ru.ulstu.is.sbapp.laba3.service.WarehouseService;
import ru.ulstu.is.sbapp.laba3.service.WarehousesMaterialsService;

import javax.validation.Valid;

@Controller
@RequestMapping("/warehousesmaterials")
public class WarehousesMaterialsMvcController {
    private final WarehousesMaterialsService warehousesMaterialsService;
    private final WarehouseService warehouseService;
    private final MaterialService materialService;

    public WarehousesMaterialsMvcController(WarehousesMaterialsService warehousesMaterialsService,WarehouseService warehouseService, MaterialService materialService){
        this.warehousesMaterialsService = warehousesMaterialsService;
        this.warehouseService = warehouseService;
        this.materialService = materialService;
    }

    @GetMapping
    public String getWarehousesMaterialss(Model model) {
        model.addAttribute("warehousesmaterials",
                warehousesMaterialsService.findAllWarehousesMaterials().stream()
                        .map(WarehousesMaterialsDto::new)
                        .toList());
        return "warehousesmaterials";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editWarehousesMaterials(@PathVariable(required = false) Long id,
                                Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("warehousesMaterialsDto", new WarehousesMaterialsDto());
            model.addAttribute("warehouses",
                    warehouseService.findAllWarehouses().stream()
                            .map(WarehouseDto::new)
                            .toList());
            model.addAttribute("materials",
                    materialService.findAllMaterials().stream()
                            .map(MaterialDto::new)
                            .toList());
        } else {
            model.addAttribute("warehousesMaterialsId", id);
            model.addAttribute("warehouses",
                    warehouseService.findAllWarehouses().stream()
                            .map(WarehouseDto::new)
                            .toList());
            model.addAttribute("materials",
                    materialService.findAllMaterials().stream()
                            .map(MaterialDto::new)
                            .toList());
            model.addAttribute("warehousesMaterialsDto", new WarehousesMaterialsDto(warehousesMaterialsService.findWarehousesMaterials(id)));
        }
        return "warehousesmaterials-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveWarehousesMaterials(@PathVariable(required = false) Long id,
                                          @RequestParam(value = "warehouseId") Long warehouse_id,
                                          @RequestParam(value = "materialId") Long material_id,
                                @ModelAttribute @Valid WarehousesMaterialsDto warehousesMaterialsDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "warehousesmaterials-edit";
        }
        warehousesMaterialsDto.setWarehouse_id(warehouse_id);
        warehousesMaterialsDto.setWarehouse(warehouseService.findWarehouse(warehousesMaterialsDto.getWarehouse_id()));
        warehousesMaterialsDto.setMaterial_id(material_id);
        warehousesMaterialsDto.setMaterial(materialService.findMaterial(warehousesMaterialsDto.getMaterial_id()));
        if (id == null || id <= 0) {
            warehousesMaterialsService.addWarehousesMaterials(warehousesMaterialsDto.getWarehouse().getId(), warehousesMaterialsDto.getMaterial().getId(), warehousesMaterialsDto.getAmount());
        } else {
            warehousesMaterialsService.updateWarehousesMaterials(id, warehousesMaterialsDto.getWarehouse().getId(), warehousesMaterialsDto.getMaterial().getId(), warehousesMaterialsDto.getAmount());
        }
        return "redirect:/warehousesmaterials";
    }
    @PostMapping("/delete/{id}")
    public String deleteWarehousesMaterials(@PathVariable Long id) {
        warehousesMaterialsService.deleteWarehousesMaterials(id);
        return "redirect:/warehousesmaterials";
    }
}
