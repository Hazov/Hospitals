package ru.hazov.javahospitalsoap.kafka_msg;

public record MedicamentOrder(String hospitalName, boolean isConsumer, int count) {}

