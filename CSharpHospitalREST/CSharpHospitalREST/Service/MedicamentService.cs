using CSharpHospitalREST.config.db;
using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.Service;

public class MedicamentService(HospitalDatabaseContext db)
{
    internal Medicament GetMedicamentByName(string name)
    {
        return db.Medicaments.First(m => m.Name == name);
        
    }
}