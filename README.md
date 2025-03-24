# FetchProducts

FetchProducts is an Android application that retrieves a list of items from a remote API, filters and sorts them, groups the items by List ID, and displays them in an interactive UI. Users can tap on a List ID to view a detailed list of items in a new screen. The app uses modern Android architecture components including Retrofit, Hilt for dependency injection, ViewModel with StateFlow, and ViewBinding.

## Features

- **API Data Retrieval:**  
  Uses Retrofit with suspend functions and Kotlin coroutines to fetch data from:  
  `https://fetch-hiring.s3.amazonaws.com/hiring.json`
- **Data Filtering & Custom Sorting:**  
  Filters out items with blank or null names. The names are sorted alphabetically by their text portion and numerically by the number (if present).
- **Grouping:**  
  Groups items by `listId` so that all items belonging to the same list are shown together.
- **Interactive UI with Fragments:**  
  - **ListFragment:** Displays group headers (each representing a List ID) in an accordion style.
  - **ListDetailFragment:** When a group header is clicked, navigates to a detail fragment that shows the List ID as a header and the associated items in a RecyclerView.
  - Custom fragment animations are applied during transitions.
- **Modern Architecture & Tools:**
  - **ViewModel** and **StateFlow** for managing UI state.
  - **Hilt** for dependency injection.
  - **ViewBinding** for type-safe UI interactions.
  - Custom fonts and styling.
  - Dynamic customization of system UI elements (status and navigation bars).

## Architecture Overview

- **Data Layer:**  
  - `ApiService` and `RetrofitClient` handle API calls.
  - `ItemRepository` processes and filters the API data.
- **ViewModel Layer:**  
  - `ItemViewModel` fetches, sorts, and groups the data, exposing it via a `StateFlow`.
- **UI Layer:**  
  - **ListFragment:** Displays grouped List IDs using an accordion-style adapter.
  - **ListDetailFragment:** Shows detailed items for a selected List ID with a toolbar and back navigation.
  - **MainActivity:** Hosts the fragments and handles navigation via manual fragment transactions.

## Getting Started

### Prerequisites

- **Android Studio** (latest stable version recommended)
- **Minimum SDK:** API 24
- **Gradle:** 7+ and latest Android Gradle Plugin
