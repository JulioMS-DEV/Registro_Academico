package ni.edu.uam.uamregister.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ni.edu.uam.uamregister.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "UAM Register",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sistema de gestión de cursos y estudiantes",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.Cursos.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Gestionar Cursos")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.Estudiantes.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors()
                ) {

                    Text("Gestionar Estudiantes")
                }
            }
        }
    }
}