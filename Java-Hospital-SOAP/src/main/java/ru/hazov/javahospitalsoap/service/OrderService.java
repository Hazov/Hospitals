package ru.hazov.javahospitalsoap.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.hazov.javahospitalsoap.entity.EOrder;
import ru.hazov.javahospitalsoap.entity.Medicament;
import ru.hazov.javahospitalsoap.jaxb_model.OrderMedicamentResponse;
import ru.hazov.javahospitalsoap.repository.EOrderRepository;
import ru.hazov.javahospitalsoap.repository.MedicamentRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    private final MedicamentRepository medicamentRepository;
    private final EOrderRepository eOrderRepository;

    public OrderService(MedicamentRepository medicamentRepository, EOrderRepository eOrderRepository) {
        this.medicamentRepository = medicamentRepository;
        this.eOrderRepository = eOrderRepository;
    }


    public EOrder createOrder(Medicament medicament, int count) {
        EOrder order = new EOrder();
        order.setDate(new Date());
        order.setMedicament(medicament);
        order.setQuantity(count);
        eOrderRepository.save(order);
        return order;
    }

    public void deleteOrder(long orderId) {
        eOrderRepository.deleteById(orderId);
    }

    public Optional<EOrder> getOrderById(long orderId) {
        return eOrderRepository.findById(orderId);
    }
}
