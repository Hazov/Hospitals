using System.Text.Encodings.Web;
using System.Text.Json;
using CSharpHospitalREST.dto;
using RestSharp;

namespace CSharpHospitalREST.Service
{
    public class RestService(IConfiguration config)
    {
        public void SendCovid19Patients(Covid19PatientsReport covid19PatientsReport)
        {
            var client = new RestClient(config["EsbEndpoint"]!);

            var request = new RestRequest("/");
            
            JsonSerializerOptions options = new JsonSerializerOptions
            {
                Encoder = JavaScriptEncoder.UnsafeRelaxedJsonEscaping,
            };
            string jsonToSend = JsonSerializer.Serialize(covid19PatientsReport, options);
            request.AddParameter("application/json", jsonToSend, ParameterType.RequestBody);
            request.RequestFormat = DataFormat.Json;

            client.Execute(request, Method.Post);
        }
    }
}
