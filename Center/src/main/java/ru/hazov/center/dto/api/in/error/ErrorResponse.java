package ru.hazov.center.dto.api.in.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.hazov.center.dto.api.in.common.DefaultResponse;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ErrorResponse extends DefaultResponse {
    private String reason;

    public ErrorResponse(String reason) {
        this.reason = reason;
    }
}
