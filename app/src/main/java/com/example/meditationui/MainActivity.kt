package com.example.meditationui

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditationui.ui.HomeScreen
import com.example.meditationui.ui.theme.MeditationUITheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUITheme {
                Navigation()
            }
        }
    }
}


@Composable
fun SplashScreen(navController: NavController){

    val scale = remember {
        Animatable(0.5f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navController.navigate(Screen.MainScreen.route)
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Image(painterResource(id = R.drawable.nike_logo), contentDescription = "splash logo", modifier = Modifier.scale(scale.value))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {

        OutlinedTextField(value = text, onValueChange = {
            text = it
        }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(Screen.DetailScreen.withArgs(text))

        }, modifier = Modifier.align(Alignment.End)) {
            Text(text = "To Details Screen")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MeditationUITheme {
    }
}