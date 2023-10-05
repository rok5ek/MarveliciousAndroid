package rokpetk.marvelicious.app.android.screens.herodetails

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import rokpetk.marvelicious.app.android.R
import rokpetk.marvelicious.app.android.extensions.collectInLaunchedEffectWithLifecycle
import rokpetk.marvelicious.app.android.screens.herodetails.components.MediaItemView
import rokpetk.marvelicious.app.android.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroDetailsScreen(
    navController: NavController,
    viewModel: HeroDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.event.collectInLaunchedEffectWithLifecycle { event ->
        when (event) {
            is HeroDetailsEvent.ShowError -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                stringResource(id = R.string.hero_details_comics_section),
                modifier = Modifier.padding(all = Dimens.Padding.p16)
            )
            LazyRow(
                contentPadding = PaddingValues(Dimens.Padding.p16),
                horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.p12)
            ) {
                items(
                    count = state.comics.size,
                    itemContent = { item ->
                        MediaItemView(
                            item = state.comics[item],
                        )
                    }
                )
            }
            Text(
                stringResource(id = R.string.hero_details_events_section),
                modifier = Modifier.padding(all = Dimens.Padding.p16)
            )
            LazyRow(
                contentPadding = PaddingValues(Dimens.Padding.p16),
                horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.p12)
            ) {
                items(
                    count = state.events.size,
                    itemContent = { item ->
                        MediaItemView(
                            item = state.comics[item],
                        )
                    }
                )
            }
            Text(
                stringResource(id = R.string.hero_details_series_section),
                modifier = Modifier.padding(all = Dimens.Padding.p16)
            )
            LazyRow(
                contentPadding = PaddingValues(Dimens.Padding.p16),
                horizontalArrangement = Arrangement.spacedBy(Dimens.Padding.p12)
            ) {
                items(
                    count = state.series.size,
                    itemContent = { item ->
                        MediaItemView(
                            item = state.comics[item],
                        )
                    }
                )
            }
        }
    }
}