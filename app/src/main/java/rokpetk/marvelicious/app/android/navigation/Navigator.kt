package rokpetk.marvelicious.app.android.navigation

import androidx.navigation.NavController

object Navigator {

    fun openHeroDetails(
        navController: NavController,
        heroId: String
    ) {
        navController.navigate(Screen.HeroDetails.createRouteWithArg(heroId))
    }
}