package ni.edu.uam.uamregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ni.edu.uam.uamregister.navigation.AppNavigation
import ni.edu.uam.uamregister.ui.theme.UamRegisterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            UamRegisterTheme {

                AppNavigation()
            }
        }
    }
}