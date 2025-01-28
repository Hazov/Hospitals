using CSharpHospitalREST.Models;
using Microsoft.EntityFrameworkCore;


namespace CSharpHospitalREST.config.db
{
    public class HospitalDatabaseContext : DbContext
    {
        public HospitalDatabaseContext(DbContextOptions<HospitalDatabaseContext> options) : base(options)
        {
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<PatientDisease>()
                .HasOne(pd => pd.Patient)
                .WithMany(p => p.PatientDiseases)
                .HasForeignKey(pd => pd.PatientId); 

            modelBuilder.Entity<PatientDisease>()
                .HasOne(pd => pd.Disease)
                .WithMany(d => d.PatientDiseases) 
                .HasForeignKey(pd => pd.DiseaseId);

            modelBuilder.Entity<PatientDisease>().ToTable("PatientDiseases");


            modelBuilder.Entity<PatientDisease>()
                .HasMany(pd => pd.Medicaments)
                .WithMany(m => m.PatientDiseases)
                .UsingEntity(j => j.ToTable("PatientDiseaseMedicaments"));

        }

        public DbSet<Patient> Patients { get; set; }
        public DbSet<Medicament> Medicaments { get; set; }
        public DbSet<Disease> Diseases { get; set; }

        public DbSet<PatientDisease> PatientDiseases { get; set; }




    }
}
