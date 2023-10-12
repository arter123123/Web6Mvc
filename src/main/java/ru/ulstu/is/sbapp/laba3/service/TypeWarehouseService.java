package ru.ulstu.is.sbapp.laba3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.repository.MaterialRepository;
import ru.ulstu.is.sbapp.laba3.repository.TypeWarehouseRepository;
import ru.ulstu.is.sbapp.laba3.repository.WarehouseRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class TypeWarehouseService {
    private final TypeWarehouseRepository typewarehouseRepository;
    private final ValidatorUtil validatorUtil;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseService warehouseService;

    public TypeWarehouseService(TypeWarehouseRepository typewarehouseRepository, ValidatorUtil validatorUtil, WarehouseRepository warehouseRepository, WarehouseService warehouseService){
        this.typewarehouseRepository = typewarehouseRepository;
        this.validatorUtil = validatorUtil;
        this.warehouseRepository = warehouseRepository;
        this.warehouseService = warehouseService;
    }
    @Transactional
    public TypeWarehouse addTypeWarehouse(String name) {
        final TypeWarehouse typeWarehouse = new TypeWarehouse(name);
        validatorUtil.validate(typeWarehouse);
        return typewarehouseRepository.save(typeWarehouse);
    }
    @Transactional(readOnly = true)
    public TypeWarehouse findTypeWarehouse(Long id) {
        final Optional<TypeWarehouse> typewarehouse = typewarehouseRepository.findById(id);
        return typewarehouse.orElseThrow(() -> new TypeWarehouseNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<TypeWarehouse> findAllTypeWarehouses() {
        return typewarehouseRepository.findAll();
    }

    @Transactional
    public TypeWarehouse updateTypeWarehouse(Long id, String name) {
        final TypeWarehouse currentTypeWarehouse = findTypeWarehouse(id);
        currentTypeWarehouse.setTypeWarehouseName(name);
        validatorUtil.validate(currentTypeWarehouse);
        return typewarehouseRepository.save(currentTypeWarehouse);
    }

    @Transactional
    public TypeWarehouse deleteTypeWarehouse(Long id) {
        final TypeWarehouse currentTypeWarehouse = findTypeWarehouse(id);
        typewarehouseRepository.delete(currentTypeWarehouse);
        return currentTypeWarehouse;
    }

    @Transactional
    public void deleteAllTypeWarehouses() {
        typewarehouseRepository.deleteAll();
    }
}
