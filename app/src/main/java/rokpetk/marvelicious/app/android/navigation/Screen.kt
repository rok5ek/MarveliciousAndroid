package rokpetk.marvelicious.app.android.navigation

sealed class Screen(private val _route: String, val arg: String = "") {

    fun createRouteWithArg(value: String): String {
        return "$_route/$value"
    }

    val route: String
        get() = if (arg.isEmpty()) _route else "$_route/{$arg}"

    object Home : Screen(_route = "home_route")
    object HeroDetails : Screen(_route = "hero_details_route", arg = "hero_id")
}