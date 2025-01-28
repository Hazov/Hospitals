
using CSharpHospitalREST.config.db;
using CSharpHospitalREST.dao;
using CSharpHospitalREST.dto.AddPatient;
using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.Service
{
    public class PatientService(HospitalDatabaseContext db)
    {
        const string Covid19Name = "covid19";
        public List<PatientDao> GetPatientsForCovid19Report()
        {
            return GetPatientsByDisease(Covid19Name).ToList();
        }

        internal IQueryable<PatientDao> GetPatientsByDisease(string diseaseName)
        {
            return (from p in db.Patients

                    join pd in db.PatientDiseases on p.Id equals pd.PatientId
                    join d in db.Diseases on pd.DiseaseId equals d.Id
                    where d.Name.Equals(diseaseName) where !pd.SentToEsb

                    select new PatientDao(p.Name, p.LastName, p.MiddleName,
                        p.BirthDate, p.Gender, pd.Id, d.Name,
                        pd.StartDate, pd.EndDate, pd.FatalOutcome, pd.Medicaments, pd.SentToEsb)
                );
        }

        public void CreatePatient(AddPatientRequest addPatientRequest, Disease disease)
        {
            var patient = new Patient
            {
                Name = addPatientRequest.Name,
                LastName = addPatientRequest.LastName,
                MiddleName = addPatientRequest.MiddleName,
                BirthDate = addPatientRequest.BirthDate,
                Gender = addPatientRequest.Gender
            };
            db.Patients.Add(patient);
            var patientDisease = new PatientDisease
            {
                Patient = patient,
                Disease = disease
            };
            db.PatientDiseases.Add(patientDisease);
            db.SaveChanges();
        }
    }

}
