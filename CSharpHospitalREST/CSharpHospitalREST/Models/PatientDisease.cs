namespace CSharpHospitalREST.Models
{
    public class PatientDisease
    {
        public long Id { get; set; }

        public long PatientId { get; set; }
        public Patient Patient { get; set; }

        public long DiseaseId { get; set; }
        public Disease Disease { get; set; }
        
        public DateTime StartDate { get; set; }

        public DateTime EndDate { get; set; }
        public bool FatalOutcome { get; set; }
        
        public bool SentToEsb { get; set; }
    
        public List<Medicament> Medicaments { get; set; } = new List<Medicament>();


    }
}
