using CSharpHospitalREST.dto.disease.CreateDisease;
using CSharpHospitalREST.Service;
using Microsoft.AspNetCore.Mvc;

namespace CSharpHospitalREST.Controllers;
[ApiController]
[Route("diseases")]
public class DiseaseController(DiseaseService diseaseService) : ControllerBase


{
    [HttpPost(Name="CreateDisease")]
    public CreateDiseaseResponse CreateDisease([FromBody] CreateDiseaseRequest request)
    {
        try
        {
            diseaseService.CreateDisease(request);
            return new CreateDiseaseResponse(true, "success");
        }
        catch (Exception ex)
        {
            return new CreateDiseaseResponse(false, "Не удалось создать болезнь");
        }
    }
}