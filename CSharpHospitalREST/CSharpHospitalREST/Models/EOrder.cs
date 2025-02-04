namespace CSharpHospitalREST.Models
{
    public class EOrder
    {
        public long Id { get; set; }
        public Medicament Medicament { get; set; }
        public int? Count { get; set; }
        
    }
}