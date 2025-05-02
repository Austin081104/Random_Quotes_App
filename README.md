:

📱 Random Quote Generator App (with API & Offline Mode)
This is a simple Android app that fetches random quotes using the API Vercels Quotes API, and allows you to:

🔄 Fetch new random quotes

💾 Save favorite quotes to a local SQLite database

📋 Copy quotes to clipboard

📤 Share quotes with other apps

📃 View saved quotes

❌ Delete saved quotes

📡 Offline support when API fails

🚀 Features
Fetch quotes from API Vercel
Offline fallback with 20 built-in quotes
Clean and minimal UI
Fully local quote saving (no internet required for favorites)

Share via WhatsApp, Gmail, etc.

🛠️ Tech Stack
Java (Android SDK)

Retrofit (for HTTP requests)

SQLite (local storage)

XML Layouts (UI)

🔧 Setup Instructions
Clone this repository

git clone https://github.com/your-username/random-quote-app.git
Open in Android Studio

Add your API key from API Ninjas:

Go to QuoteService.java

Replace "YOUR_API_KEY" with your actual key
@Headers("X-Api-Key: YOUR_API_KEY")
Run the app on an emulator or device

