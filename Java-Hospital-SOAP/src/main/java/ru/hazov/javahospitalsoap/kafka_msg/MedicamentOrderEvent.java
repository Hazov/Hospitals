package ru.hazov.javahospitalsoap.kafka_msg;

import java.util.List;

public record MedicamentOrderEvent(List<MedicamentOrder> medicamentOrders){}
