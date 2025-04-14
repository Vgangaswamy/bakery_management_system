### Bakery Management System - README

---

#### **Project Overview**
The **Bakery Management System** is a Java EE-based web application designed to manage bakery operations efficiently. It provides role-based functionality for customers and administrators, including product browsing, order management, and inventory control.


- I have saved my screenshots of all my pages in a folder with the name "Screenshots".

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
### Development Insights

Working on the **Bakery Management System** has been a fantastic learning experience for me, As this was also an Idea I had in my undergraduate. It allowed me to explore multiple aspects of Java EE development and understand how different components come together to build a complete web application.


---

#### **What I Learned**
1. **Jakarta EE and JSF**:
   - I gained a deeper understanding of Jakarta EE, especially the integration of JPA, EJBs, and JSF. 
   - Learning how JSF handles page navigation, data binding, and component reusability was particularly valuable.

2. **Database Design**:
   - Designing the database schema was an eye-opener. Mapping relationships like `One-to-Many` and `Many-to-Many` using JPA helped solidify my understanding of ORM.

3. **Security**:
   - Implementing role-based access control for admins and customers taught me how to use Jakarta Security effectively. I now appreciate how container-managed security can simplify authentication and authorization.

4. **Search and Filters**:
   - Adding a dynamic search bar was a challenge but a rewarding one. It showed me how important user-friendly filtering features are for enhancing usability.

5. **Layered Architecture**:
   - Separating concerns into persistence, service, and presentation layers was a key takeaway. It made debugging and extending the application much easier.

---

#### **What I’d Like to Explore Further**
1. **Real-Time Updates**:
   - I’d love to explore adding real-time features like inventory notifications or order status updates using WebSocket or SSE.
   
2. **API Development**:
   - Building a REST API for external integrations (like connecting the bakery system to delivery platforms) is something I’d like to try in the future.

3. **Advanced UI**:
   - While I used Bootstrap and styled some components, I’d like to delve deeper into front-end frameworks like React or Angular to create more dynamic and responsive interfaces.

---

#### **What I Liked**
1. **Building Something Useful**:
   - Knowing that the project could actually be used by a bakery business made it feel meaningful. It’s satisfying to see how the different pieces fit together to solve real-world problems.

2. **Role-Based Navigation**:
   - I really enjoyed implementing navigation flows for different roles (customer and admin). It felt rewarding to see it working seamlessly.

3. **Custom Validators**:
   - Adding validators for email and phone numbers was a small feature, but I liked how it improved the user experience and ensured data integrity.

---

#### **What I Didn’t Like**
1. **JSF Debugging**:
   - Debugging JSF pages, especially EL expressions, was tricky at times. Minor typos in binding expressions could cause silent failures, which was frustrating.

2. **UI Limitations**:
   - While Bootstrap made styling easier, I felt limited by the predefined components. It made me realize how much more flexibility modern front-end frameworks offer.

---

#### **Overall Experience**
This project pushed me out of my comfort zone and helped me grow as a developer. It wasn’t just about coding—it was about understanding the bigger picture of how a system works end-to-end. I feel more confident in my ability to design and build scalable web applications, and I’m excited to keep improving and learning from future projects.



