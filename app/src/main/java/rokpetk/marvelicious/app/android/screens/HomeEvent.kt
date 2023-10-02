package rokpetk.marvelicious.app.android.screens

sealed class HomeEvent {
    data class ShowError(val message: String) : HomeEvent()
}