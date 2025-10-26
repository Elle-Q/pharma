Drug
=====================
## directory
- [add_drug](#add_drug)


## add_drug

* status**`Checked`**
* developer **`Elle`**
* method **`POST`**
* address`/pharma/drugs`

> request

| Field          | Necessary  | Type   | Description         | Default  |
|----------------|------------|--------|---------------------|----------|
| name           | yes        | String | prescription number |          |
| batchNumber    | yes        | Int    | pharmacy id         |          |
| manufacturerId | yes        | String | doctor              |          |
| stock          | yes        | String | pharmacist          |          |
| expiryDate     | yes        | Date   | patient id          |          |
| description    | yes        | String | patient name        |          |




> request sample

```javascript
{
    "name": "Aspirin Enteric-coated Tablets",
        "manufacturerId": 1,
        "batchNumber": "XXXX2025000001",
        "expiryDate": "2026-12-31",
        "stock": 1000,
        "description": "Used for pain relief, fever reduction, and anti-inflammatory purposes"
}
```


> response sample

```javascript
{
    "code": "200",
        "msg": "success",
        "data": 1
}
```