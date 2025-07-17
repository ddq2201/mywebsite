# MyWebsite Portal

A lightweight Spring Boot web application offering user management, dashboards, embedded games, notes, and shared resources—all in a unified portal.

---

## ✨ Features

- 👤 **User Management** – View and add users  
- 📊 **Dashboard & Matches** – Visual data and match viewing  
- 🎮 **Mini-Games** – Snake, Rock Paper Scissors, Dragon Flight  
- 📚 **Work Materials** – Upload and manage shared files  
- 📝 **Notes** – Add, edit, and delete notes with persistence  

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot  
- Thymeleaf  
- H2 / MySQL  
- HTML + CSS + basic JavaScript  

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

### 2. Run the Application

Using Maven Wrapper:
```bash
./mvnw spring-boot:run
```

Or from IDE (e.g. IntelliJ), run `MyWebsiteApplication.java`.

### 3. Access the Application

- On your machine: `http://localhost:8080`  
- On another device (same Wi-Fi):  

  In `src/main/resources/application.properties`, add:

  ```properties
  server.address=0.0.0.0
  server.port=8080
  ```

  Then open in browser:  
  `http://<your-local-ip>:8080` (e.g., `http://192.168.1.10:8080`)

> 🔐 Make sure firewall allows inbound connections on port 8080.

---

## 📁 Project Structure

```
├── controller/        → Web controllers (@Controller)
├── model/             → JPA entity classes
├── repository/        → Spring Data JPA interfaces
├── templates/         → Thymeleaf HTML views
├── static/            → Static resources (CSS, JS, images)
└── application.properties → App configuration
```

---

## 📦 Build and Package

```bash
./mvnw clean package
```

The `.jar` file will be available in `target/`. You can run it using:

```bash
java -jar target/your-app.jar
```

---

## 📜 License

This project is licensed under the MIT License.  
Use it, modify it, share it freely.

---
