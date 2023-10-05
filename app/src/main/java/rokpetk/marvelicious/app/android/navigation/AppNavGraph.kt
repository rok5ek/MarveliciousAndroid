package rokpetk.marvelicious.app.android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import rokpetk.marvelicious.app.android.screens.herodetails.HeroDetailsScreen
import rokpetk.marvelicious.app.android.screens.home.HomeScreen

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.HeroDetails.route,
            arguments = listOf(navArgument(Screen.HeroDetails.arg) { type = NavType.StringType })
        ) {
            HeroDetailsScreen(navController = navController)
        }
    }
}