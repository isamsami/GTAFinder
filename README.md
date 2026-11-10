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

## 4. Queries and Filtering

The query method connects user search inputs directly to local SQLite searches:

- **Real-Time Input:** `SearchView.OnQueryTextListener` in `MainActivity` listens for live user input.
- **Parameterized SQL Query:** Searching executes `LocationDao.queryByAddress(query)` using secure parameterized strings:
  ```sql
  SELECT * FROM location WHERE address LIKE '%' || ? || '%' ORDER BY id DESC

---

## 5. Add, Update, and Delete CRUD Operations

Location entry management is handled inside `ManageLocationActivity`:

- **Add Mode:** Opened via the `+ Add New Location` button. Validates that inputs are non-empty and coordinates fall within valid ranges (Latitude: -90 to 90, Longitude: -180 to 180). Submitting calls `LocationDao.insertLocation()`.

- **Update Mode:** Triggered by selecting an item from the `RecyclerView` in `MainActivity`. Intent extras pass `id`, `address`, `latitude`, and `longitude` to pre-fill the form fields. Submitting calls `LocationDao.updateLocation()`.

- **Delete Mode:** Accessible inside Update Mode via the "Delete Location" button, which executes `LocationDao.deleteLocation(id)`.

When `ManageLocationActivity` completes, calling `finish()` returns control to `MainActivity`, where `onResume()` automatically re-queries the database and refreshes both the map markers and list display.
