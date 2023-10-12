package ru.ulstu.is.sbapp.laba3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.laba3.model.Warehouse;

public interface IWarehouseRepository extends JpaRepository<Warehouse,Long> {
}
