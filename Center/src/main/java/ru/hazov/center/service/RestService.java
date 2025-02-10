package ru.hazov.center.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hazov.center.dto.api.out.medicament_count.EsbMedicamentCountsResponse;
import ru.hazov.center.dto.api.out.medicament_order.EsbMedicamentOrdersRequest;

@Service
public class RestService {
    @Value("${esb.url.medicamentCount}")
    private String esbMedicamentCountUrl;

    private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EsbMedicamentCountsResponse medicamentCountsEsbRequest(String medicamentName) {
        return restTemplate.getForObject(
                        esbMedicamentCountUrl + String.format("?medicament=%s", medicamentName),
                        EsbMedicamentCountsResponse.class
                );
    }

    public EsbMedicamentCountsResponse orderMedicamentEsbRequest(EsbMedicamentOrdersRequest esbMedicamentOrders) {
        return restTemplate.postForObject(
                esbMedicamentCountUrl, esbMedicamentOrders,
                EsbMedicamentCountsResponse.class
        );
    }
}
