# Mail Forge ✉️⚒️

A premium full-stack application designed to craft and manage email templates, featuring a high-performance Spring Boot backend and a modern React + Vite frontend.

---

## 📂 Project Structure

This repository is structured as a **monoreposity** containing both the backend and frontend components:

```text
Mail-Forge/
├── backend/          # Spring Boot Application (Maven)
│   ├── src/          # Source files (Java)
│   └── pom.xml       # Maven dependencies configuration
│
├── frontend/         # Vite-React Application
│   ├── src/          # Frontend assets and components (JSX/JS/CSS)
│   ├── index.html    # Entry HTML
│   └── package.json  # NPM dependencies & scripts
│
└── .gitignore        # Root gitignore rules
```

---

## 🛠️ Technology Stack

### Backend (`/backend`)
* **Framework:** Spring Boot (Java)
* **Build System:** Maven
* **Key Integrations:** Spring Web, Spring WebClient (for API/AI integration)

### Frontend (`/frontend`)
* **Framework:** React.js
* **Build Tool:** Vite
* **Styling:** CSS3
* **Transpiler:** ESLint

---

## 🚀 Getting Started

### 1. Prerequisites
Ensure you have the following installed on your machine:
* Java JDK (17 or higher recommended)
* Node.js (v18 or higher recommended) and npm
* Maven

### 2. Running the Backend
1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Run the Spring Boot application using Maven:
   ```bash
   mvn spring-boot:run
   ```

### 3. Running the Frontend
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```

---

## 👤 Author
* **Name:** Sarvesh Joshi
* **Email:** sarveshjoshi4056@gmail.com
