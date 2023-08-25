package com.example.profilejetpack_compose

import android.content.Intent
import android.content.Loader
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


import com.example.profilejetpack_compose.ui.theme.ProfileJetpackComposeTheme


val fontfamily = R.font.delagothicone_regular

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Loaderbouncy()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(10.dp)
        ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ElevatedButton(onClick = {   }, modifier = Modifier
                .fillMaxHeight()
            ) {
                Text(text = "Cancel")
            }

            OutlinedButton(onClick = {   }, modifier = Modifier
                .fillMaxHeight()
            ) {
                Text(text = "Save")
            }

        }
        ProfileImage()
        
    }
}

@Composable
fun ProfileImage() {
    val mcontext = LocalContext.current

    val fontfamily1 = FontFamily(
        Font(R.font.delagothicone_regular)
    )

    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.profilepif_github
        else
            imageUri.value
    )
    
    Column(modifier = Modifier
        .padding(6.dp)
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(15.dp)
                .size(150.dp)
            ) {
            Image(painter = painter, contentDescription = null, modifier = Modifier
                .wrapContentSize()
                .clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media")
                    )

                    mcontext.startActivity(intent)
                    //startActivityforResult, to be executed
                },
                contentScale = ContentScale.Crop
                )
        }
        Text(text = "Profile Picture", modifier = Modifier.padding(10.dp),fontFamily = fontfamily1, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun Loaderbouncy() {
    val yOffset = remember{
        Animatable(0f)
    }
     val scale = remember{
         Animatable(1f)
     }

    LaunchedEffect("Loader") {
        delay(500)
        yOffset.animateTo(.5f,bouncyAnimationSpecific)
        scale.animateTo(3f,bouncyAnimationSpecific)

        delay(400)
        scale.animateTo(10f,bouncyAnimationSpecific)
        delay(400)
        scale.animateTo(50f,bouncyAnimationSpecific)
    }
    val size =  remember{
        mutableStateOf(IntSize.Zero)
    }

    Box(
        Modifier
            .fillMaxSize()
            .onSizeChanged {
                size.value = it
            }
    ) {
        GradientCircle(
            Modifier
                .align(Alignment.TopCenter)
                .size(26.dp)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                    translationY = yOffset.value * size.value.height
                }
        )
        ProfileScreen()

    }
}

@Composable
fun GradientCircle(modifier: Modifier = Modifier) {
    val brush = remember{
        Brush.horizontalGradient(listOf( Color.Cyan, Color.Yellow))
    }

    Canvas(modifier = modifier) {
        drawCircle(brush = brush)
    }
}

val bouncyAnimationSpecific: SpringSpec<Float> = spring(
    dampingRatio = Spring.DampingRatioHighBouncy,
    stiffness = Spring.StiffnessLow
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileJetpackComposeTheme {
        ProfileScreen()
    }
}