package com.example.profilejetpack_compose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.profilejetpack_compose.ui.theme.ProfileJetpackComposeTheme


val fontfamily = R.font.delagothicone_regular

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(10.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            
            Text(text = "Cancel", modifier = Modifier
                .clickable { }
                .padding(10.dp))
            Text(text = "Save", modifier = Modifier
                .clickable { }
                .padding(10.dp))
            
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
        .padding(10.dp)
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
                        Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"))

                    mcontext.startActivity(intent)
                },
                contentScale = ContentScale.Crop
                )
        }
        Text(text = "Change Profile Picture", modifier = Modifier.padding(10.dp),fontFamily = fontfamily1, fontWeight = FontWeight.Medium)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileJetpackComposeTheme {
        ProfileScreen()
    }
}