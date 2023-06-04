import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator


@OptIn(
    ExperimentalVoyagerApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun App() {

    MaterialTheme {

        lateinit var composeConfiguration: ComposeScreenConfiguration
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            composeConfiguration =
                ComposeScreenConfiguration(constraints.maxWidth, constraints.maxHeight)

            CompositionLocalProvider(LocalComposeScreenConfiguration provides composeConfiguration) {
                println("screen_width=${LocalComposeScreenConfiguration.current.width}")

                TabNavigator(
                    HomeTab(),
                    tabDisposable = {
                        TabDisposable(
                            navigator = it,
                            tabs = listOf(HomeTab(), FavoritesTab(), ProfileTab())
                        )
                    }
                ) {

                    // then it will provide normal navigation without nested navigation if we are not using nested navigation for each tab
                    Navigator(MainScreen())
                }
            }

        }
    }

}

@Composable
fun BottomBar(
) {
    NavigationBar {
        TabNavigationItem(HomeTab())
        TabNavigationItem(FavoritesTab())
        TabNavigationItem(ProfileTab())
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}


expect fun getPlatformName(): String