Prescription
=====================
## directory
- [submit_prescription](#submit_prescription)
- [fulfill_prescription](#fulfill_prescription)


## submit_prescription

* status**`Checked`**
* developer **`Elle`**
* method **`POST`**
* address`/pharma/auditlogs`

> request

| Field        | Necessary   | Type     | Description       | Default  |
|--------------|-------------|----------|-------------------|----------|
| pharmacyId   | no          | Int      | pharmacy id       |          |
| patientId    | no          | Int      | patient id        |          |
| status       | no          | String   | 'fail','success'  |          |



> request sample

```javascript
{
    "patientId": 1,
    "status": "success", 
    "pharmacyId": 1
}
```


> response sample

```javascript
{
    "code": "0",
        "msg": "success",
        "data": [
        {
            "operationType": "PRESCRIPTION_FULFILL_SUCCESS",
            "prescriptionId": 7,
            "patientId": 1,
            "pharmacyId": 1,
            "operatorName": "Joshoa",
            "status": "success",
            "drugsRequested": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": 1,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "drugsDispensed": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": 1,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "failureReasons": null,
            "createTime": "2025-10-26T16:43:56",
            "updateTime": "2025-10-26T16:43:56"
        },
        {
            "operationType": "PRESCRIPTION_SUBMIT_SUCCESS",
            "prescriptionId": 7,
            "patientId": 1,
            "pharmacyId": 1,
            "operatorName": "Joshoa",
            "status": "success",
            "drugsRequested": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": 1,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "drugsDispensed": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": 1,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "failureReasons": null,
            "createTime": "2025-10-26T16:43:31",
            "updateTime": "2025-10-26T16:43:31"
        },
        {
            "operationType": "PRESCRIPTION_SUBMIT_SUCCESS",
            "prescriptionId": 7,
            "patientId": 1,
            "pharmacyId": 1,
            "operatorName": "Joshoa",
            "status": "success",
            "drugsRequested": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": null,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "drugsDispensed": [
                {
                    "id": 1,
                    "prescriptionId": 7,
                    "pharmacyId": null,
                    "drugId": 1,
                    "drugName": "Aspirin Enteric-coated Tablets",
                    "dosage": "10mg/d",
                    "amount": 5,
                    "instruction": "Take one tablet daily in the morning"
                }
            ],
            "failureReasons": null,
            "createTime": "2025-10-26T14:57:35",
            "updateTime": "2025-10-26T14:57:35"
        }
    ]
}
```
