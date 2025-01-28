using CSharpHospitalREST.config.db;
using CSharpHospitalREST.Models;
using CSharpHospitalREST.Service;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using AutoMapper;
using CSharpHospitalREST.dto;
using CSharpHospitalREST.dto.AddPatient;
using CSharpHospitalREST.dto.GetPatients;

namespace CSharpHospitalREST.Controllers
{
    
    [ApiController]
    [Route("patients")]
    public class PatientsController(
        PatientService patientService,
        DiseaseService diseaseService,
        IMapper mapper) : ControllerBase
    {
        private const int GetPatientsPageSize = 20;
        
        
        [HttpGet(Name = "GetPatients")]
        public GetPatientsResponse GetPatients([FromQuery] GetPatientsRequest request)
        {
            try
            {
                var patientQuery = patientService.GetPatientsByDisease(request.Disease);
                var patients = patientQuery.Take(GetPatientsPageSize * request.Page).ToList();
                var patientsDto = mapper.Map<List<PatientDto>>(patients);
                return new GetPatientsResponse(true, "success", patientsDto);
            }
            catch (Exception e)
            {
                return new GetPatientsResponse(false, "Не удалось получить пользователей",null);
            }
            
        }
        
        [HttpPost(Name = "AddPatient")]
        public AddPatientResponse AddPatient([FromBody] AddPatientRequest addPatientRequest)
        {
            try
            {
                var diseaseName = addPatientRequest.DiseaseName;
                var disease = diseaseService.GetDiseaseByName(diseaseName);
                patientService.CreatePatient(addPatientRequest, disease);
                return new AddPatientResponse(true, "success");
            }
            catch (Exception e)
            {
                return new AddPatientResponse(false, "Не удалось создать пользователя");
            }
            
            
        }
        
        // [HttpPost(Name = "MedicamentsRequest")]
        // public async Task<ActionResult<IEnumerable<Patient>>> AddPatient(AddPatientDto addPatientDto)
        // {
        //     var disease = db.Diseases.FindAsync(addPatientDto.DiseaseName);
        //     db.Patients.AddAsync()
        // }
    }
}
