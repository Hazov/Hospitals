using CSharpHospitalREST.config.db;
using CSharpHospitalREST.dto.disease.CreateDisease;
using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.Service;

public class DiseaseService(HospitalDatabaseContext db)
{
    public Disease? GetDiseaseByName(string diseaseName)
    {
        return db.Diseases.First(d => d.Name.Equals(diseaseName));
    }
    
    public Disease CreateDisease(CreateDiseaseRequest request)
    {
        var disease = new Disease
        {
            Name = request.Name,
            Description = request.Description,
        };
        db.Diseases.Add(disease);
        return disease;
    }
    
}