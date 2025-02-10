package ru.hazov.center.dto.api.in.medicament_order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record MedicamentOrderRequest(@NotNull @NotEmpty String medicamentName, @Positive int count) {
}
