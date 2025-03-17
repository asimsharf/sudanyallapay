# Sudan Yalla Pay - System Overview

## 1. Project Structure
### Directory Tree
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── sudagoarth
│   │   │           └── sudanyallapay
│   │   │               ├── BankAccounts
│   │   │               ├── Bills
│   │   │               ├── Cards
│   │   │               ├── Documents
│   │   │               ├── Enums
│   │   │               ├── Merchants
│   │   │               ├── Notifications
│   │   │               ├── Security
│   │   │               ├── Transactions
│   │   │               ├── Users
│   │   │               ├── Wallets
│   │   │               ├── exceptions
│   │   │               ├── utils
│   │   │               └── SudanyallapayApplication.java
│   │   └── resources
│   └── test
│       └── java
│           └── com
│               └── sudagoarth
│                   └── sudanyallapay
└── uploads
```

## 2. Login & Registration Screen
### Purpose: 
Allow users to register and log in securely.

### UI Elements:
- **Login Form:** Email/Phone, Password, "Forgot Password" link, Login Button.
- **Registration Form:** Full Name, Email, Phone Number, Password, Confirm Password, National ID (optional), Date of Birth.
- **Buttons:** "Login", "Register", "Continue with Google/Facebook".
- **Validation Messages:** Show when incorrect data is entered.

## 3. User Dashboard
### Purpose:
Show a summary of wallet balance, transactions, and quick actions.

### UI Elements:
- **Wallet Balance:** Display user balance in SDG currency.
- **Recent Transactions:** A scrollable list of the latest 5 transactions.
- **Quick Actions:** Buttons for Deposit, Withdraw, Transfer, Pay Bill.
- **Notifications Icon:** Displays unread messages.

## 4. Transactions Screen
### Purpose:
Show the history of all transactions with filtering options.

### UI Elements:
- **Filter by Type:** Dropdown menu for Deposit, Withdrawal, Transfer, Bill Payment, Top-up.
- **Transaction List:** Each item includes Transaction Type, Amount, Status (Pending, Completed, Failed), Date.
- **Search Bar:** Find transactions by reference number.

## 5. Wallet Management Screen
### Purpose:
Allow users to view and manage their wallet.

### UI Elements:
- **Balance Display:** Shows the available balance.
- **Linked Bank Accounts & Cards:** List of connected payment methods.
- **Deposit/Withdraw Buttons:** Enable adding or withdrawing funds.
- **Transaction History:** Filter transactions by Deposit, Withdrawal, Transfer, Bill Payment.

## 6. Bank Accounts & Cards Screen
### Purpose:
Allow users to add and manage their bank accounts & cards.

### UI Elements:
- **List of Added Accounts & Cards:** Show Bank Name, Account Number, Status (Active/Inactive).
- **Add New Account/Card Button:** Form with fields for Bank Name, Account Number, IBAN, SWIFT Code.
- **Card Management Section:** Show Card Number (masked), Expiry Date, Card Type (Debit, Credit, Prepaid).

## 7. Bill Payment Screen
### Purpose:
Allow users to pay their bills.

### UI Elements:
- **List of Pending Bills:** Show Biller Name, Due Date, Amount, Status (Pending/Paid).
- **Pay Now Button:** Opens a confirmation modal before processing payment.
- **Payment Receipt:** Display proof of payment after successful transactions.

## 8. Merchant Dashboard
### Purpose:
Allow businesses to manage their accounts & revenue.

### UI Elements:
- **Business Information:** Show Business Name, Registration Number, Contact Details.
- **Wallet Overview:** Show Current Balance & Recent Transactions.
- **Revenue Insights:** Graphs showing daily, weekly, monthly earnings.
- **Transaction History:** Similar to the user transaction screen but filtered for business payments.

## 9. Notifications Screen
### Purpose:
Show important messages and alerts to users.

### UI Elements:
- **List of Notifications:** Each item has Message, Date, Read/Unread Status.
- **Mark All as Read Button:** Clears unread notifications.
- **Clickable Items:** Redirect users to relevant actions (e.g., successful transactions, bill reminders).

## 10. Profile & Settings Screen
### Purpose:
Allow users to update their personal information.

### UI Elements:
- **Profile Picture:** Upload or update an image.
- **Personal Details:** Name, Email, Phone, Date of Birth, National ID (optional).
- **Security Settings:** Change Password, Enable Two-Factor Authentication (2FA).
- **Logout Button:** Ends the user session securely.

---

## Database Schema Overview
### **Users Table**
Stores user information including contact details, authentication, and profile settings.

### **Auth Security Table**
Handles OTP-based authentication and security measures.

### **Wallets Table**
Manages user balances, transactions, and linked accounts.

### **Transactions Table**
Stores all financial transactions, including deposits, withdrawals, and transfers.

### **Bank Accounts & Cards Table**
Holds user-linked payment methods for deposits and withdrawals.

### **Bills Table**
Manages bill payments and due payments associated with users.

### **Notifications Table**
Stores alerts and updates relevant to user activities.

### **Merchants Table**
Manages business accounts and their associated financial transactions.

🚀 **This document serves as a high-level breakdown of the Sudan Yalla Pay system architecture and user journey.**


# **What is NOT Covered in Sudan Yalla Pay Layered (N-Tier) Architecture [BankAccounts] from SOLID Principles?**  
The Sudan Yalla Pay project follows **most** of the SOLID principles, but there are some **gaps and potential improvements** to fully adhere to these principles.

---

## **1. Single Responsibility Principle (SRP) - ✅ Mostly Covered**  
The project generally follows **SRP** by separating concerns into **Controller, Service, and Repository layers**.  

### **🔴 Issue**:  
- **Logging and Business Logic in Controllers:**  
  - The `BankAccountController` logs information but also performs request validation and response formatting.
  - This means the controller has **two responsibilities**: handling HTTP requests **and** logging.

### **🚀 Improvement:**  
- Create a separate **Logging Service** to handle logging.  
- Ensure **validation** is handled separately using **Spring Boot validation annotations** (`@Valid`, `@Validated`) or **exception handlers**.

---

## **2. Open/Closed Principle (OCP) - ⚠️ Partially Covered**  
The implementation allows extensions without modifying existing classes in most cases. However, the **status update method** and **delete operation** are unimplemented.

### **🔴 Issue**:  
- The `deleteBankAccount()` and `statusBankAccount()` methods **throw `UnsupportedOperationException`**.
- If in the future these methods need to be implemented, **existing code will have to be modified instead of extending functionality**.

### **🚀 Improvement:**  
- Implement `deleteBankAccount()` using a **soft delete approach** (adding a `status=INACTIVE` instead of removing from DB).
- If `statusBankAccount()` will have multiple implementations, consider using **Strategy Pattern** to define different status update strategies.

---

## **3. Liskov Substitution Principle (LSP) - ✅ Well Covered**  
The Sudan Yalla Pay project follows **LSP** because `BankAccountService` **implements** `BankAccountInterface`, making it substitutable.  

### **🔴 Issue**:  
- The `BankAccountService` implementation **throws exceptions instead of handling edge cases properly**.
- For example, `getBankAccount()` **throws a `NotFoundException`**, but **doesn't provide alternative behavior**.

### **🚀 Improvement:**  
- Return **optional responses** instead of throwing exceptions in some cases.  
- If a bank account does not exist, return an **empty response with status `204 No Content` instead of `404 Not Found`**.

---

## **4. Interface Segregation Principle (ISP) - ⚠️ Partially Covered**  
The `BankAccountInterface` contains **all** operations for managing bank accounts.

### **🔴 Issue**:  
- The interface **forces all implementations to include every method** (`createBankAccount`, `updateBankAccount`, `deleteBankAccount`, `statusBankAccount`), even if they don’t need them.
- Some methods **aren't even implemented** (e.g., `deleteBankAccount()`).

### **🚀 Improvement:**  
- Split `BankAccountInterface` into **smaller, more specific interfaces**:
  - `BankAccountReadInterface` → `getBankAccounts()`, `getBankAccount()`
  - `BankAccountWriteInterface` → `createBankAccount()`, `updateBankAccount()`
  - `BankAccountStatusInterface` → `statusBankAccount()`
  - `BankAccountDeleteInterface` → `deleteBankAccount()`
  
This allows implementations to **only use the methods they need**, reducing unnecessary dependencies.

---

## **5. Dependency Inversion Principle (DIP) - ✅ Mostly Covered**  
The Sudan Yalla Pay project correctly **injects dependencies using interfaces** rather than concrete classes, which follows DIP.

### **🔴 Issue**:  
- `BankAccountService` **depends on `UserRepository` directly**, which is a lower-level module.  
- Ideally, the service layer should not **directly access repositories**, but instead work with an **abstraction** (like a `UserServiceInterface`).

### **🚀 Improvement:**  
- Create a **UserServiceInterface** and implement it in `UserService`.  
- Inject **UserServiceInterface** into `BankAccountService` instead of `UserRepository`.  

This ensures **loose coupling** and makes future changes **easier without modifying multiple layers**.

---

## **🚀 Summary of What’s Missing in Sudan Yalla Pay**  
| **SOLID Principle**  | **Coverage** | **Gaps** | **Suggested Fix** |
|----------------------|-------------|----------|--------------------|
| **SRP (Single Responsibility)** | ✅ **Mostly Covered** | Controller handles both logging & request validation. | Create a **LoggingService** and move logging logic. |
| **OCP (Open/Closed)** | ⚠️ **Partially Covered** | Unimplemented methods (`deleteBankAccount`, `statusBankAccount`). | Implement them or use **Strategy Pattern** for status updates. |
| **LSP (Liskov Substitution)** | ✅ **Well Covered** | Throws `NotFoundException` instead of handling missing data. | Return **empty responses (204 No Content)** for missing records. |
| **ISP (Interface Segregation)** | ⚠️ **Partially Covered** | `BankAccountInterface` forces all implementations to implement all methods. | Split into **smaller, more specific interfaces**. |
| **DIP (Dependency Inversion)** | ✅ **Mostly Covered** | `BankAccountService` depends on `UserRepository` directly. | Introduce **`UserServiceInterface`** and inject it instead. |

---

## **Final Verdict:**  
The Sudan Yalla Pay project **follows SOLID principles well but has minor gaps**. Applying the suggested **refinements** will **improve maintainability, flexibility, and scalability** of the system.

Would you like help **refactoring some of these areas**? 🚀😊

***Screen Capture PDF***

![Image Description](screencapture.png)