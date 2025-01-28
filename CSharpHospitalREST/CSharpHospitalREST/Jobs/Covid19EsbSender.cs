using CSharpHospitalREST.Service;
using Quartz;
using RestSharp;

namespace CSharpHospitalREST.Jobs
{
    public class Covid19EsbSender(EsbService esbService) : IJob

    {
        public Task Execute(IJobExecutionContext context)
        {
            esbService.SendCovid19Report();
            return Task.CompletedTask;
        }
    }
}
