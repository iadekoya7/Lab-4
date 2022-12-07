package com.mad.lab4_bottom_navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mad.lab4_bottom_navigation.Navigation.BottomNavScreens
import com.mad.lab4_bottom_navigation.Navigation.SubMenuBottomNavigation
import com.mad.lab4_bottom_navigation.Navigation.currentRoute
import java.util.*

@Composable
fun HomeScreen(
    navController: NavController,
    composeHostController: NavHostController
) {
    val homeNavController = rememberNavController()
    val bottomNavigationItems = remember{
        buildList {
            add(BottomNavScreens.Home)
            add(BottomNavScreens.Customer)
            add(BottomNavScreens.Transactions)
            add(BottomNavScreens.Profile)
        }
    }
    val currentRoute = currentRoute(navController = homeNavController)
    val scaffoldState = rememberScaffoldState()
    val greetingMessage = remember {
        when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning"
            in 12..15 -> "Good Afternoon"
            in 16..23 -> "Good Evening"
            else -> "Good Day"
        }
    }
    val title = when (currentRoute) {
        BottomNavScreens.Transactions.route -> stringResource(BottomNavScreens.Transactions.stringRes)
        BottomNavScreens.Customer.route -> stringResource(BottomNavScreens.Customer.stringRes)
        BottomNavScreens.Profile.route -> ""
        else -> greetingMessage
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeAppBar(
                mainNavController = navController,
                title = title,
                navigationIcon = {
                    if (currentRoute != BottomNavScreens.Profile.route) {
                        IconButton(onClick = {
                            homeNavController.navigate(BottomNavScreens.Profile.route) {
                                launchSingleTop = true
                                popUpTo(BottomNavScreens.Home.route) {
                                    inclusive = false
                                }
                            }
                        }) {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary.copy(0.52f)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = { SubMenuBottomNavigation(homeNavController, bottomNavigationItems) },
        backgroundColor = colorResource(R.color.menuBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f),
            ) {
                NavHost(
                    navController = homeNavController,
                    startDestination = BottomNavScreens.Home.route
                ) {
                    homeRoutes(
                        homeNavController = homeNavController,
                        composeNavController = composeHostController,
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }
}