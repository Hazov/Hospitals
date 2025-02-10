package ru.hazov.center.dto.api.out.medicament_order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EsbMedicamentOrdersRequest {
    private List<EsbMedicamentOrder> medicamentOrders;
}
