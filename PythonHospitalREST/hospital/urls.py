from django.urls import path

from hospital import views
urlpatterns = [
     path('covid19PatientsReport', views.covid19patients, name='covid19patients' ),
     path('createDisease', views.create_disease, name='createDisease' ),
     path('createPatient', views.create_patient, name='createPatient' ),
     path('medicamentCount', views.medicament_count, name='medicamentCount' ),
     path('orderMedicament', views.order_medicament, name='orderMedicament' ),
     path('rejectOrderMedicament', views.reject_order_medicament, name='rejectOrderMedicament' ),

]

