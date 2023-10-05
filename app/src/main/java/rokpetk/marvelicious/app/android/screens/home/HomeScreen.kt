package rokpetk.marvelicious.app.android.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        Column(
            modifier = Modifier.padding(
                paddingValues = paddingValues
            )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = Dimens.Padding.p12),
                value = state.searchQuery,
                onValueChange = { viewModel.onSearchQuery(it) },
                label = { Text(stringResource(id = R.string.home_search_label)) }
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = Dimens.Padding.p20)
            ) {
                items(
                    items = state.items,
                    itemContent = { item ->
                        HomeItemView(
                            item = item,
                            onClick = {
                                Navigator.openHeroDetails(
                                    navController = navController,
                                    heroId = item.id.toString()
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}