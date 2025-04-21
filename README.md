# QuickDine – Dual Android App Project

## 📱 Project Overview

This project was created as part of **Exercise 1**, and consists of **two Android applications** that share a **common module**.

### 💡 Concept:
- One app is for **customers** to view the restaurant menu and place orders.
- The other app is for **waiters** to view and manage incoming orders.
- Both apps inherit from an abstract `BaseActivity` class located in the **shared module**.

---

## 🧱 Project Structure

- **customerapp/** – Android app for customers to browse the menu and place orders.
- **waiterapp/** – Android app for waiters to view and manage all submitted orders.
- **common/** – Shared module that includes:
  - Shared models (e.g. `MenuItem`)
  - Abstract `BaseActivity`
  - Shared utilities such as `OrderStorage` for managing data across apps

---
