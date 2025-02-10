
from rest_framework import serializers

from hospital.models import PatientDisease, Medicament, EOrder


class Covid19MedicamentSerializer(serializers.ModelSerializer):

    class Meta:
        model = Medicament
        fields = ['name']

class Covid19PatientSerializer(serializers.ModelSerializer):
    firstName = serializers.CharField(source='patient.firstName', read_only=True)
    lastName = serializers.CharField(source='patient.lastName', read_only=True)
    middleName = serializers.CharField(source='patient.middleName', read_only=True)
    birthDate = serializers.DateField(source='patient.birthDate', read_only=True)
    diseaseName = serializers.CharField(source='disease.name', read_only=True)
    diseaseStartDate = serializers.DateField(source='startDate', read_only=True)
    diseaseEndDate = serializers.DateField(source='endDate', read_only=True)
    diseaseFatalOutcome = serializers.BooleanField(source='fatalOutcome', read_only=True)
    medicaments = Covid19MedicamentSerializer(many=True, read_only=True)

    class Meta:
        model = PatientDisease
        fields = ['firstName', 'lastName', 'middleName', 'birthDate',
                  'diseaseName', 'diseaseStartDate', 'diseaseEndDate', 'diseaseFatalOutcome', 'medicaments']

class Covid19ReportSerializer(serializers.BaseSerializer):
    def to_representation(self, instance):
        return {"covid19Patients":instance.covid19Patients}


class MedicamentCountSerializer(serializers.ModelSerializer):
    class Meta:
        fields = ['count']
        model = Medicament

class MedicamentCountResponseSerializer(serializers.BaseSerializer):
    def to_representation(self, instance):
        return {"medicamentCountResponse": instance}

class OrderMedicamentSerializer(serializers.ModelSerializer):
    class Meta:
        fields = ['id']
        model = EOrder

