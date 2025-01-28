using System.Text.Json.Serialization;
using CSharpHospitalREST.dto.patient.Covid19Report;

namespace CSharpHospitalREST.dto
{
    public class PatientDto(
        string name,
        string lastName,
        string? middleName,
        DateTime birthDate,
        bool gender,
        string diseaseName,
        DateTime diseaseStartDate,
        DateTime diseaseEndDate,
        bool diseaseFatalOutcome)
    {
        [JsonPropertyName("name")] 
        public string Name {get; set;} = name;

        [JsonPropertyName("lastName")] 
        public string LastName {get; set;} = lastName;

        [JsonPropertyName("middleName")] 
        public string? MiddleName {get; set;} = middleName;

        [JsonPropertyName("birthDate")] 
        public DateTime BirthDate {get; set;} = birthDate;

        [JsonPropertyName("gender")] 
        public Boolean Gender {get; set;} = gender;

        [JsonPropertyName("diseaseName")] 
        public string DiseaseName {get; set;} = diseaseName;

        [JsonPropertyName("diseaseStartDate")] 
        public DateTime DiseaseStartDate {get; set;} = diseaseStartDate;

        [JsonPropertyName("diseaseEndDate")] 
        public DateTime DiseaseEndDate {get; set;} = diseaseEndDate;

        [JsonPropertyName("diseaseFatalOutcome")] 
        public Boolean DiseaseFatalOutcome {get; set;} = diseaseFatalOutcome;

        [JsonPropertyName("medicaments")] 
        public List<MedicamentDto> Medicaments {get; set;}
    };
}