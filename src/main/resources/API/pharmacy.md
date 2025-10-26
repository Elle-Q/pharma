Pharmacy
=====================
## directory
- [pharmacies_with_contracts](#pharmacies_with_contracts)


## pharmacies_with_contracts

* status**`Checked`**
* developer **`Elle`**
* method **`POST`**
* address`/pharma/pharmacies/with-contracts`

> response sample

```javascript
{
    "code": "200",
        "msg": "success",
        "data": [
        {
            "pharmacy": {
                "id": 4,
                "name": "Shenzhen People's Hospital Pharmacy",
                "address": "1017 Dongmen North Road, Luohu District, Shenzhen",
                "telephone": "0755-25533018",
                "manager": "Kevin Chen"
            },
            "contractDrugs": []
        },
        {
            "pharmacy": {
                "id": 3,
                "name": "Guangzhou Zhongshan Pharmacy",
                "address": "58 Zhongshan Er Road, Yuexiu District, Guangzhou",
                "telephone": "020-87332233",
                "manager": "Michael Wang"
            },
            "contractDrugs": [
                {
                    "id": 1,
                    "name": "Aspirin Enteric-coated Tablets",
                    "batchNumber": "ASPI2025000001",
                    "manufacturer": "Bayer Healthcare",
                    "description": "Used for pain relief, fever reduction, and anti-inflammatory purposes",
                    "expiryDate": "2026-12-31",
                    "allocatedAmount": 1000,
                    "usedAmount": 900
                }
            ]
        },
        {
            "pharmacy": {
                "id": 2,
                "name": "Shanghai Huashan Pharmacy",
                "address": "433 Huashan Road, Jingan District, Shanghai",
                "telephone": "021-52889999",
                "manager": "Lisa Li"
            },
            "contractDrugs": [
                {
                    "id": 1,
                    "name": "Aspirin Enteric-coated Tablets",
                    "batchNumber": "ASPI2025000001",
                    "manufacturer": "Bayer Healthcare",
                    "description": "Used for pain relief, fever reduction, and anti-inflammatory purposes",
                    "expiryDate": "2026-12-31",
                    "allocatedAmount": 500,
                    "usedAmount": 100
                }
            ]
        },
        {
            "pharmacy": {
                "id": 1,
                "name": "Beijing Union Medical Pharmacy",
                "address": "1 Shuai Fu Yuan, Dongcheng District, Beijing",
                "telephone": "010-69151188",
                "manager": "David Zhang"
            },
            "contractDrugs": [
                {
                    "id": 1,
                    "name": "Aspirin Enteric-coated Tablets",
                    "batchNumber": "ASPI2025000001",
                    "manufacturer": "Bayer Healthcare",
                    "description": "Used for pain relief, fever reduction, and anti-inflammatory purposes",
                    "expiryDate": "2026-12-31",
                    "allocatedAmount": 2000,
                    "usedAmount": 0
                },
                {
                    "id": 2,
                    "name": "Aspirin Enteric-coated Tablets",
                    "batchNumber": "ASPI2025000001",
                    "manufacturer": "Pfizer",
                    "description": "Used for pain relief, fever reduction, and anti-inflammatory purposes",
                    "expiryDate": "2026-12-31",
                    "allocatedAmount": 1000,
                    "usedAmount": 200
                },
                {
                    "id": 3,
                    "name": "Amoxicillin Capsules",
                    "batchNumber": "AMOX2024000001",
                    "manufacturer": "Pfizer",
                    "description": "Broad-spectrum antibiotic for bacterial infections",
                    "expiryDate": "2024-08-15",
                    "allocatedAmount": 2000,
                    "usedAmount": 500
                }
            ]
        }
    ]
}
```