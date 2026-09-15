package com.example.weatherforecast.ui.feature.home



import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R


@Composable
fun MyImage(imageBitmap: ImageBitmap?) {
    val modifier = Modifier
        .width(100.dp)
        .height(100.dp)

    return if (imageBitmap == null) {
        Image(

            painter = painterResource((R.drawable.ic_launcher_background)),
            contentDescription = "",
        )
    } else {
        Image(
            modifier = Modifier,
            bitmap = imageBitmap,
            contentDescription = ""
        )
    }
}
@Composable
fun HomeScreen( context: Context) {
        var imageBitmap: ImageBitmap? by remember { mutableStateOf(value = null) }
        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) {bitmap -> imageBitmap = bitmap?.asImageBitmap()}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.TopStart,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    cameraLauncher.launch()
                }
            ,
            contentAlignment = Alignment.BottomEnd
        ){
            MyImage(imageBitmap = imageBitmap)

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // 1. Titre "Aujourd'hui"
            Text(
                text = stringResource(R.string.today),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(R.drawable.il_sun_cloud), // Remplace par l'icône de soleil si tu l'as ajoutée
                contentDescription = "Illustration météo",
                modifier = Modifier.fillMaxWidth(0.6f)
            )


            // 3. Température
            Text(
                text = "25 °C",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = { Toast.makeText(context, "j'ai cliqué", Toast.LENGTH_LONG).show() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.homeScreen_detail),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
