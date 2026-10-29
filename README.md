# Exception Safe Record Entry System

**Course:** PBSIT 002 Object Oriented Programming  
**Module:** Week 13 Hands-On Assignment  
**Topic:** Exception Handling, Input Validation, Custom Exceptions, and Safe Recovery  

---

## 📌 Project Overview
The **Exception Safe Record Entry System** is a robust Java console application designed to accept record entries without crashing or terminating when invalid or unsuitable data is supplied. It demonstrates key object-oriented design principles including data encapsulation, custom exception handling, fail-fast validation in constructors, and loop-based fault recovery.

---

## 📁 Repository Structure

```
ExceptionSafeRecords/
├── .gitignore
├── README.md
├── build.xml
├── Week_13_Test_Sheet.docx
├── Console_Transcript.txt
├── Exception_Handling_Explanation.txt
├── nbproject/
│   ├── project.properties
│   └── project.xml
└── src/
    ├── app/
    │   └── Application.java            # Main console application and user recovery loop
    ├── exception/
    │   └── DuplicateRecordException.java # Custom exception for duplicate primary keys
    ├── model/
    │   └── ItemRecord.java              # Encapsulated domain model with fail-fast validation
    └── service/
        └── RecordManager.java           # Record manager enforcing duplicate check business rules
```

---

## ⚙️ Key Technical Features

1. **Fail-Fast Domain Validation (`model/ItemRecord.java`)**:
   - Rejects `null` or blank unique identifiers with `IllegalArgumentException`.
   - Rejects `null` or blank record names with `IllegalArgumentException`.
   - Rejects non-positive quantities (`quantity < 1`) with `IllegalArgumentException`.

2. **Custom Exception (`exception/DuplicateRecordException.java`)**:
   - Custom checked exception thrown when an attempt is made to insert an identifier that already exists in memory.

3. **Service Layer Search & Duplicate Prevention (`service/RecordManager.java`)**:
   - Case-insensitive search using Java Streams (`equalsIgnoreCase`).
   - Searches existing records prior to insertion and throws `DuplicateRecordException` if matched.

4. **Fault Recovery & Guidance (`app/Application.java`)**:
   - Catches `NumberFormatException` during string-to-integer parsing for clear user feedback.
   - Encloses user input prompts in a retry loop (`while (running)`), ensuring application continuity after any exception.
   - Provides a graceful exit mechanism via typing `EXIT`.

---

## 🚀 How to Run

### Method 1: Apache NetBeans IDE
1. Open Apache NetBeans IDE.
2. Select **File > Open Project**.
3. Navigate to and select the `ExceptionSafeRecords` project folder.
4. Right-click the project in the Project Explorer and select **Run** (or press `F6`).

### Method 2: Command Line (Java CLI)
```bash
# Navigate to the project source directory
cd src

# Compile all packages
javac app/Application.java model/ItemRecord.java service/RecordManager.java exception/DuplicateRecordException.java

# Execute the application
java app.Application
```

---

## 🧪 Test Execution Matrix

| Test ID | Condition | Example Input | Expected Behavior | Actual Output | Status |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **EH01** | Valid record | `R001, Sample One, 5` | Record accepted and displayed | `Record added successfully.` | **PASS** |
| **EH02** | Blank identifier | `[blank], Sample Two, 2` | Clear validation message; record not added | `Validation Error: Identifier cannot be null or blank.` | **PASS** |
| **EH03** | Nonnumeric quantity | `R002, Sample Two, five` | Numeric guidance; program remains active | `Data Error: Quantity requires a valid numeric value.` | **PASS** |
| **EH04** | Zero quantity | `R003, Sample Three, 0` | Positive-value guidance; record not added | `Validation Error: Quantity must be a positive whole number (1 or greater).` | **PASS** |
| **EH05** | Duplicate identifier | `Add R001 again` | Duplicate message; original remains unchanged | `Unable to add record: Identifier R001 already exists.` | **PASS** |
| **EH06** | Recovery | `R004, Valid Entry, 1` (after EH03) | Valid entry succeeds without restarting | `Record added successfully.` | **PASS** |

---

## 📄 Submission Evidence
- `Week_13_Test_Sheet.docx`: Completed 6-row test sheet table.
- `Console_Transcript.txt`: Verbatim console execution transcript covering test cases EH01–EH06.
- `Exception_Handling_Explanation.txt`: Detailed explanation of detection points across layers.
