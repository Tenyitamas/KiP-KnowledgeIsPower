package com.tenyitamas.kip_knowledgeispower.presentation.detailed

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.presentation.detailed.components.ArticleHeader
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing

@Composable
fun DetailedScreen(
    onShare: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailedViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    val scrollState = rememberScrollState()


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .verticalScroll(state = scrollState)
                .fillMaxSize()
        ) {
            ArticleHeader(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface),
                title = state.article?.title
                    ?: stringResource(id = R.string.error_title_cannot_be_loaded),
                description = state.article?.description ?: "",
                source = state.article?.author ?: "",
                timeString = state.article?.publishedAt ?: "",
                onShare = {
                    onShare()
                }
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(state.article?.urlToImage)
                    .crossfade(1000)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_background)
                    .build(),
                contentDescription = state.article?.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            if(state.article?.url != null) {
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            webViewClient = WebViewClient()
                            loadUrl(state.article.url)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if(state.isLoaded) {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(DetailedEvent.OnSaveButtonClick)
                },
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(spacing.spaceMedium)
            ) {
                Icon(
                    imageVector = if(state.isSaved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if(state.isSaved) "delete" else "save"
                )
            }
        }
    }
}
