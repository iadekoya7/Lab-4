package com.mad.lab4_bottom_navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mad.lab4_bottom_navigation.Navigation.BottomNavScreens

fun NavGraphBuilder.homeRoutes(
    homeNavController: NavHostController,
    composeNavController: NavHostController,
){
    composable(BottomNavScreens.Home.route){
        BalanceCard()
    }

    composable(BottomNavScreens.Customer.route){
        CustomerScreen()
    }

    composable(BottomNavScreens.Transactions.route){
        TransactionScreen()
    }

    composable(BottomNavScreens.Profile.route){
        ProfileScreen()
    }

}