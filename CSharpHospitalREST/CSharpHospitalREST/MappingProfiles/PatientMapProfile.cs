using AutoMapper;
using CSharpHospitalREST.dao;
using CSharpHospitalREST.dto;
using CSharpHospitalREST.dto.patient.Covid19Report;
using CSharpHospitalREST.Models;

namespace CSharpHospitalREST.MappingProfiles
{
    public class PatientMapProfile : Profile

        
    {
        public PatientMapProfile() {
            CreateMap<PatientDao, PatientDto>();
            CreateMap<Medicament, MedicamentDto>();
        }
      
    }
}
