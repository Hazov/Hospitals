using CSharpHospitalREST.dto.medicament;
using CSharpHospitalREST.dto.medicament.OrderMedicament;
using CSharpHospitalREST.dto.medicament.RejectOrderMedicament;
using CSharpHospitalREST.Models;
using CSharpHospitalREST.Service;
using Microsoft.AspNetCore.Mvc;

namespace CSharpHospitalREST.Controllers;

[ApiController]
[Route("medicament")]
public class MedicamentController(MedicamentService medicamentService, EOrderService eOrderService)
{
    [HttpGet(Name = "GetMedicamentCount")]
    public MedicamentCountResponse GetMedicamentCount([FromQuery] MedicamentCountRequest request)
    {
        MedicamentCountResponse response = new MedicamentCountResponse();
       
        var medicament = medicamentService.GetMedicamentByName(request.name);
        response.name = medicament.Name!;
        response.count = medicament.Count;
        return response;
       
            
    }
    
    [HttpPost(Name = "OrderMedicament")]
    public OrderMedicamentResponse OrderMedicament([FromBody] OrderMedicamentRequest request)
    {
        OrderMedicamentResponse response = new OrderMedicamentResponse();
        var medicament = medicamentService.GetMedicamentByName(request.name);
        var eOrder = eOrderService.CreateOrder(medicament, request.count);
        response.orderId = eOrder.Id;
        return response;
    }
    
    [HttpPost(Name = "RejectOrderMedicament")]
    public RejectOrderMedicamentResponse RejectOrderMedicament([FromBody] RejectOrderMedicamentRequest request)
    {
        var response = new RejectOrderMedicamentResponse();
        eOrderService.DeleteOrder(request.orderId); 
        response.result = true;
        response.orderId = request.orderId;
        response.reason = "success";
        return response;
        
    }
}