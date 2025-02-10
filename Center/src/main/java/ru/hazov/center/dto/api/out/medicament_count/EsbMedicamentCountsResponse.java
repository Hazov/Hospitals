package ru.hazov.center.dto.api.out.medicament_count;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hazov.center.dto.api.in.common.DefaultResponse;
import ru.hazov.center.dto.api.in.medicament_counts.MedicamentCount;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class EsbMedicamentCountsResponse extends DefaultResponse {
    private List<MedicamentCount> medicamentCounts;
}
