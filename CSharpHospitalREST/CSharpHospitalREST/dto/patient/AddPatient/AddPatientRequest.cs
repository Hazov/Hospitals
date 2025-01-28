namespace CSharpHospitalREST.dto.AddPatient;

public record AddPatientRequest(string Name, string LastName, string MiddleName, DateTime BirthDate, bool Gender, string DiseaseName)
{
    
}