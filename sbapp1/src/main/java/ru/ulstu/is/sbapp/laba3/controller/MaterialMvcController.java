package ru.ulstu.is.sbapp.laba3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.laba3.service.MaterialService;

import javax.validation.Valid;

@Controller
@RequestMapping("/material")
public class MaterialMvcController {
    private final MaterialService materialService;

    public MaterialMvcController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public String getMaterials(Model model) {
        model.addAttribute("materials",
                materialService.findAllMaterials().stream()
                        .map(MaterialDto::new)
                        .toList());
        return "material";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editMaterial(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("materialDto", new MaterialDto());
        } else {
            model.addAttribute("materialId", id);
            model.addAttribute("materialDto", new MaterialDto(materialService.findMaterial(id)));
        }
        return "material-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveMaterial(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid MaterialDto materialDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "material-edit";
        }
        if (id == null || id <= 0) {
            materialService.addMaterial(materialDto.getMaterialName());
        } else {
            materialService.updateMaterial(id, materialDto.getMaterialName());
        }
        return "redirect:/material";
    }

    @PostMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return "redirect:/material";
    }
}
