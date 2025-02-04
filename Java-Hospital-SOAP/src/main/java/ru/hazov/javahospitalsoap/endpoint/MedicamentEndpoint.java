package ru.hazov.javahospitalsoap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;
import ru.hazov.javahospitalsoap.entity.EOrder;
import ru.hazov.javahospitalsoap.entity.Medicament;
import ru.hazov.javahospitalsoap.jaxb_model.*;
import ru.hazov.javahospitalsoap.mapper.MedicamentMapper;
import ru.hazov.javahospitalsoap.service.MedicamentService;
import ru.hazov.javahospitalsoap.service.OrderService;


@Endpoint
public class MedicamentEndpoint {

    private final MedicamentService medicamentService;
    private final MedicamentMapper medicamentMapper;
    private final OrderService orderService;

    @Autowired
    MedicamentEndpoint(MedicamentService medicamentService, MedicamentMapper medicamentMapper, OrderService orderService) {
        this.medicamentService = medicamentService;
        this.medicamentMapper = medicamentMapper;
        this.orderService = orderService;
    }

    @SoapAction("getMedicamentsCount")
    @PayloadRoot(localPart = "getMedicamentCountResponse", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public GetMedicamentCountResponse getPatients(@RequestPayload GetMedicamentCountRequest request) {
        Medicament medicament = medicamentService.getByName(request.getName());
        return medicamentMapper.createGetMedicamentCountResponse(medicament);
    }

    @SoapAction("orderMedicament")
    @PayloadRoot(localPart = "orderMedicamentResponse", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public OrderMedicamentResponse orderMedicament(@RequestPayload OrderMedicamentRequest request) {
        OrderMedicamentResponse orderMedicamentResponse = new OrderMedicamentResponse();
        Medicament medicament = medicamentService.getByName(request.getName());
        EOrder order = orderService.createOrder(medicament, request.getCount());

        orderMedicamentResponse.setResult(true);
        orderMedicamentResponse.setReason("success");
        orderMedicamentResponse.setOrderId(order.getId());
        return orderMedicamentResponse;
    }

    @SoapAction("rejectOrderMedicament")
    @PayloadRoot(localPart = "rejectOrderMedicamentResponse", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public RejectOrderMedicamentResponse rejectOrderMedicament(@RequestPayload RejectOrderMedicamentRequest request) {
        RejectOrderMedicamentResponse rejectOrderMedicamentResponse = new RejectOrderMedicamentResponse();
        orderService.deleteOrder(request.getOrderId());
        rejectOrderMedicamentResponse.setResult(true);
        rejectOrderMedicamentResponse.setReason("success");
        rejectOrderMedicamentResponse.setOrderId(request.getOrderId());
        return rejectOrderMedicamentResponse;
    }
}


