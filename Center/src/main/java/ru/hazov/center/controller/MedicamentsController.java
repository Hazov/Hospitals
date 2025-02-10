package ru.hazov.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hazov.center.dto.api.in.medicament_order.MedicamentOrderRequest;

import ru.hazov.center.dto.api.in.medicament_order.MedicamentOrderResponse;
import ru.hazov.center.service.MedicamentsService;


@RestController
@RequestMapping(path = "/medicaments")
public class MedicamentsController {
    private final MedicamentsService medicamentsService;

    @Autowired
    public MedicamentsController(MedicamentsService medicamentsService) {
        this.medicamentsService = medicamentsService;
    }

    @GetMapping("/medicament-request")
    public ResponseEntity<MedicamentOrderResponse> orderMedicaments(@Validated MedicamentOrderRequest request){
        MedicamentOrderResponse medicamentOrderResponse = new MedicamentOrderResponse();
        medicamentsService.orderMedicaments(request.medicamentName(), request.count());
        medicamentOrderResponse.setResult(true);
        return new ResponseEntity<>(medicamentOrderResponse, HttpStatusCode.valueOf(200));

    }
}
