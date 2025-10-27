# Pharma Supply Chain and Prescription Fulfillment System

## 📖 Project Overview
Pharmaceutical supply chain and prescription fulfillment systems to manage pharmaceutical inventory, prescriptions, and pharmacies, as well as handle the logistics of fulfilling patient prescriptions.

### 🎯 Core functions
- drug inventory management (manufacturer、expiry、stock)
- pharmacy contract and allocation management
- Prescription submission & fulfillment
- Auditlog record and analysis
- Concurrency control and data consistency

## 🛠 Prerequisites

### Env
- Java 17+
- Gradle 8.10+
- MySQL 8.0+
- Spring Boot 3.3.3
- Redis -- not available for now

### Database Schema
> sql path: /resources/SQL
- audit_log
- contract (will be renamed to pharmacy_drug_allocation) 
- drug
- manufacturer
- patient
- pharmacy
- prescription
- prescription_item


## ✨ ️Setup Instructions
1. clone repository:  `git@github.com:Elle-Q/pharma.git`
2. database setup (create table & insert mock data):
   path : `/resources/SQL/*.sql`
3. configure yml `application-dev.yml`:
```bash
spring:
  datasource:
    url: jdbc:mysql://192.168.31.110:3306/pharma_dev?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: Ggg123654.
```
4. run aplication


## 📚 ️️️API DOC
### Check detail here: `/resources/API`
- auditlog.md
- drug.md
- pharmacy.md
- prescription.md

## ❌testing Instructions

🙏`sorry no time to finish this part`
> partial complete
>>> PrescriptionProcessServiceTest


## 🔍 Code Quality with Checkstyle
### Check Rules
The project uses strict code quality checks, including:
- **Naming Conventions**: PascalCase for class names, camelCase for method names, UPPER_CASE for constants
- **Import Standards**: Avoid star imports, remove unused imports
- **Code Style**: Proper whitespace usage, brace conventions
- **Code Quality**: Method length ≤ 50 lines, parameter count ≤ 7, avoid magic numbers

```bash
# clean
./gradlew clean

# run code check
./gradlew checkstyleMain

# build
./gradlew build

# check report
open build/reports/checkstyle/main.html
```

## 💡Assumptions
### Business Rule Assumptions
1. **Authentication**: User authentication is handled by a separate module (all request are legal and valid)
2. **Authorization**: Role-based access control is implemented for different user types
3. **Authorization**: Prescription authentication is handled by a separate module (all prescription are legal and valid)

### System Assumptions
1. **Java Environment**: Java 17+ runtime is available on all deployment environments
2. **Database Connectivity**: Stable network connectivity to MySQL database is maintained
3. **Memory Requirements**: Sufficient heap memory is allocated for the application
4. **Redis Requirements**: Stable network connectivity to Redis database is maintained
