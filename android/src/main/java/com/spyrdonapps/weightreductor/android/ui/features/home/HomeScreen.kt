package com.spyrdonapps.weightreductor.android.ui.features.home

import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.spyrdonapps.weightreductor.R
import com.spyrdonapps.weightreductor.android.ui.custom.DefaultBottomBar
import com.spyrdonapps.weightreductor.android.ui.features.main.MainViewModel
import com.spyrdonapps.weightreductor.android.ui.features.meals.MealsScreen
import com.spyrdonapps.weightreductor.android.ui.features.products.ProductsScreen
import com.spyrdonapps.weightreductor.android.ui.features.profile.ProfileScreen
import com.spyrdonapps.weightreductor.android.ui.features.weighings.WeighingsScreen
import com.spyrdonapps.common.util.utils.exhaustive

@Composable
fun HomeScreen(viewModel: MainViewModel, goToSettings: () -> Unit) {
    val (currentTab, setCurrentTab) = savedInstanceState { HomeTab.Meals }
    val navItems = HomeTab.values().toList()
    // todo pass theme like in Jetsnack
    Scaffold(
        bottomBar = {
            DefaultBottomBar(
                currentTab = currentTab,
                onTabSelected = setCurrentTab,
                items = navItems
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Crossfade(currentTab) { tab ->
            when (tab) {
                HomeTab.Meals -> MealsScreen(modifier = modifier, viewModel)
                HomeTab.Products -> ProductsScreen(modifier, viewModel)
                HomeTab.Weighings -> WeighingsScreen(modifier, viewModel)
                HomeTab.Profile -> ProfileScreen(modifier, viewModel)
            }.exhaustive
        }
    }
}

enum class HomeTab(
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {
    Meals(R.string.meals, Icons.Outlined.List),
    Products(R.string.products, Icons.Outlined.ShoppingCart),
    Weighings(R.string.weighings, Icons.Outlined.AddCircle),
    Profile(R.string.profile, Icons.Outlined.Person),
}