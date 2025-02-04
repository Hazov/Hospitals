package ru.hazov.center.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.hazov.center.dto.medicament_counts.MedicamentCountsResponse;
import ru.hazov.center.kafka_events.KafkaEvent;

@Service
public class RestService {
    @Value("${esb.url.medicamentCount}")
    private String esbMedicamentCountUrl;
    @Value("${esb.url.hospitalsKafka}")
    private String hospitalsKafkaUrl;

    private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MedicamentCountsResponse medicamentCountsEsbRequest(String medicamentName) {
        MedicamentCountsResponse response =
                restTemplate.getForObject(
                        esbMedicamentCountUrl + String.format("?medicament=%s", medicamentName),
                        MedicamentCountsResponse.class
                );
        System.out.println(response);


    }

    public void toHospitals(Object o) {

        String s = restTemplate.postForObject(hospitalsKafkaUrl, new KafkaEvent(o), String.class);

    }
}
