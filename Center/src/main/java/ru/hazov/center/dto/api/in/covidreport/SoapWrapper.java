package ru.hazov.center.dto.api.in.covidreport;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SoapWrapper<T> {

    @JsonAlias({ "Covid19PatientsReport" })
    private T child;
}
