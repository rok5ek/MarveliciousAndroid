package rokpetk.marvelicious.app.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import rokpetk.marvelicious.app.android.screens.components.HomeItemView
import rokpetk.marvelicious.app.android.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier.padding(
                paddingValues = paddingValues
            )
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = Dimens.Padding.p20)
            ) {
                items(
                    items = state.items,
                    itemContent = { item ->
                        HomeItemView(item = item)
                    }
                )
            }
        }
    }
}