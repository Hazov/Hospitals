import json
from datetime import date

from django.http import JsonResponse
from rest_framework.decorators import api_view

from hospital.models import Patient, PatientDisease, Disease, EOrder, Medicament
from hospital.serializers import Covid19PatientSerializer, Covid19ReportSerializer, CountSer, MedicamentCountSerializer, \
    OrderMedicamentSerializer


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

@api_view(['GET'])
def medicament_count(request):
    params = request.GET
    madicamentName = params.get("name")
    medicament = Medicament.objects.filter(name=madicamentName)
    serializer = MedicamentCountSerializer(medicament)
    return JsonResponse(serializer.data, safe=False)

@api_view(['POST'])
def order_medicament(request):
    try:
        body = json.loads(request.body.decode('utf-8'))
        count = body.get("count")
        medName = body.get("name")
        medicament = Medicament.objects.get(name=medName)
        order = EOrder.objects.create(medicament=medicament, count=count)
        order.save()
        serializer = OrderMedicamentSerializer(order)
        return JsonResponse(serializer.data, safe=False)
    except Exception as e:
        return JsonResponse({"result": False, "reason": "Не удалось создать заказ"})



@api_view(['POST'])
def reject_order_medicament(request):
    try:
        body = json.loads(request.body.decode('utf-8'))
        order_id = body.get("orderId")
        order = EOrder.objects.get(id=order_id)
        order.delete()
    except Exception as e:
        return JsonResponse({"result": False, "reason": "Не удалось удалить заказ"})
    return JsonResponse({"result": True, "reason": "success"})

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
        disease_name = body.get("diseaseName")
        disease = Disease.objects.get(
            name = disease_name
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
