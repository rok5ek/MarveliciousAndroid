package rokpetk.marvelicious.app.android.screens.herodetails

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import rokpetk.marvelicious.app.android.R
import rokpetk.marvelicious.app.android.extensions.collectInLaunchedEffectWithLifecycle
import rokpetk.marvelicious.app.android.screens.herodetails.components.SectionView
import rokpetk.marvelicious.app.android.ui.components.CircularProgressView
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
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            CircularProgressView(isVisible = state.isLoading)
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.heroDetailsHeight),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart
                )
                Text(
                    modifier = Modifier.padding(Dimens.Padding.p16),
                    text = state.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                SectionView(
                    isVisible = !state.isLoading,
                    title = stringResource(id = R.string.hero_details_comics_section),
                    items = state.comics
                )
                Box(modifier = Modifier.height(Dimens.Padding.p16))
                SectionView(
                    isVisible = !state.isLoading,
                    title = stringResource(id = R.string.hero_details_events_section),
                    items = state.events
                )
                Box(modifier = Modifier.height(Dimens.Padding.p16))
                SectionView(
                    isVisible = !state.isLoading,
                    title = stringResource(id = R.string.hero_details_series_section),
                    items = state.series
                )
            }
        }
    }
}