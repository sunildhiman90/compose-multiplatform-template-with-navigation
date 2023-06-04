import androidx.compose.runtime.staticCompositionLocalOf

data class ComposeScreenConfiguration(val width: Int, val height: Int)

val LocalComposeScreenConfiguration =
    staticCompositionLocalOf<ComposeScreenConfiguration> { noLocalProvidedFor("LocalComposeScreenConfiguration") }

private fun noLocalProvidedFor(name: String): Nothing {
    error("CompositionLocal $name not present")
}