# CryptoCurrency


This is a simple application which shows the list of crypto currency along with price and percentage. 

**Features:**
* The App supports offline support using Room database. 
* Pull to refresh option is provided to fetch data from remote.
* Search option is provided to filter the News based on search text. 

**Architecture components and libraries used:**
* **MVVM** architecture pattern is used with **ViewModel, LiveData**. It is a lifecycle-aware component and it can automatically manage lifecycle. 
* Uses **Retrofit** framework for authenticating and interacting with APIs and sending network requests.
* Uses **Moshi** converter to Retrofit, which will automatically parse the JSON and give the object of the type needed.
* Uses **View Binding** to generate a binding class for each XML layout file present in that module.
* **Room database** for offine support. Room is an SQL powered database and it uses annotations to generate powerful queries and database tables.



***Swipe to Refresh to fetch data from remote***
Pull to Refresh |
--- |
![pull to refresh (1)](https://user-images.githubusercontent.com/59041442/208338330-3d09b0e8-342b-4999-9bfb-7df769efbb15.jpg)


***Local database storage using RoomDB***
SQLite DB |
--- |
![coinsDB (1)](https://user-images.githubusercontent.com/59041442/208338671-2254c817-e87a-4017-b6c8-ec3a54a570cb.png)

