# StormotionTestTask
 
 A simple Kotlin Android app made for Stormotion as a test task.

## Used libs and technologies

 - ViewModel
 - RecyclerView
 - Kotlin Coroutines
 - Coil
 - DaggerHilt
 - Retrofit 2
 - NavigationComponent
 - Google AdMod

### MainScreen (ListFragment)

DarkTheme | LightTheme
--- | ---
![DarkThemeList](https://user-images.githubusercontent.com/32799066/119681420-bba5e180-be4a-11eb-9d21-a9f5ab7416a5.jpg) | ![LightThemeList](https://user-images.githubusercontent.com/32799066/119681464-c6607680-be4a-11eb-88bd-ca7b00816a0c.jpg)

### DetailsFragment

DarkTheme | LightTheme
--- | ---
![DarkThemeDetails](https://user-images.githubusercontent.com/32799066/119676502-91522500-be46-11eb-997e-a9bb9a85dde9.jpg) | ![LightThemeDetails](https://user-images.githubusercontent.com/32799066/119676530-97480600-be46-11eb-8263-8d7890edc13f.jpg)

## Summary

 It was interesting to work on the app. As a new experience I learned how to use Mockaroo Api. In the application itself I tried to follow MVVM architecture. As part of the task, Google AdMod implemented with some bugs, and also all data comes in one API request to ListFragment and passed as a Parceble argument to DetailsFragment.
