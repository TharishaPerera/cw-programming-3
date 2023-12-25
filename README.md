# ToothCare CW For Programming 3

## Introduction

Toothcare is a full-stack web application built using Java Springboot for the backend and ReactJS with TypeScript for the frontend.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java JDK installed on your computer
  ├── jdk version 17
- NodeJS installed on your computer
  ├── NodeJS version v18.19.0 

## Getting Started

To get started with the project, follow these steps:

1. **Clone the repository**
   ```bash
   git clone https://github.com/TharishaPerera/cw-programming-3.git
   
2. **Install Dependencies**
   ```bash
    # Navigate to the backend folder
    cd toothcare
    ./mvnw clean install
    
    # Navigate to the frontend folder
    cd ../frontend
    npm install
   
3. **Run the Application**
   ```bash
    # Start the backend server
    cd ../toothcare
    ./mvnw spring-boot:run
    
    # Start the frontend development server
    cd ../frontend
    npm run dev

4. **Access the application**
  - Open your web browser and go to http://localhost:5173 to access the frontend. (Depends on your available ports)
  - The backend API is available at http://localhost:8080.
    
  - Use the following credentials to log into the system
    ```bash
      # username / email
      gayani@toothcare.com

      # password
      gayani@1234
      
