using System.ComponentModel.DataAnnotations.Schema;

namespace CSharpHospitalREST.Models
{
    public class Patient
    {
        public long Id { get; set; }
    
        public string Name { get; set; }
        public string? LastName { get; set; }

        public string? MiddleName { get; set; }

        public bool Gender { get; set; }

        public DateTime BirthDate { get; set; }


        public List<PatientDisease> PatientDiseases { get; set; } = new List<PatientDisease>();


    }
}
