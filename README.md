# GTAFinder - Location Management & Mapping App

**Target Platform:** Android (API Level 24+) | Kotlin  

---

## 1. Overview & Architecture
**GTAFinder** is an Android application for managing and mapping locations across the Greater Toronto Area. Built using Kotlin, it separates data access logic from the UI using a DAO pattern:

* **Data Layer (`/data`):** `Location.kt` (Model), `LocationDbHelper.kt` (SQLite helper), `LocationDao.kt` (CRUD operations), `GtaData.kt` (100-item seed dataset).
* **UI Layer (`/ui`):** `MainActivity.kt` (Search & Map view), `ManageLocationActivity.kt` (Add/Edit/Delete form), `LocationAdapter.kt` (RecyclerView).

---

## 2. Database & Data Seeding
* **SQLite Schema:** 5 columns (`id`, `address`, `latitude`, `longitude`, `created_datetime`).
* **Auto-Seeding:** On first launch, `MainActivity` checks if the database is empty and automatically inserts 100 pre-populated GTA locations from `GtaData.kt` using `LocationDao.insertLocation()`.

---

## 3. Google Maps SDK Integration
* Maps are rendered via `SupportMapFragment` and `OnMapReadyCallback`.
* The camera defaults to zoom over the GTA (`43.6532, -79.3832`).
* Query results dynamically place map markers via `MarkerOptions()` with the address as the title and coordinates in the snippet.

---
