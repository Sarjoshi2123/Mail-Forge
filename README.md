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

## 🌐 Production Deployment (Free Tier)

This project is fully configured for seamless, zero-cost production deployment on **Render** (Backend) and **Vercel** (Frontend).

### 🖥️ Backend Deployment on Render

The backend is configured to build using a multi-stage **Dockerfile** under Eclipse Temurin JDK 21.

#### 1. Setup Render Web Service
1. Log in to [Render](https://render.com/) and click **New > Web Service**.
2. Connect your GitHub account and select your **Mail-Forge** repository.
3. Configure the following service settings:
   * **Name:** `mail-forge-backend` (or your preferred name)
   * **Region:** Choose the region closest to you
   * **Branch:** `main`
   * **Root Directory:** `backend` (⚠️ **CRITICAL:** Set this to build only the backend)
   * **Runtime:** `Docker` (⚠️ **CRITICAL:** Setting Root Directory + Docker auto-detects our multi-stage Dockerfile)
   * **Instance Type:** `Free`

#### 2. Configure Environment Variables on Render
Navigate to the **Environment** tab on your Render dashboard and add:
| Key | Value | Description |
|---|---|---|
| `GEMINI_API_KEY` | *Your Gemini API Key* | Your private Google Gemini AI API token |
| `CORS_ALLOWED_ORIGINS` | `https://your-frontend.vercel.app` | The production URL of your Vercel frontend |

#### 3. Trigger Deployment
Click **Create Web Service**. Render will automatically build the Docker image and deploy your Spring Boot container!

---

### 🎨 Frontend Deployment on Vercel

The React frontend is set up to build natively on Vercel and dynamically communicate with your Render backend.

#### 1. Setup Vercel Project
1. Log in to [Vercel](https://vercel.com/) and click **Add New > Project**.
2. Connect your GitHub account and import the **Mail-Forge** repository.
3. Configure the following project settings:
   * **Framework Preset:** `Vite`
   * **Root Directory:** Click *Edit* and select the `frontend` folder (⚠️ **CRITICAL:** Set this to build only the React app)
   * Keep default Build and Output Settings.

#### 2. Configure Environment Variables on Vercel
Expand the **Environment Variables** section and add:
| Key | Value | Description |
|---|---|---|
| `VITE_API_BASE_URL` | `https://mail-forge-backend.onrender.com` | The live URL of your Render backend |

#### 3. Trigger Deployment
Click **Deploy**. Vercel will build the frontend assets and host your modern UI!

---

## 👤 Author
* **Name:** Sarvesh Joshi
* **Email:** sarveshjoshi4056@gmail.com
