package rokpetk.marvelicious.app.android.screens.herodetails

sealed class HeroDetailsEvent {
    data class ShowError(val message: String) : HeroDetailsEvent()
}