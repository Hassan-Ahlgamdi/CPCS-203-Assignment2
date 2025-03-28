# Employee Management System

![Java](https://img.shields.io/badge/Java-17-blue)
![OOP](https://img.shields.io/badge/OOP-4Pillars-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📝 Project Overview
A Java-based Employee Management System developed as part of the Object-Oriented Programming course at King Abdulaziz University. This system demonstrates advanced OOP principles including inheritance, polymorphism, abstraction, and interfaces.

## 🎯 Project Objectives
- Implement inheritance through abstract classes and subclasses
- Demonstrate polymorphism and dynamic binding
- Utilize ArrayList for dynamic employee management
- Apply the `instanceof` operator for type checking
- Implement interface for special permissions

## 📂 Project Structure
src/
├── main/
│ ├── Employee.java (Abstract class)
│ ├── Manager.java (Extends Employee, implements Approver)
│ ├── Developer.java (Extends Employee)
│ ├── Designer.java (Extends Employee)
│ ├── Approver.java (Interface)
│ ├── Leave.java (Leave record class)
│ └── EmployeeManagementSystem.java (Main system class)
test/
├── Input.txt (Test input file)
└── Output.txt (Expected output file)

Copy

## ✨ Key Features
- **Abstract Employee Class**: Base class with common attributes (name, ID, salary)
- **Specialized Employee Types**:
  - `Manager`: Can approve leaves (implements `Approver`)
  - `Developer`: 12% salary bonus
  - `Designer`: 10% salary bonus
- **Leave Management**: Track and approve employee leaves
- **Comprehensive Operations**:
  - Add/remove employees
  - Print all employees
  - Approve leaves
  - Mark employees as working
  - View leave records
