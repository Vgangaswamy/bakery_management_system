### Bakery Management System - README

---

#### **Project Overview**
The **Bakery Management System** is a Java EE-based web application designed to manage bakery operations efficiently. It provides role-based functionality for customers and administrators, including product browsing, order management, and inventory control.

---

### **Features**

#### **Customer Features**
- Browse available bakery products added by the admin.
- Search for products using a keyword (name or description) in the customer section.
- Add products to a cart and place orders.
- Check cart to add and delete products.
#### **Admin Features**
- Add, edit, and delete bakery products.
- Manage inventory.
- Monitor product availability.

---

### **Technologies Used**
- **Java EE**: Jakarta Faces (JSF), Jakarta Persistence (JPA), Jakarta Security (Role-Based Access).
- **Application Server**: Payara 6.2023.
- **Database**: MySQL (or compatible RDBMS).
- **Frontend**: JSF, Bootstrap for UI design.
- **Build Tool**: Maven.
- **Mail Integration**: JavaMail API.
- **Languages**: Java 17, XHTML.

---

### **Project Structure**

#### **Main Modules**
1. **Persistence Layer**:
   - JPA entities for database mapping (`Customer`, `Bakery`, `CustomerOrder`, etc.).
   - Relationships include One-to-Many (e.g., `CustomerOrder` → `Bakery`).

2. **Service Layer**:
   - Business logic for managing bakery items (`BakeryService`), customers (`CustomerService`), and orders (`CustomerOrderService`).

3. **Presentation Layer**:
   - JSF-based web pages for user interaction.
   - Secure navigation with role-based access.

4. **Security**:
   - Role-based authentication (`Admin`, `Customer`).
   - Uses container-based security with groups and permissions.

---

### **Installations**

#### **1. Prerequisites**
- **Java**: JDK 17+
- **Payara Server**: 6.2023
- **Maven**: 3.8+
- **Database**: MySQL (or similar).

#### **2. Database Setup**
1. Create a database schema using the provided SQL scripts.
2. Update `persistence.xml` with your database credentials:
   ```xml
   <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bakery_db" />
   <property name="jakarta.persistence.jdbc.user" value="root" />
   <property name="jakarta.persistence.jdbc.password" value="password" />
   ```

#### **3. Build the Application**
1. Clone the project repository.
2. Open the project in IntelliJ IDEA or another IDE.
3. Run the following Maven command:
   ```bash
   mvn clean install
   ```

#### **4. Deploy**
1. Copy the generated WAR file to the `webapps` directory of your Payara server.
2. Start the server and navigate to:
   ```
   http://localhost:8080/bakery-management
   ```

---

### **Navigation Flow**

#### **1. Home Page**
- Links to customer and admin login pages.
- Shows a welcome message with project details.

#### **2. Customer Dashboard**
- Displays available bakery items in a data table.
- Provides options to add items to a cart and search for exisiting products.

#### **3. Admin Dashboard**
- Displays the list of bakery items with "Edit" and "Delete" buttons.
- Includes a "Create New Product" button to add new items.

#### **4. Cart Page**
- Displays the list of items in the customer's cart.
- Includes a confirm order option.

#### **5. Order Confirmation**
- Displays a summary of the placed order with the user information.

---

### **Key Functionality**

#### **1. Search Functionality**
- Customers can search for products by name or description.
- Uses a `searchKeyword` field to filter products dynamically.

#### **2. Role-Based Security**
- Admins can manage inventory and add/edit/delete products.
- Customers can only browse products and place orders.

#### **3. Dynamic Table Actions**
- Admins can delete products directly from the product table.
- Customers can add products to their cart using an "Add to Cart" button.

#### **4. Order Management**
- Each order is associated with a customer and contains multiple bakery items.
- Orders are saved in the database with total prices calculated.


---

### **Developer Notes**

#### **Known Issues**
- Edge case: Concurrent edits on the same product may overwrite data.
- Database must be pre-seeded with initial product data for full functionality.
- Delete the database before every run please run the below code 
```bash  
drop database itmd4515; create database itmd4515; use itmd4515; 
```
- To login as admin, use 
```bash  
admin as username and password
```
-  To login as cus, use 
```bash  
cus1 as username and password
```
-  To login as bakery owner, use 
```bash  
bakery1 as username and password
```

#### **Future Enhancements**
- Add WebSocket notifications for real-time updates.
- Enhance security by adding CAPTCHA for login.

#### **Tools Used**
- IntelliJ IDEA for development.
- Payara for deployment.
- Bootstrap for frontend styling.

---




