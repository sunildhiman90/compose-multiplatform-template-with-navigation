import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator

class PostListScreen(private val title: String = "With Nested Navigation") : Screen {

    @Composable
    override fun Content() {
        val mainNavigator = LocalNavigator.currentOrThrow

        val tabNavigator = LocalTabNavigator.current

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(title)

//            Button(
//                onClick = { tabNavigator.current = ProfileTab() }
//            ) {
//                Text("Go To ProfileTab")
//            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { mainNavigator.pop() }
            ) {
                Text("Go Back")
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { mainNavigator.push(AnotherScreen()) }
            ) {
                Text("Go To Another Screen")
            }
        }
    }


}
