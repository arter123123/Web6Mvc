package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.WebConfiguration;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.service.MaterialService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/material")
public class MaterialController {
    private final MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/{id}")
    public MaterialDto getMaterial(@PathVariable Long id) {
        return new MaterialDto(materialService.findMaterial(id));
    }

    @GetMapping("/")
    public List<MaterialDto> getMaterials() {
        return materialService.findAllMaterials().stream()
                .map(MaterialDto::new)
                .toList();
    }

    @PostMapping("/")
    public MaterialDto createMaterial(@RequestParam("Name") String name) {
        return new MaterialDto(materialService.addMaterial(name));
    }

    @PatchMapping("/{id}")
    public MaterialDto updateMaterial(@PathVariable Long id,
                                      @RequestParam("Name") String Name) {
        return new MaterialDto(materialService.updateMaterial(id, Name));
    }

    @DeleteMapping("/{id}")
    public MaterialDto deleteMaterial(@PathVariable Long id) {
        return new MaterialDto(materialService.deleteMaterial(id));
    }
}
