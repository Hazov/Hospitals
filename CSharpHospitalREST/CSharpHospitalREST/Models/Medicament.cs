namespace CSharpHospitalREST.Models
{
    public class Medicament
    {
        public long Id { get; set; }
        public string Name { get; set; }
        public int Count { get; set; }

        public List<PatientDisease> PatientDiseases { get; set; } = new List<PatientDisease>();
    }
}
