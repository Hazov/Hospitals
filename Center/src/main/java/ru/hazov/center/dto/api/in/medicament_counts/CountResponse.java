package ru.hazov.center.dto.api.in.medicament_counts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hazov.center.dto.api.in.common.DefaultResponse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CountResponse extends DefaultResponse {
    private int count;
}
