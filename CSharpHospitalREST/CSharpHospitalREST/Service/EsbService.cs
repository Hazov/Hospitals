using AutoMapper;
using CSharpHospitalREST.dao;
using CSharpHospitalREST.dto;

namespace CSharpHospitalREST.Service
{
    public class EsbService(PatientService patientService, RestService restService, IMapper mapper)
    {
        public void SendCovid19Report()
        {
            List<PatientDao> covid19PatientsDao = patientService.GetPatientsForCovid19Report();
            List<PatientDto> covid19PatientDtos = mapper.Map<List<PatientDto>>(covid19PatientsDao); 
            restService.SendCovid19Patients(new Covid19PatientsReport(covid19PatientDtos));
           
        }

    }
}
