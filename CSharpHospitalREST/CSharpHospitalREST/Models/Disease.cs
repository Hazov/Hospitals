namespace CSharpHospitalREST.Models
{
    public class Disease
    {
        public long Id { get; set; }
        public string Name { get; set; }

        public string? Description { get; set; }

        public List<PatientDisease> PatientDiseases { get; set; } = new List<PatientDisease>();
    }
}
