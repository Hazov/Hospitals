package ru.hazov.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hazov.center.dto.medicamentOrder.MedicamentOrderRequest;
import ru.hazov.center.service.MedicamentsService;

import java.util.Map;

@Controller
public class MedicamentsController {
    private final MedicamentsService medicamentsService;

    @Autowired
    public MedicamentsController(MedicamentsService medicamentsService) {
        this.medicamentsService = medicamentsService;
    }

    @GetMapping("/medicament-request")
    public void OrderMedicaments(MedicamentOrderRequest request){
        medicamentsService.orderMedicaments(request.medicamentName(), request.count());


    }
}
