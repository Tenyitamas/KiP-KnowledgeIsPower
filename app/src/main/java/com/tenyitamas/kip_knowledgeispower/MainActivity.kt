package com.tenyitamas.kip_knowledgeispower

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.tenyitamas.kip_knowledgeispower.domain.model.Article
import com.tenyitamas.kip_knowledgeispower.domain.model.Source
import com.tenyitamas.kip_knowledgeispower.navigation.Route
import com.tenyitamas.kip_knowledgeispower.presentation.detailed.DetailedScreen
import com.tenyitamas.kip_knowledgeispower.presentation.search.SearchScreen
import com.tenyitamas.kip_knowledgeispower.presentation.settings.SettingsScreen
import com.tenyitamas.kip_knowledgeispower.presentation.shared.BottomNavItem
import com.tenyitamas.kip_knowledgeispower.presentation.shared.BottomNavigationBar
import com.tenyitamas.kip_knowledgeispower.presentation.shared.NewsItem
import com.tenyitamas.kip_knowledgeispower.ui.theme.KiPKnowledgeIsPowerTheme
import com.tenyitamas.kip_knowledgeispower.util.AssetParamType
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
                    bottomBar = {
                        BottomNavigationBar(
                            items = bottomNavItems(),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SEARCH
                    ) {
                        composable(Route.SEARCH) {
                            SearchScreen(
                                onNavigateToOverview = { article ->
                                    val articleJson = Uri.encode(Gson().toJson(article))
                                    navController.navigate(
                                        Route.DETAILED
                                            .plus("/$articleJson")
                                    )
                                }
                            )
                        }

                        composable(
                            route = Route.DETAILED
                                .plus("/{article}"),
                            arguments = listOf(
                                navArgument("article") {
                                    type = AssetParamType()
                                }
                            )
                        ) {
                            val article = it.arguments?.getParcelable<Article>("article")
                            DetailedScreen(
                                article = article
                            )
                        }

                        composable(Route.SAVED) {
                            Text(
                                text = "HELLO SAVED",
                                modifier = Modifier.fillMaxSize()
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



