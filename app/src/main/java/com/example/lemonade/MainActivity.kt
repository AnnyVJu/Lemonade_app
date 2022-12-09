package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}


@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCounter by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                LemonTextAndImage(
                    textResource = R.string.select_lemon,
                    painterResource = R.drawable.lemon_tree,
                    contentDescriptionResource = R.string.lemon_tree,
                    onImageClick = {
                        currentStep = 2
                        squeezeCounter = (1..3).random()
                    }
                )
            }
            2 -> {
                LemonTextAndImage(
                    textResource = R.string.squeeze,
                    painterResource = R.drawable.lemon_squeeze,
                    contentDescriptionResource = R.string.lemon,
                    onImageClick = {
                        squeezeCounter--
                        if (squeezeCounter == 0) {
                            currentStep = 3
                        }
                    }
                )
            }
            3 -> {
                LemonTextAndImage(
                textResource = R.string.glass,
                painterResource = R.drawable.lemon_drink,
                contentDescriptionResource = R.string.glass,
                onImageClick = { currentStep = 4 })
            }
            4 -> {
                LemonTextAndImage(
                textResource = R.string.empty_glass,
                painterResource = R.drawable.lemon_restart,
                contentDescriptionResource = R.string.empty_glass,
                onImageClick = { currentStep = 1 } )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textResource: Int,
    painterResource: Int,
    contentDescriptionResource: Int,
    onImageClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(painterResource),
            contentDescription = stringResource(contentDescriptionResource),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(3.dp, Color(105, 255, 100))
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}


