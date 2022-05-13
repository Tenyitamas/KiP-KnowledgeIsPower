package com.tenyitamas.kip_knowledgeispower

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.navigation.Route
import com.tenyitamas.kip_knowledgeispower.presentation.detailed.DetailedScreen
import com.tenyitamas.kip_knowledgeispower.presentation.saved.SavedScreen
import com.tenyitamas.kip_knowledgeispower.presentation.search.SearchScreen
import com.tenyitamas.kip_knowledgeispower.presentation.settings.SettingsScreen
import com.tenyitamas.kip_knowledgeispower.presentation.shared.BottomNavigationBar
import com.tenyitamas.kip_knowledgeispower.ui.theme.KiPKnowledgeIsPowerTheme
import com.tenyitamas.kip_knowledgeispower.navigation.ArticleParamType
import com.tenyitamas.kip_knowledgeispower.util.bottomNavItems
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KiPKnowledgeIsPowerTheme {

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    snackbarHost = {
                        SnackbarHost(it) { data ->
                            Snackbar(
                                snackbarData = data,
                                actionColor = MaterialTheme.colors.secondary,
                                contentColor = MaterialTheme.colors.primary,
                                backgroundColor = MaterialTheme.colors.surface
                            )
                        }
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            items = bottomNavItems(),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.SEARCH
                    ) {
                        composable(Route.SEARCH) {
                            SearchScreen(
                                onArticleClick = { article ->
                                    val articleJson = Uri.encode(Gson().toJson(article))
                                    navController.navigate(
                                        Route.DETAILED
                                            .plus("/$articleJson")
                                    )
                                },
                                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                            )
                        }

                        composable(
                            route = Route.DETAILED
                                .plus("/{article}"),
                            arguments = listOf(
                                navArgument("article") {
                                    type = ArticleParamType()
                                }
                            )
                        ) {
                            val article = it.arguments?.getParcelable<Article>("article")
                            val imageUri = Uri.parse(
                                article?.urlToImage ?: "android.resource://"
                                    .plus(packageName)
                                    .plus("/drawable/" + "ic_launcher")
                            )
                            val share = Intent.createChooser(
                                Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, article?.url ?: "")
                                    putExtra(Intent.EXTRA_STREAM, imageUri)
                                    putExtra(Intent.EXTRA_TITLE, article?.title ?: "")
                                    type = "image/*"
                                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                                },
                                article?.title ?: ""
                            )
                            DetailedScreen(
                                onShare = {
                                    startActivity(share)
                                },
                                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                            )
                        }

                        composable(Route.SAVED) {
                            SavedScreen(
                                onArticleClick = { article ->
                                    val articleJson = Uri.encode(Gson().toJson(article))
                                    navController.navigate(
                                        Route.DETAILED
                                            .plus("/$articleJson")
                                    )
                                },
                                scaffoldState = scaffoldState,
                                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                            )
                        }

                        composable(Route.SETTINGS) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}



