import json
from datetime import date

from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.decorators import api_view

from hospital.models import Patient, PatientDisease, Disease
from hospital.serializers import Covid19PatientSerializer, Covid19ReportSerializer


@api_view(['GET'])
def covid19patients(request):
    params = request.GET
    disease_name = params.get("diseaseName")
    date = params.get("date")

    patients = PatientDisease.objects.filter(
        disease__name=disease_name,
        endDate__lte=date
    )

    serializer = Covid19PatientSerializer(patients, many=True)
    obj = type('', (object,), {"covid19Patients": serializer.data})()
    serializer = Covid19ReportSerializer(obj)

    return JsonResponse(serializer.data, safe=False)

@api_view(['POST'])
def create_disease(request):
    try:
        body = json.loads(request.body.decode('utf-8'))
        Disease.objects.create(
            name=body.get("name"),
            description=body.get("description"),
        )
    except Exception as e:
        return JsonResponse({"result": False, "reason": "Не удалось создать болезнь"})
    return JsonResponse({"result": True, "reason": "success"})

@api_view(['POST'])
def create_patient(request):
    try:
        body = json.loads(request.body.decode('utf-8'))
        diseaseName = body.get("diseaseName")
        disease = Disease.objects.get(
            name = diseaseName
        )
        patient = Patient.objects.create(
            firstName=body.get("firstName"),
            lastName=body.get("lastName"),
            middleName=body.get("middleName"),
            birthDate=body.get("birthDate"),
        )
        PatientDisease.objects.create(
            patient = patient,
            disease = disease,
            startDate = date.today(),

        )
    except Exception as e:
        return JsonResponse({"result": False, "reason": "Не удалось создать пациента"})
    return JsonResponse({"result": True, "reason": "success"})
