package ru.hazov.center.dto.medicamentOrder;

import lombok.Data;


public record MedicamentOrderRequest(String medicamentName, int count) {
}
