# **Sudan Yalla Pay - UI/UX Wireframe & Design Guide**

***How to build and run***

```bash
mvn clean install
mvn spring-boot:run
```

***Swagger Path***

[[Swagger](http://localhost:4000/swagger-ui/index.html)]


## **📌 1. Textual Breakdown (Screen-by-Screen Explanation)**  

### **1️⃣ Login & Registration Screen**  
**🔹 Purpose:** Allow users to register and log in securely.  

📌 **UI Elements:**  
- **Login Form**: Email/Phone, Password, "Forgot Password" link, Login Button.  
- **Registration Form**: Full Name, Email, Phone Number, Password, Confirm Password, National ID (optional), Date of Birth.  
- **Buttons:** "Login", "Register", "Continue with Google/Facebook".  
- **Validation Messages:** Show when incorrect data is entered.  

---

### **2️⃣ User Dashboard**  
**🔹 Purpose:** Show a summary of wallet balance, transactions, and quick actions.  

📌 **UI Elements:**  
- **Wallet Balance:** Display user balance in **SDG currency**.  
- **Recent Transactions:** A scrollable list of the latest 5 transactions.  
- **Quick Actions:** Buttons for **Deposit, Withdraw, Transfer, Pay Bill**.  
- **Notifications Icon:** Displays unread messages.  

---

### **3️⃣ Transactions Screen**  
**🔹 Purpose:** Show the history of all transactions with filtering options.  

📌 **UI Elements:**  
- **Filter by Type:** Dropdown menu for **Deposit, Withdrawal, Transfer, Bill Payment, Top-up**.  
- **Transaction List:** Each item includes **Transaction Type, Amount, Status (Pending, Completed, Failed), Date**.  
- **Search Bar:** Find transactions by reference number.  

---

### **4️⃣ Wallet Management Screen**  
**🔹 Purpose:** Allow users to view and manage their wallet.  

📌 **UI Elements:**  
- **Balance Display:** Shows the available balance.  
- **Linked Bank Accounts & Cards:** List of connected payment methods.  
- **Deposit/Withdraw Buttons:** Enable adding or withdrawing funds.  
- **Transaction History:** Filter transactions by **Deposit, Withdrawal, Transfer, Bill Payment**.  

---

### **5️⃣ Bank Accounts & Cards Screen**  
**🔹 Purpose:** Allow users to add and manage their bank accounts & cards.  

📌 **UI Elements:**  
- **List of Added Accounts & Cards:** Show **Bank Name, Account Number, Status (Active/Inactive)**.  
- **Add New Account/Card Button:** Form with fields for **Bank Name, Account Number, IBAN, SWIFT Code**.  
- **Card Management Section:** Show **Card Number (masked), Expiry Date, Card Type (Debit, Credit, Prepaid)**.  

---

### **6️⃣ Bill Payment Screen**  
**🔹 Purpose:** Allow users to pay their bills.  

📌 **UI Elements:**  
- **List of Pending Bills:** Show **Biller Name, Due Date, Amount, Status (Pending/Paid)**.  
- **Pay Now Button:** Opens a confirmation modal before processing payment.  
- **Payment Receipt:** Display proof of payment after successful transactions.  

---

### **7️⃣ Merchant Dashboard**  
**🔹 Purpose:** Allow businesses to manage their accounts & revenue.  

📌 **UI Elements:**  
- **Business Information:** Show **Business Name, Registration Number, Contact Details**.  
- **Wallet Overview:** Show **Current Balance & Recent Transactions**.  
- **Revenue Insights:** Graphs showing **daily, weekly, monthly earnings**.  
- **Transaction History:** Similar to the user transaction screen but filtered for business payments.  

---

### **8️⃣ Notifications Screen**  
**🔹 Purpose:** Show important messages and alerts to users.  

📌 **UI Elements:**  
- **List of Notifications:** Each item has **Message, Date, Read/Unread Status**.  
- **Mark All as Read Button:** Clears unread notifications.  
- **Clickable Items:** Redirect users to relevant actions (e.g., successful transactions, bill reminders).  

---

### **9️⃣ Profile & Settings Screen**  
**🔹 Purpose:** Allow users to update their personal information.  

📌 **UI Elements:**  
- **Profile Picture:** Upload or update an image.  
- **Personal Details:** Name, Email, Phone, Date of Birth, National ID (optional).  
- **Security Settings:** Change Password, Enable Two-Factor Authentication (2FA).  
- **Logout Button:** Ends the user session securely.  

---

## **📌 2. Hand-Drawn Wireframe (ASCII Representation)**  

```plaintext
-------------------------------------------------
| Sudan Yalla Pay        | [🔔 Notifications]  |
-------------------------------------------------
| Wallet Balance: SDG 10,500                    |
-------------------------------------------------
| Quick Actions                                 |
| [Deposit] [Withdraw] [Transfer] [Pay Bill]   |
-------------------------------------------------
| Recent Transactions                           |
| --------------------------------------------- |
| | ✅ Transfer | + SDG 500.00 | Completed   |
| | ❌ Bill Pay | - SDG 200.00 | Failed      |
| | ⏳ Withdraw | - SDG 1,000.00 | Pending  |
| --------------------------------------------- |
-------------------------------------------------
```

---

## **📌 3. How to Create This Wireframe in Figma, Adobe XD, or Sketch**  

### **🔹 Using Figma**  
1. **Go to [figma.com](https://www.figma.com/) and create a free account.**  
2. **Create a new project** and select **Frame (F)** to define screen size.  
3. **Use Rectangles & Text** to draw buttons, inputs, and UI elements.  
4. **Add Icons** from Figma’s built-in asset library.  
5. **Use Auto Layout** to align elements properly.  
6. **Save and Export as PDF or Image.**  

---

### **🔹 Using Adobe XD**  
1. **Download Adobe XD (Free Version)** from [Adobe](https://www.adobe.com/products/xd.html).  
2. **Create a New Artboard** (iPhone/Android/Web).  
3. **Use Shape Tools** to create input fields, buttons, and sections.  
4. **Import Icons** from Material Design or FontAwesome.  
5. **Use Components & Prototypes** for interactivity.  
6. **Export the wireframe for sharing.**  

---

### **🔹 Using Sketch**  
1. **Download Sketch (Mac Only)** from [sketch.com](https://www.sketch.com/).  
2. **Create a New Project** and set the canvas size.  
3. **Use the Shape Tool** to create layout components.  
4. **Add Text Layers** for labels, buttons, and descriptions.  
5. **Use Symbols** to reuse UI components (e.g., buttons).  
6. **Export as PNG, SVG, or PDF.**  

---

🚀 **Now you can start designing Sudan Yalla Pay’s UI/UX!** Let me know if you need further details or improvements. 🎯


***Screen Capture PDF***

![Image Description](screencapture.png)