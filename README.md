# DrodXAuctionBid 🧩

**A Distributed Online Auction System built with Java EE**

---

## 🔍 Overview

**DrodXAuctionBid** is a scalable, modular, and database-free Distributed Online Auction System developed using **Java EE technologies** such as **EJB, JMS, and WebSocket (Payara)**. It simulates real-time bidding scenarios with support for **manual and auto-bidding**, designed for academic, enterprise learning, and demonstration purposes.

The project follows a clean modular architecture:

* **core:** DTOs, utility classes, and shared logic.
* **ejb:** EJB beans for business logic and state management.
* **web:** HTML pages and servlets for the user interface.
* **ear:** EAR packaging to bundle everything into a deployable unit.

---

## 🛠️ Technologies Used

* Java 11
* Jakarta EE / Java EE

  * EJB (Stateless and Singleton Session Beans)
  * Message-Driven Beans (MDB)
  * JMS (Java Messaging Service)
  * Servlets
  * WebSocket (Payara Server)
* Gson (for JSON parsing)
* Payara Server (with OpenMQ for JMS)
* HTML, CSS, JavaScript for frontend
* In-memory collections simulate persistence (no external database)

---

## 📦 Architecture

```
Client (Browser)
      ↓
Web Tier (Servlet + HTML/JS/CSS)
      ↓
EJB Tier (Business Logic with Session Beans)
      ↓
Core Module (DTOs, Static Repositories)
```

JMS is used for real-time communication between bidding components, and WebSocket updates clients instantly.

---

## ✨ Key Features

### 🔐 User Management

* Registration and login using session beans
* Data stored in-memory without a database

### 🛍️ Product Catalog

* Products loaded statically using shared lists
* No external DB dependency

### ⚙️ Bidding System

* Manual bids processed with concurrency safety
* Auto-bidding engine that increments automatically up to a user's max limit

### ⏱️ Real-time Bidding with JMS + WebSocket

* JMS topics broadcast bid updates
* Web clients receive instant updates via WebSocket

### 🔄 Bid Prioritization

* Manual bids temporarily pause auto-bids
* Queue-based bid handling ensures fairness and order

---

## 📂 Module Structure

```
DrodXAuctionBid/
├── core/           # DTOs, Static data
├── ejb/            # EJBs (Session Beans, Bid Manager, AutoBid Manager)
├── web/            # HTML pages, Servlets, WebSocket endpoints
├── ear/            # EAR module with application.xml
└── README.md
```

---

## 🔧 How to Run

1. Use **JDK 11**.
2. Import as a Maven multi-module project in **IntelliJ** or **Eclipse**.
3. Deploy the **EAR module** to **Payara Server**.
4. Access via browser: `http://localhost:8080/DrodXAuctionBid-web`

---

## 🖼️ Screenshots

### Login Page

![Login Page](screenshots/login.png)

### Register Page

![Register Page](screenshots/register.png)

### Home Page

![Home Page](screenshots/home.png)

### Bidding Page

![Bidding Page](screenshots/bidding.png)

---

## 🎯 Project Overview

DrodXAuctionBid is a Java EE-based **Distributed Online Auction System** built using **EJB** and **JMS**. It simulates high-concurrency auction environments and provides:

* Real-time product views
* Manual and auto-bid functionalities
* Live bid updates via WebSocket
* Centralized in-memory data storage for users and products

The system uses **Message-Driven Beans** for asynchronous processing and **Singleton Session Beans** for centralized product and user management.

---

## 🚀 Getting Started

Clone the repository:

```bash
git clone https://github.com/DisanduRodrigo/DrodXAuctionBid.git
```

Deploy the EAR project on **Payara Server**.
Access the system via: `http://localhost:8080/DrodXAuctionBid-web`

---

## License

This project is licensed under the **MIT License**.
