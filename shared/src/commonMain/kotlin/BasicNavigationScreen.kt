import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

data class BasicNavigationScreen(
    val index: Int,
    val wrapContent: Boolean = false,
    val nestedNavigation: Boolean = true,
) : Screen {

    override val key = uniqueScreenKey

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {


        LifecycleEffect(
            onStarted = { println("Navigator Start screen #$index") },
            onDisposed = { println("Navigator Dispose screen #$index") },
        )

        //this will provide main Navigator if we have not used BasicNavigationScreen inside Navigator for example for HomeTab
        // And this will provide this screen's parent navigator if we have used BasicNavigationScreen inside Navigator for example for ProfileTab or FavouritesTab
        //In short [LocalNavigator.currentOrThrow] provides current screen's nearest parent navigator
        val navigator = LocalNavigator.currentOrThrow

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.run {
                if (wrapContent) padding(vertical = 16.dp).wrapContentHeight()
                else fillMaxSize()
            }
        ) {

            Text(
                text = if (nestedNavigation) "With Nested Navigation" else "Without Nested Navigation",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))

            InnerTabNavigation()
            Spacer(modifier = Modifier.height(56.dp))

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Image Resource from commonMain",
                style = MaterialTheme.typography.bodySmall
            )
            Image(
                painter = painterResource("MR/images/add_post_icon@1x.png"),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Screen #$index",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    enabled = navigator.canPop,
                    onClick = navigator::pop,
                    modifier = Modifier.weight(.5f)
                ) {
                    Text(text = "Pop")
                }

                Spacer(modifier = Modifier.weight(.1f))

                Button(
                    onClick = { navigator.push(BasicNavigationScreen(index.inc(), wrapContent)) },
                    modifier = Modifier.weight(.5f)
                ) {
                    Text(text = "Push")
                }

                Spacer(modifier = Modifier.weight(.1f))

//                Button(
//                    onClick = { navigator.replace(TestNavigationScreen(navigator,index.inc(), wrapContent)) },
//                    modifier = Modifier.weight(.5f)
//                ) {
//                    Text(text = "Replace")
//                }
            }

            LazyColumn(
                modifier = Modifier.height(100.dp)
            ) {
                items(100) {
                    Text("Item #$it")
                }
            }
        }
    }
}