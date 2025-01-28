package ru.hazov.javahospitalsoap.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hazov.javahospitalsoap.transport.dao.Covid19PatientDAO;
import ru.hazov.javahospitalsoap.entity.Disease;
import ru.hazov.javahospitalsoap.entity.Patient;
import ru.hazov.javahospitalsoap.jaxb_model.*;
import ru.hazov.javahospitalsoap.util.Util;


import java.util.List;

@Component
public class PatientMapper {

    private final Util util;

    @Autowired
    public PatientMapper(Util util) {
        this.util = util;
    }

    public GetPatientsResponse createGetPatientsResponse(List<Patient> patients){
        GetPatientsResponse getPatientsResponse = new GetPatientsResponse();

        List<ru.hazov.javahospitalsoap.jaxb_model.Patient> xmlPatients = patients.stream().map(patient -> {
            ru.hazov.javahospitalsoap.jaxb_model.Patient xmlPatient = new ru.hazov.javahospitalsoap.jaxb_model.Patient();
            xmlPatient.setFirstName(patient.getFirstName());
            xmlPatient.setLastName(patient.getLastName());
            xmlPatient.setMiddleName(patient.getMiddleName());
            xmlPatient.setBirthDate(util.toXMLGregorianCalendar(patient.getBirthDate()));

            xmlPatient.getDiseaseInfo().addAll(patient.getDiseases().stream().map(diseasePatient -> {
                DiseaseInfo diseaseInfo = new DiseaseInfo();
                Disease disease = diseasePatient.getDisease();

                diseaseInfo.setFatalOutcome(diseasePatient.isFatalOutcome());
                diseaseInfo.setDiseaseName(disease.getName());
                return diseaseInfo;
            }).toList());

            return xmlPatient;
        }).toList();

        getPatientsResponse.getPatient().addAll(xmlPatients);
        return getPatientsResponse;
    }
    public Covid19PatientsReport createCovid19PatientsReport(List<Covid19PatientDAO> patientDAOs){
        Covid19PatientsReport covid19report = new Covid19PatientsReport();
        List<Covid19Patient> covid19Patients = patientDAOs.stream().map(patientDAO -> {
            Covid19Patient covid19Patient = new Covid19Patient();
            covid19Patient.setFirstName(patientDAO.getFirstName());
            covid19Patient.setLastName(patientDAO.getLastName());
            covid19Patient.setMiddleName(patientDAO.getMiddleName());
            covid19Patient.setBirthDate(util.toXMLGregorianCalendar(patientDAO.getBirthDate()));
            covid19Patient.setDiseaseFatalOutCome(patientDAO.isDiseaseFatalOutCome());
            covid19Patient.setDiseaseStartDate(util.toXMLGregorianCalendar(patientDAO.getDiseaseStartDate()));
            covid19Patient.setDiseaseEndDate(util.toXMLGregorianCalendar(patientDAO.getDiseaseEndDate()));
            List<Medicament> meds = patientDAO.getMedicaments().stream().map(
                    medicamentDAO -> {
                        Medicament medicament = new Medicament();
                        medicament.setName(medicamentDAO.getName());
                        return medicament;
                    }
            ).toList();
            covid19Patient.getMedicaments().addAll(meds);
            covid19Patient.setDiseaseFatalOutCome(patientDAO.isDiseaseFatalOutCome());
            return covid19Patient;
        }).toList();
        covid19report.getCovid19Patients().addAll(covid19Patients);
        return covid19report;
    }
}
