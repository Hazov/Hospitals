using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.dao
{
    public record PatientDao(
        string Name,
        string LastName,
        string? MiddleName,
        DateTime BirthDate,
        Boolean Gender,
        long DiseasePatientId,
        string DiseaseName,
        DateTime DiseaseStartDate,
        DateTime DiseaseEndDate,
        Boolean DiseaseFatalOutcome,
        List<Medicament> Medicaments,
        bool SentToEsb
        
        );
}
