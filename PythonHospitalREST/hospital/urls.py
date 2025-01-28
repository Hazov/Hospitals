from django.urls import path

from hospital import views

urlpatterns = [
     path('covid19PatientsReport', views.covid19patients, name='covid19patients' ),
     path('createDisease', views.create_disease, name='createDisease' ),
     path('createPatient', views.create_patient, name='createPatient' ),
]