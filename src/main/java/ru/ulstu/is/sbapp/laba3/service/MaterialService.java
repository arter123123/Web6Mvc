package ru.ulstu.is.sbapp.laba3.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.laba3.model.Material;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.repository.MaterialRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final ValidatorUtil validatorUtil;

    public MaterialService(MaterialRepository materialRepository, ValidatorUtil validatorUtil) {
        this.materialRepository = materialRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Material addMaterial(String name) {
        final Material material = new Material(name);
        validatorUtil.validate(material);
        return materialRepository.save(material);
    }

    @Transactional(readOnly = true)
    public Material findMaterial(Long id) {
        final Optional<Material> material = materialRepository.findById(id);
        return material.orElseThrow(() -> new MaterialNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Material> findAllMaterials() {
        return materialRepository.findAll();
    }

    @Transactional
    public Material updateMaterial(Long id, String Name) {
        final Material currentMaterial = findMaterial(id);
        currentMaterial.setMaterialName(Name);
        validatorUtil.validate(currentMaterial);
        return materialRepository.save(currentMaterial);
    }

    @Transactional
    public Material deleteMaterial(Long id) {
        final Material currentMaterial = findMaterial(id);
        materialRepository.delete(currentMaterial);
        return currentMaterial;
    }
    @Transactional
    public void deleteAllMaterials() {
       materialRepository.deleteAll();
    }


}

