package com.q.qstorejetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.q.qstorejetpackcompose.ui.navigation.Screen
import com.q.qstorejetpackcompose.ui.screen.detail.DetailScreen
import com.q.qstorejetpackcompose.ui.screen.home.HomeScreen
import com.q.qstorejetpackcompose.ui.screen.profile.ProfileScreen
import com.q.qstorejetpackcompose.ui.theme.QStoreJetpackComposeTheme

@ExperimentalMaterial3Api
@Composable
fun QStoreApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(topBar = {
        if (currentRoute == Screen.Home.route) {
            TopBar(navController = navController, modifier = modifier.padding(start = 8.dp))
        }
    },
        content = { innerPadding ->
            NavHost(navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)) {
                composable(Screen.Home.route) {
                    HomeScreen(navigateToDetail = { phoneId ->
                        navController.navigate(Screen.DetailPhone.createRoute(phoneId))
                    })
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(navigateBack = { navController.navigateUp() })
                }
                composable(route = Screen.DetailPhone.route,
                    arguments = listOf(navArgument("phoneId") { type = NavType.LongType })) {
                    val id = it.arguments?.getLong("phoneId") ?: -1L
                    DetailScreen(phoneId = id, navigateBack = { navController.navigateUp() })
                }
            }
        })
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(
    navController: NavHostController,
    modifier: Modifier,
) {
    CenterAlignedTopAppBar(title = {
        Text(
            "QStore",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.h6,
        )
    }, navigationIcon = {
        Icon(painter = painterResource(R.drawable.qlogo),
            contentDescription = "Logo Q",
            modifier = modifier.size(32.dp))
    }, actions = {
        IconButton(onClick = {
            navController.navigate(Screen.Profile.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
                launchSingleTop = true
            }
        }) {
            Icon(imageVector = Icons.Filled.AccountCircle,
                contentDescription = "about_page",
                modifier = modifier.size(32.dp))
        }
    })
}

@Preview(showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun QStoreAppPreview() {
    QStoreJetpackComposeTheme {
        QStoreApp()
    }
}