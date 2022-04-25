package com.tenyitamas.kip_knowledgeispower.presentation.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.tenyitamas.kip_knowledgeispower.R
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.presentation.shared.NewsItem
import com.tenyitamas.kip_knowledgeispower.ui.theme.LocalSpacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SavedScreen(
    onArticleClick: (Article) -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: SavedViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val spacing = LocalSpacing.current

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {

        if (state.articles.isEmpty()) {
            item {
                Text(
                    text = stringResource(id = R.string.no_saved_articles),
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,

                    )


            }
        }
        items(state.articles) { article ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                NewsItem(
                    article = article,
                    onClick = { onArticleClick(article) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.spaceSmall)
                )

                IconButton(
                    onClick = {
                        viewModel.onDeleteButtonClick(article = article)

                        scope.launch {

                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = context.getString(R.string.article_deleted),
                                actionLabel = context.getString(R.string.undo)
                            )

                            if(result == SnackbarResult.ActionPerformed) {
                                viewModel.onRestoreButtonClick()
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(spacing.spaceMedium)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = MaterialTheme.colors.secondary
                    )
                }

            }

        }
    }

}