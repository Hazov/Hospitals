from django.db import models

class Medicament(models.Model):
    name = models.CharField(max_length=100)
    count = models.IntegerField()
    class Meta:
        db_table = 'medicament'


class Disease(models.Model):
    name = models.CharField(unique=True, max_length=100)
    description = models.TextField(null=True, blank=True)

    class Meta:
        db_table = 'disease'

class Patient(models.Model):
    firstName = models.CharField(max_length=100)
    lastName = models.CharField(max_length=100)
    middleName = models.CharField(max_length=100)
    birthDate = models.DateField()
    diseases = models.ManyToManyField(Disease, through='PatientDisease')

    class Meta:
        db_table = 'patient'

class PatientDisease(models.Model):
    patient = models.ForeignKey(Patient, on_delete=models.CASCADE)
    disease = models.ForeignKey(Disease, on_delete=models.CASCADE)
    startDate = models.DateField()
    endDate = models.DateField(null=True, blank=True)
    fatalOutcome = models.BooleanField(default=False)
    medicaments = models.ManyToManyField(Medicament)

    class Meta:
        db_table = 'patientdisease'

class EOrder(models.Model):
    medicament = models.ForeignKey(Medicament, on_delete=models.CASCADE)
    count = models.IntegerField()








