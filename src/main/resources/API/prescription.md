Prescription
=====================
## directory
- [submit_prescription](#submit_prescription)
- [fulfill_prescription](#fulfill_prescription)


## submit_prescription

* status**`Checked`**
* developer **`Elle`**
* method **`POST`**
* address`/pharma/prescriptions/submit`

> request

| Field          | Necessary  | Type    | Description         | Default |
|----------------|------------|---------|---------------------|---------|
| number         | yes        | String  | prescription number |         |
| pharmacyId     | yes        | Int     | pharmacy id         |         |
| doctorName     | yes        | String  | doctor              |         |
| pharmacistName | yes        | String  | pharmacist          |         |
| patientId      | yes        | Int     | patient id          |         |
| patientName    | yes        | String  | patient name        |         |
| diagnosis      | yes        | String  | diagnosis           |         |
| createTime     | yes        | String  | create time         |         |
| validDays      | yes        | Int     | valid days          |         |
| drugId         | yes        | Int     | drug id             |         |
| drugName       | yes        | String  | drug name           |         |
| dosage         | yes        | String  | dosage              |         |
| amount         | yes        | Int     | amount              |         |
| instruction    | yes        | String  | instruction         |         |



> request sample

```javascript
{
    "number": "XXXX202510250002",
        "pharmacyId": 1,
        "doctorName": "Dr.Wang",
        "pharmacistName": "Joshoa",
        "patientId": 2,
        "patientName": "Jason",
        "diagnosis": "Patient requires ongoing medication management and regular blood pressure monitoring.",
        "createTime": "2025-10-26T10:30:00",
        "validDays": 5,
        "items": [
        {
            "drugId": 2,
            "drugName": "Aspirin Enteric-coated Tablets",
            "dosage": "10mg/d",
            "amount": 50,
            "instruction": "Take one tablet daily in the morning"
        },
        {
            "drugId": 4,
            "drugName": "Metformin Hydrochloride Tablets",
            "dosage": "20mg/d",
            "amount": 3,
            "instruction": "twice a day"
        }
    ]
}
```


> response sample

```javascript
{
    "code": "0",
        "msg": "success",
        "data": null
}
```

## fulfill_prescription

* status**`Checked`**
* developer **`Elle`**
* method **`GET`**
* address`/pharma/prescriptions/{id}/fulfill`

> request sample

```
/pharma/prescriptions/7/fulfill
```


> response sample SUCCESS & FAIL

```javascript
{
    "code": "0",
        "msg": "success",
        "data": {
        "success": true,
            "failureReasons": null
    }
}
```

```javascript
{
    "code": "0",
        "msg": "success",
        "data": {
        "success": false,
            "failureReasons": [
            {
                "code": "PRESCRIPTION_NOT_FULFILLABLE",
                "message": "prescription not fulfillable : status is not submitted",
                "detail": {
                    "number": "XXXX202510250001",
                    "fulfilled_time": null,
                    "pharmacy_id": 1,
                    "patient_id": 1,
                    "expire_time": "2025-10-30T10:30:00",
                    "pharmacist_name": "Joshoa",
                    "doctor_name": "Dr.Ludwig",
                    "status": "FULFILLED"
                }
            }
        ]
    }
}
```