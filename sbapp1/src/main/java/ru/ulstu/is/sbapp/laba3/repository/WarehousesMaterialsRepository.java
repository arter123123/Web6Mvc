package ru.ulstu.is.sbapp.laba3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.laba3.model.Material;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;
import ru.ulstu.is.sbapp.laba3.model.WarehousesMaterials;

import java.util.Optional;

public interface WarehousesMaterialsRepository extends JpaRepository<WarehousesMaterials, Long> {
    Optional<WarehousesMaterials> findByWarehouse(Warehouse Warehouse);
    Optional<WarehousesMaterials> findByMaterial(Material Material);
}
