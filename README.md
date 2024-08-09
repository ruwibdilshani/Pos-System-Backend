<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&color=green&width=835&height=70&lines=POS+SYSTEM+BACKEND" alt="pos" /></a>

# POS System Backend
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JavaEE](https://img.shields.io/badge/JavaEE-6DB33F?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Overview

- **Technologies:**
    - Java EE (Servlets)
    - MySQL
    - Jakarta JSON-B
    - Logback (Logging)
    - Apache Tomcat

- **Project Purpose:**
  The POS system allows small to medium-sized businesses to manage their sales operations effectively, including customer management, inventory control, and order processing.

## Getting Started

### Prerequisites

Ensure you have the following installed:

- JDK 11 or higher
- Apache Tomcat 9.x or higher
- MySQL Server
- Git (for cloning the repository)

### Installation Steps

1. **Clone the Project Repository:**
   ```bash
   git clone(https://github.com/ruwibdilshani/Pos-System-Backend.git)
   
   ```

2. **Set Up MySQL Database:**
    - Create a new database in MySQL:
      ```sql
      CREATE DATABASE simple_pos;
      ```
    - Import the database schema:
      ```bash
      mysql -u [username] -p pos_system < db_schema.sql
      ```

3. **Deploy the Application:**
    - Package the project into a WAR file.
    - Deploy the WAR file to the Tomcat server.


## API Reference

The system provides RESTful APIs for managing customers, items, and orders. Below is an overview of the available endpoints:

### Customer Management

- **Add Customer (POST /customer):**
    - **Request:**
      ```json
      {
        "id": "C001",
        "name": "Ruwi",
        "address": "Galle",
        "salary": 5000
      }
      ```
    - **Response:** `201 Created`

- **Retrieve Customers (GET /customer):**
    - **Parameters:**
        - `type=one&id=C001`: Fetch a single customer by ID.
        - `type=all`: Fetch all customers.
    - **Response:** `200 OK`

- **Update Customer (PUT /customer?id={customerId}):**
    - **Request:**
      ```json
      {
        "name": "Ruwi",
        "address": "Galle",
        "salary": 6000
      }
      ```
    - **Response:** `200 OK`

- **Delete Customer (DELETE /customer?id={customerId}):**
    - **Response:** `204 No Content`

### Item Management

- **Add Item (POST /item):**
    - **Request:**
      ```json
      {
        "code": "I001",
        "itemName": "Rice",
        "qty": 10,
        "unitPrice": 1500
      }
      ```
    - **Response:** `201 Created`

- **Retrieve Items (GET /item):**
    - **Parameters:**
        - `type=one&itemCode=I001`: Fetch a single item by code.
        - `type=all`: Fetch all items.
    - **Response:** `200 OK`

- **Update Item (PUT /item?itemCode={itemCode}):**
    - **Request:**
      ```json
      {
        "itemName": "Rice",
        "qty": 5,
        "unitPrice": 2000
      }
      ```
    - **Response:** `200 OK`

- **Delete Item (DELETE /item?itemCode={itemCode}):**
    - **Response:** `204 No Content`

### Order Processing

- **Place Order (POST /placeorder):**
    - **Request:**
      ```json
      {
        "orderId": "ORD001",
        "date": "2024-08-09",
        "customerId": "C001"
      }
      ```
    - **Response:** `201 Created`

- **Save Order Details (POST /orderdetail):**
    - **Request:**
      ```json
      [
        {
          "itemCode": "I001",
          "qty": 2,
          "unitPrice": 1500
        }
      ]
      ```
    - **Response:** `201 Created`

## Additional Information

### Logging and Monitoring

The application uses Logback for logging. Logs are generated in both the console and log files, providing detailed information about API requests, errors, and other significant events.

### Error Handling

The API includes robust error handling mechanisms to return meaningful HTTP status codes and messages, ensuring clients can easily identify and resolve issues.

-# Features
#### Add Customer: Easily add and manage customer information.
#### Add Items: Add menu items with images to create a visually appealing menu.
#### Place Orders: Simplify the ordering process with a user-friendly order placement system.
#### Modern UI: Enjoy a sleek and responsive design that works well on various devices

# API EndPoint Documentation

### Customer : https://documenter.getpostman.com/view/36641894/2sA3s1pY6Y
### Item : https://documenter.getpostman.com/view/36641894/2sA3s1psRd
### Order : https://documenter.getpostman.com/view/36641894/2sA3s1psW1
### OrderDetail : https://documenter.getpostman.com/view/36641894/2sA3s1psjL


<div align="center">

#### © 2024 All Right Reserved, Designed By [Ruwi B Dilshani](https://github.com/ruwibdilshani

</div>

