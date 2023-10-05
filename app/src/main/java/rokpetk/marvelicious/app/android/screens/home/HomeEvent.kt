package rokpetk.marvelicious.app.android.screens.home

sealed class HomeEvent {
    data class ShowError(val message: String) : HomeEvent()
}