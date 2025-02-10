package ru.hazov.center.dto.api.out.medicament_order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EsbMedicamentOrder {
    private String hospital;
    private int value;
}

