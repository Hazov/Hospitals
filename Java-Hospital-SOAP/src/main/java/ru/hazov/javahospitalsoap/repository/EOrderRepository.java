package ru.hazov.javahospitalsoap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hazov.javahospitalsoap.entity.EOrder;

@Repository
public interface EOrderRepository extends JpaRepository<EOrder, Long> {
}
