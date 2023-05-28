import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.transitions.SlideTransition


//TODO, instead of using Navigator in TabContent, try to use it outside all tabs and tab content, so that new route will show up without tabs
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Tab.TabContent(nestedNavigation: Boolean = true) {
    val tabTitle = options.title

    LifecycleEffect(
        onStarted = {
            //Log.d("Navigator", "Start tab $tabTitle")
            },
        onDisposed = {
            //Log.d("Navigator", "Dispose tab $tabTitle")
            },
    )

    val mainNavigator = LocalNavigator.currentOrThrow

    Navigator(screen = BasicNavigationScreen(index = 0)) { navigator ->
        val navigatorToUse = if(nestedNavigation) navigator else mainNavigator
        SlideTransition(navigatorToUse) { screen ->
            Column {
                InnerTabNavigation()
                screen.Content() //this one too need to comment out for running on android
                //navigator.lastItem.Content() //this one too need to be uncommented for running on android
                println("Navigator Last Event: ${navigatorToUse.lastEvent}")
            }
        }
    }
}

@Composable
fun InnerTabNavigation() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        TabNavigationButton(HomeTab())

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(FavoritesTab())

        Spacer(modifier = Modifier.weight(.05f))

        TabNavigationButton(ProfileTab())
    }
}

@Composable
private fun RowScope.TabNavigationButton(
    tab: Tab
) {
    val tabNavigator = LocalTabNavigator.current

    Button(
        enabled = tabNavigator.current.key != tab.key,
        onClick = { tabNavigator.current = tab },
        modifier = Modifier.weight(1f)
    ) {
        Text(text = tab.options.title)
    }
}