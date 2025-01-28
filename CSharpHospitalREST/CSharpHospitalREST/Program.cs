
using Microsoft.EntityFrameworkCore;
using CSharpHospitalREST.config.db;
using CSharpHospitalREST.Service;
using Quartz;
using CSharpHospitalREST.Jobs;
using System.Reflection;

var builder = WebApplication.CreateBuilder(args);


builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddScoped<PatientService>();
builder.Services.AddScoped<EsbService>();
builder.Services.AddScoped<RestService>();
builder.Services.AddScoped<DiseaseService>();
builder.Services.AddAutoMapper(Assembly.GetExecutingAssembly());


// (Worker) Отправка отчета по covid19 пациентам на шину
builder.Services.AddQuartz(q =>
{

    var jobKey = new JobKey(nameof(Covid19EsbSender));
    q.AddJob<Covid19EsbSender>(opt => opt.WithIdentity(jobKey));

    q.AddTrigger(cfg => cfg.ForJob(jobKey)
    .WithIdentity("Covid19EsbSender-Trigger")
    .WithSimpleSchedule(x => x.WithIntervalInMinutes(1).RepeatForever()));
});

builder.Services.AddQuartzHostedService(q => q.WaitForJobsToComplete = true);

builder.Services.AddDbContext<HospitalDatabaseContext>(options =>
        options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));


var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();

