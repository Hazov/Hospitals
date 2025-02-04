package ru.hazov.center.kafka_events.medicament_order;

public record MedicamentOrder(String hospitalName, boolean isConsumer, int count) {}
