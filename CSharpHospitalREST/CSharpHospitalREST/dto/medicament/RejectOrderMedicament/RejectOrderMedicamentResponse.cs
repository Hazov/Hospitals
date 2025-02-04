namespace CSharpHospitalREST.dto.medicament.RejectOrderMedicament;

public class RejectOrderMedicamentResponse
{
    public bool result { get; set; }
    public string reason { get; set; }
    public long orderId { get; set; }
    
}