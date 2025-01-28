import dataclasses
from datetime import datetime

from django.db.models import QuerySet

from hospital.models import PatientDisease





@dataclasses.dataclass
class Covid19PatientDTO:
    firstName: str
    lastName: str
    middleName: str
    birthDate: datetime.date
    disease_name: str
    diseaseStartDate: datetime.date
    diseaseEndDate: datetime.date
    diseaseFatalOutcome: bool
    medicaments: []

    @classmethod
    def fromPatientDisease(cls, pd: [QuerySet]):
        p = pd.patient
        d = pd.disease
        md = pd.medicaments

        return Covid19PatientDTO(p.firstName, p.lastName, p.middleName, p.birthDate,
                                 d.name, pd.startDate, pd.endDate, pd.fatalOutcome,
                                 [(m.name) for m in md.all()])