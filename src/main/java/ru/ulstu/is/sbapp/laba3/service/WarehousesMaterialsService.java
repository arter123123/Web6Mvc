package ru.ulstu.is.sbapp.laba3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.laba3.model.Material;
import org.springframework.stereotype.Service;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.model.WarehousesMaterials;
import ru.ulstu.is.sbapp.laba3.repository.MaterialRepository;
import ru.ulstu.is.sbapp.laba3.repository.TypeWarehouseRepository;
import ru.ulstu.is.sbapp.laba3.repository.WarehouseRepository;
import ru.ulstu.is.sbapp.laba3.repository.WarehousesMaterialsRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class WarehousesMaterialsService {
    private final WarehousesMaterialsRepository warehousesMaterialsRepository;
    private final ValidatorUtil validatorUtil;
    private final MaterialRepository materialRepository;
    private final WarehouseRepository warehouseRepository;
    public WarehousesMaterialsService(WarehousesMaterialsRepository warehousesMaterialsRepository, ValidatorUtil validatorUtil, MaterialRepository materialRepository, WarehouseRepository warehouseRepository){
        this.warehousesMaterialsRepository = warehousesMaterialsRepository;
        this.validatorUtil = validatorUtil;
        this.materialRepository = materialRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public WarehousesMaterials addWarehousesMaterials(Long WarehouseId, Long MaterialId, Integer amount) {
        final var material = materialRepository.findById(MaterialId).orElseThrow(() -> new MaterialNotFoundException(MaterialId));
        final var warehouse = warehouseRepository.findById(WarehouseId).orElseThrow(() -> new WarehouseNotFoundException(WarehouseId));
        final WarehousesMaterials warehousesMaterials = new WarehousesMaterials(warehouse, material, amount);
        validatorUtil.validate(warehousesMaterials);
        return warehousesMaterialsRepository.save(warehousesMaterials);
    }
    @Transactional(readOnly = true)
    public WarehousesMaterials findWarehousesMaterials(Long id) {
        final WarehousesMaterials warehousesMaterials = warehousesMaterialsRepository.findById(id).orElseThrow(() -> new WarehousesMaterialsNotFoundException(id));
        return warehousesMaterials;
    }

    @Transactional(readOnly = true)
    public List<WarehousesMaterials> findAllWarehousesMaterials() {
        return warehousesMaterialsRepository.findAll();
    }
    @Transactional
    public WarehousesMaterials updateWarehousesMaterials(Long id, Long WarehouseId, Long MaterialId, int amount) {
        final var material = materialRepository.findById(MaterialId).orElseThrow(() -> new MaterialNotFoundException(MaterialId));
        final var warehouse = warehouseRepository.findById(WarehouseId).orElseThrow(() -> new WarehouseNotFoundException(WarehouseId));
        final WarehousesMaterials currentWarehousesMaterials = findWarehousesMaterials(id);
        currentWarehousesMaterials.setMaterial(material);
        currentWarehousesMaterials.setWarehouse(warehouse);
        currentWarehousesMaterials.setAmount(amount);
        validatorUtil.validate(currentWarehousesMaterials);
        return warehousesMaterialsRepository.save(currentWarehousesMaterials);
    }

    @Transactional
    public WarehousesMaterials deleteWarehousesMaterials(Long id) {
        final WarehousesMaterials currentWarehousesMaterials = findWarehousesMaterials(id);
        warehousesMaterialsRepository.delete(currentWarehousesMaterials);
        return currentWarehousesMaterials;
    }
    @Transactional
    public void deleteAllWarehousesMaterials() {
        warehousesMaterialsRepository.deleteAll();
    }
}
