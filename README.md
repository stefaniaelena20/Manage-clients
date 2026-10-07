# Client and Order Management System

A Java desktop application for managing clients, products, and customer orders. The project uses a graphical interface built with **Java Swing**, a **MySQL** database, and a layered architecture that separates the user interface, business logic, and data access.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Application Workflow](#application-workflow)
- [Architecture](#architecture)
- [Database](#database)
- [Getting Started](#getting-started)
- [Using the Application](#using-the-application)
- [Project Structure](#project-structure)
- [Diagrams](#diagrams)
- [Current Setup Notes](#current-setup-notes)
- [Learning Goals](#learning-goals)

## Overview

The application provides a simple interface for maintaining client and product information and placing orders.

Users can add clients, create and update product records, and place orders by selecting a client, a product, and a quantity. Before an order is completed, the application checks that the quantity is valid and that enough stock is available. After a successful order, the product stock is updated and a bill record is generated and saved to the database.

## Features

### Client management

- Add a client with a name, email address, and postal address.
- Display existing clients in a table.
- Require a client name before saving a new record.

### Product management

- Add products with a name, price, and stock quantity.
- Display available products in a table.
- Update product information.
- Validate product form fields and reject negative stock values.

### Order management

- Select an existing client and product from the interface.
- Enter the desired order quantity.
- Reject quantities that are not positive.
- Check that the selected product exists and has enough stock.
- Save the order with its date and time.
- Update the remaining product stock after a successful order.
- Calculate the order total and generate a bill record.
- Log bill details, including the order ID, client name, product name, quantity, total price, and bill date.

## Technology Stack

| Area | Technology |
|---|---|
| Programming language | Java 23 |
| Desktop interface | Java Swing |
| Database | MySQL |
| Database access | JDBC |
| Build configuration | Maven |
| Data model | Java classes for clients, products, orders, and bills |

## Application Workflow

The main window provides access to three sections:

1. **Manage Clients** — add a client and view the client list.
2. **Manage Products** — add products, update product information, and view the product list.
3. **Manage Orders** — choose a client and product, enter a quantity, and place an order.

The order process checks the requested quantity and available stock. If the order can be placed, the application saves the order, reduces the product stock, calculates the total price, and stores the bill information.

## Architecture

The application is organized into three main layers:

| Layer | Responsibility |
|---|---|
| Presentation | Swing windows and tables used to interact with the application. |
| Business logic | Coordinates client, product, and order operations and applies validations. |
| Data access | Connects to MySQL and performs database queries through JDBC. |

The `model` package contains the main data objects used by the other layers.

This separation makes each part of the application easier to understand and maintain. For example, the order window collects the user’s selections, the business layer checks whether the order is valid, and the data access layer stores the result.

## Database

The project includes a MySQL schema in `dump.sql`. The script creates the `schooldb` database and the tables required by the application.

| Table | Purpose |
|---|---|
| `client` | Stores client names, email addresses, and postal addresses. |
| `product` | Stores product names, prices, and available stock. |
| `orders` | Stores the selected client, product, quantity, and order date. |
| `log` | Stores generated bill details for completed orders. |

The `orders` table uses foreign keys to associate each order with a client and a product.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 23
- MySQL Server
- Maven
- A Java IDE such as IntelliJ IDEA or Eclipse

### 1. Clone the repository

```bash
git clone https://github.com/stefaniaelena20/Manage-clients.git
cd Manage-clients
```

### 2. Create the database

Run the SQL script provided in the repository:

```text
dump.sql
```

You can execute it in MySQL Workbench or from the MySQL command line. The script creates the `schooldb` database and its tables.

### 3. Configure the database connection

The connection settings are defined in `ConnectionFactory.java`. The current configuration uses:

```text
Database: schooldb
Host: localhost
Port: 3306
Username: root
```

Update the username and password in `ConnectionFactory.java` to match your local MySQL setup. Avoid committing real database passwords to a public repository.

### 4. Configure the MySQL JDBC driver

The MySQL Connector/J dependency is currently commented out in `pom.xml`. Enable or add a compatible MySQL JDBC driver dependency before running the application.

### 5. Open and run the application

Open the project in a Java IDE, make sure the source files are arranged according to their declared Java packages, and run:

```text
presentation.Main
```

The main window will open with buttons for managing clients, products, and orders.

## Using the Application

### Add a client

1. Open **Manage Clients** from the main window.
2. Enter the client’s name.
3. Optionally enter an email address and postal address.
4. Select **Add Client**.
5. The client list refreshes to display the new record.

### Add or update a product

1. Open **Manage Products**.
2. Enter the product name, price, and stock quantity.
3. Select **Add Product** to create a product.
4. To update a product, select the relevant product and use the update controls.

### Place an order

1. Open **Manage Orders**.
2. Select a client.
3. Select a product.
4. Enter a positive quantity.
5. Select **Place Order**.
6. The application checks the available stock and displays the result.

## Project Structure

The source code uses the following logical packages:

```text
Manage-clients/
├── presentation/
│   ├── Main.java
│   ├── ClientWindow.java
│   ├── ProductWindow.java
│   ├── OrderWindow.java
│   └── TableUtility.java
├── businessLayer/
│   ├── ClientBLL.java
│   ├── ProductBLL.java
│   └── OrderBLL.java
├── dataAccessLayer/
│   ├── AbstractDAO.java
│   ├── ClientDAO.java
│   ├── ProductDAO.java
│   ├── OrderDAO.java
│   ├── LogDAO.java
│   └── ConnectionFactory.java
├── model/
│   ├── Client.java
│   ├── Product.java
│   ├── Order.java
│   └── Bill.java
├── dump.sql
├── pom.xml
└── README.md
```

The repository currently stores the Java source files at its root. The tree above shows how the classes are grouped by their declared package responsibilities.

## Diagrams

The repository includes Draw.io diagrams covering the system’s packages, use cases, and architecture. These diagrams can be opened and edited with [diagrams.net](https://www.diagrams.net/).

## Current Setup Notes

- The project uses Java 23, as configured in `pom.xml`.
- The MySQL JDBC dependency is commented out and must be enabled for database connectivity.
- Database credentials are currently defined in `ConnectionFactory.java`; update them for your own local database.
- The source files are stored at the repository root even though they declare Java packages. Arrange them in matching package folders when setting up a standard Maven project.
- The database connection settings are intended for a local development database.

## Learning Goals

This project demonstrates:

- Building desktop interfaces with Java Swing.
- Connecting a Java application to MySQL using JDBC.
- Separating presentation, business logic, and data access responsibilities.
- Using models to represent application data.
- Validating input before creating records.
- Managing relationships between clients, products, and orders.
- Updating inventory and generating bill records as part of an order workflow.
- Documenting software design with UML and architecture diagrams.

## Author

**Stefania Elena**
