package ru.hazov.center.kafka_events.medicament_order;

import java.util.List;

public record MedicamentOrderEvent(List<MedicamentOrder> medicamentOrders){}
