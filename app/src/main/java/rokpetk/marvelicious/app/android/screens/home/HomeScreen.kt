package rokpetk.marvelicious.app.android.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import rokpetk.marvelicious.app.android.R
import rokpetk.marvelicious.app.android.extensions.collectInLaunchedEffectWithLifecycle
import rokpetk.marvelicious.app.android.navigation.Navigator
import rokpetk.marvelicious.app.android.screens.home.components.HomeItemView
import rokpetk.marvelicious.app.android.ui.components.CircularProgressView
import rokpetk.marvelicious.app.android.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.event.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is HomeEvent.ShowError -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold() { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            CircularProgressView(isVisible = state.isLoading)
            Column(
                modifier = Modifier.padding(
                    paddingValues = paddingValues
                )
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = Dimens.Padding.p20),
                    value = state.searchQuery,
                    onValueChange = { viewModel.onSearchQuery(it) },
                    label = { Text(stringResource(id = R.string.home_search_label)) }
                )

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(Dimens.Padding.p20),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.p12),
                    verticalArrangement = Arrangement.spacedBy(Dimens.Padding.p12),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(
                            count = state.items.size,
                            itemContent = { index ->
                                HomeItemView(
                                    item = state.items[index],
                                    onClick = {
                                        Navigator.openHeroDetails(
                                            navController = navController,
                                            heroId = state.items[index].id.toString()
                                        )
                                    }
                                )

                            }
                        )
                    }
                )
            }
        }
    }
}