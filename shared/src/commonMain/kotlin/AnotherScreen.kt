import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator

class AnotherScreen : Screen {

    @Composable
    override fun Content() {
        val mainNavigator = LocalNavigator.currentOrThrow

        Text("Post List Screen")

        Button(
            onClick = { mainNavigator.pop() }
        ) {
            Text("Go Back")
        }

    }


}