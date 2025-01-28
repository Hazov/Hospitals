using System.Text.Json.Serialization;

namespace CSharpHospitalREST.dto;

public class Covid19PatientsReport(List<PatientDto> covid19Patients)
{
    [JsonPropertyName("covid19Patients")] 
    public List<PatientDto> Covid19Patients { get; set; } = covid19Patients;
};


