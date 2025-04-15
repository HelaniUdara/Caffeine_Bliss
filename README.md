# 🍫 Caffeine Bliss Backend

**Caffeine Bliss** is a Spring Boot-based Point of Sale (POS) system for a coffee shop. This backend application supports customer and order management, secure authentication, and email features like sending order receipts and password reset links.

---

## ✨ Features

### 👤 Authentication & Authorization
- JWT-based authentication.
- Role-based authorization (`ADMIN` and `CASHIER`).
- Secure login with access control for endpoints.

### 📧 Email Services
- Send order receipts as **HTML emails** with embedded images using **Thymeleaf** templates.
- Support for **password reset via email** with JWT token validation.

### ⌛ Password Reset
- Users can reset their password using their **old password**.
- Users can reset their forgotten password using a **secure JWT token** sent via email.
- Token-based password reset endpoint is protected and validates expiry.

### 💼 Customer & Order Management
- Add/search customers by phone number.
- Place orders and assign them to a cashier.
- View orders by customer or cashier.
- Admin view for order history and analytics.

### 📃 Technologies Used
- Spring Boot, Spring Security
- Java MailSender + Thymeleaf for emails
- JWT for token generation & validation
- MySQL
- Lombok, ModelMapper, etc.

---

## 📊 Endpoints Overview

### ⛓ Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/auth/login` | Authenticate user and get JWT token |
| POST | `/api/v1/auth/forgot-password` | Send password reset link via email |
| POST | `/api/v1/auth/reset-password-from-link` | Reset password using token from email |

### 🔐 User Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/user/ad/get-by-Id?id={id}` | Get user by Id (admin only) |
| GET | `/api/v1/user/ad/get-by-email?email={email}` | Get user by email (admin only) |
| GET | `/api/v1/user/ad/get-users-by-role?role={role}` | Get users by their role (admin only) |
| GET | `/api/v1/user/ad/get-all-users` | Get all users (admin only) |
| POST | `/api/v1/user/ad/add` | Add new user to the system (admin only) |
| POST | `/api/v1/user/ad/update` | Update user details (admin only) |
| POST | `/api/v1/user/ad/deactivate` | Deactivate a user (admin only) |
| POST | `/api/v1/user/reset-password` | Reset password using current password (requires login) |

### 🛍️ Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/order/get-orders-by-customer?customerId={customerId}` | Get all orders for a customer |
| GET | `/api/v1/order/get-orders-by-cashier?cashierId={cashierId}` | Get all orders placed by a cashier |
| GET | `/api/v1/order/ad/get-all-orders` | Get all orders (admin only) |
| GET | `/api/v1/order/get-order-details?id={id}` | Get full order details by its UUID |
| POST | `/api/v1/order/add` | Add new order |

### 🍩 Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/product/get-by-Id?id={id}` | Get a product by its UUID |
| GET | `/api/v1/product/get-by-name?name={name}` | Get a product by name |
| GET | `/api/v1/product/get-by-category?category={category}` | Get products by category |
| GET | `/api/v1/product/get-all-products` | Get all products |
| GET | `/api/v1/product/get-limited-products?page={page}&limit={limit}` | Get paginated list of products (limit max 20) |
| POST | `/api/v1/product/ad/add` | Add a new product (admin only) |
| POST | `/api/v1/product/ad/update` | Update an existing product (admin only) |
| DELETE | `/api/v1/product/ad/delete-by-Id?id={id}` | Delete a product by UUID (admin only) |

### 💳 Customers
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/customer/get-by-mobile/{mobile}` | Get customer by mobile number |
| GET | `/api/v1/customer/get-by-name/{name}` | Get customer by name |
| GET | `/api/v1/customer/get-all-customers` | Get all customers |
| GET | `/api/v1/customer/get-limited-customers/{page}/{limit}` | Get paginated list of customers (limit max 20) |
| POST | `/api/v1/customer/add` | Add new customer |
| POST | `/api/v1/customer/update` | Update customer details |

### 📧 Receipt
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/receipt/sent-email?orderId={orderId}` | Send order receipt email to customer |
| GET | `/api/v1/receipt/get-by-orderId?orderId={orderId}` | Get receipt by order Id |

---

## ✍️ Setup Instructions

1. **Clone the repository**
```bash
git clone https://github.com/HelaniUdara/Caffeine_Bliss.git
```
2. **Configure application.properties:**
- Database connection
- JWT secrets
- Mail server credentials (e.g., Gmail SMTP)
  
3. **Run the Spring Boot application**
