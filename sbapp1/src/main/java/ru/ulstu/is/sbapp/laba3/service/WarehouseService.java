package ru.ulstu.is.sbapp.laba3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.TypeWarehouse;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.repository.MaterialRepository;
import ru.ulstu.is.sbapp.laba3.repository.TypeWarehouseRepository;
import ru.ulstu.is.sbapp.laba3.repository.WarehouseRepository;
import ru.ulstu.is.sbapp.util.validation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ValidatorUtil validatorUtil;
    private final MaterialRepository materialRepository;
    private final MaterialService materialService;
    private final TypeWarehouseRepository typeWarehouseRepository;
    public WarehouseService(WarehouseRepository warehouseRepository, ValidatorUtil validatorUtil, MaterialRepository materialRepository, TypeWarehouseRepository typeWarehouseRepository, MaterialService materialService){
        this.warehouseRepository = warehouseRepository;
        this.validatorUtil = validatorUtil;
        this.materialRepository = materialRepository;
        this.materialService = materialService;
        this.typeWarehouseRepository = typeWarehouseRepository;
    }

    @Transactional
    public Warehouse addWarehouse(String name, Long TypeWarehouseId) {
        final TypeWarehouse typeWarehouse = typeWarehouseRepository.findById(TypeWarehouseId).orElseThrow(() -> new TypeWarehouseNotFoundException(TypeWarehouseId));
        final Warehouse warehouse = new Warehouse(name, typeWarehouse);
        validatorUtil.validate(warehouse);
        return warehouseRepository.save(warehouse);
    }

    @Transactional(readOnly = true)
    public Warehouse findWarehouse(Long id) {
        final Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        return warehouse.orElseThrow(() -> new WarehouseNotFoundException(id));
    }
    @Transactional(readOnly = true)
    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }


    @Transactional
    public Warehouse updateWarehouse(Long id, String name, TypeWarehouse typeWarehouse) {
        final Warehouse currentWarehouse = findWarehouse(id);
        currentWarehouse.setWarehouseName(name);
        currentWarehouse.setTypeWarehouse(typeWarehouse);
        validatorUtil.validate(currentWarehouse);
        return warehouseRepository.save(currentWarehouse);
    }

    @Transactional
    public Warehouse deleteWarehouse(Long id) {
        final Warehouse currentWarehouse = findWarehouse(id);
        warehouseRepository.delete(currentWarehouse);
        return currentWarehouse;
    }
    @Transactional
    public void deleteAllWarehouses() {
        warehouseRepository.deleteAll();
    }
    @Transactional
    public Warehouse setTypeWarehouse(Long WarehouseId, Long TypeWarehouseId){
        final Warehouse currentWarehouse = findWarehouse(WarehouseId);
        final TypeWarehouse TypeWarehouse = typeWarehouseRepository.findById(TypeWarehouseId).orElseThrow(() -> new TypeWarehouseNotFoundException(TypeWarehouseId));
        currentWarehouse.setTypeWarehouse(TypeWarehouse);
        validatorUtil.validate(currentWarehouse);
        return warehouseRepository.save(currentWarehouse);
    }
}
