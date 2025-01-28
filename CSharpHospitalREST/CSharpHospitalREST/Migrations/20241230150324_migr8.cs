using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace CSharpHospitalREST.Migrations
{
    /// <inheritdoc />
    public partial class migr8 : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_MedicamentPatientDisease_Medicaments_MedicamentsId",
                table: "MedicamentPatientDisease");

            migrationBuilder.DropForeignKey(
                name: "FK_MedicamentPatientDisease_PatientDiseases_PatientDiseasesId",
                table: "MedicamentPatientDisease");

            migrationBuilder.DropPrimaryKey(
                name: "PK_MedicamentPatientDisease",
                table: "MedicamentPatientDisease");

            migrationBuilder.RenameTable(
                name: "MedicamentPatientDisease",
                newName: "PatientDiseaseMedicaments");

            migrationBuilder.RenameIndex(
                name: "IX_MedicamentPatientDisease_PatientDiseasesId",
                table: "PatientDiseaseMedicaments",
                newName: "IX_PatientDiseaseMedicaments_PatientDiseasesId");

            migrationBuilder.AlterColumn<string>(
                name: "Name",
                table: "Medicaments",
                type: "text",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "text");

            migrationBuilder.AddPrimaryKey(
                name: "PK_PatientDiseaseMedicaments",
                table: "PatientDiseaseMedicaments",
                columns: new[] { "MedicamentsId", "PatientDiseasesId" });

            migrationBuilder.AddForeignKey(
                name: "FK_PatientDiseaseMedicaments_Medicaments_MedicamentsId",
                table: "PatientDiseaseMedicaments",
                column: "MedicamentsId",
                principalTable: "Medicaments",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_PatientDiseaseMedicaments_PatientDiseases_PatientDiseasesId",
                table: "PatientDiseaseMedicaments",
                column: "PatientDiseasesId",
                principalTable: "PatientDiseases",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_PatientDiseaseMedicaments_Medicaments_MedicamentsId",
                table: "PatientDiseaseMedicaments");

            migrationBuilder.DropForeignKey(
                name: "FK_PatientDiseaseMedicaments_PatientDiseases_PatientDiseasesId",
                table: "PatientDiseaseMedicaments");

            migrationBuilder.DropPrimaryKey(
                name: "PK_PatientDiseaseMedicaments",
                table: "PatientDiseaseMedicaments");

            migrationBuilder.RenameTable(
                name: "PatientDiseaseMedicaments",
                newName: "MedicamentPatientDisease");

            migrationBuilder.RenameIndex(
                name: "IX_PatientDiseaseMedicaments_PatientDiseasesId",
                table: "MedicamentPatientDisease",
                newName: "IX_MedicamentPatientDisease_PatientDiseasesId");

            migrationBuilder.AlterColumn<string>(
                name: "Name",
                table: "Medicaments",
                type: "text",
                nullable: false,
                defaultValue: "",
                oldClrType: typeof(string),
                oldType: "text",
                oldNullable: true);

            migrationBuilder.AddPrimaryKey(
                name: "PK_MedicamentPatientDisease",
                table: "MedicamentPatientDisease",
                columns: new[] { "MedicamentsId", "PatientDiseasesId" });

            migrationBuilder.AddForeignKey(
                name: "FK_MedicamentPatientDisease_Medicaments_MedicamentsId",
                table: "MedicamentPatientDisease",
                column: "MedicamentsId",
                principalTable: "Medicaments",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);

            migrationBuilder.AddForeignKey(
                name: "FK_MedicamentPatientDisease_PatientDiseases_PatientDiseasesId",
                table: "MedicamentPatientDisease",
                column: "PatientDiseasesId",
                principalTable: "PatientDiseases",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
