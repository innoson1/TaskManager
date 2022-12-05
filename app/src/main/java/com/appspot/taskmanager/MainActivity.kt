package com.appspot.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.R
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appspot.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ImageAndTextWithPaddingToBaseline()
                }
            }
        }
    }
}
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}

@Composable
fun ImageAndTextWithPaddingToBaseline() {
    TaskManagerTheme() {
       val image = painterResource(com.appspot.taskmanager.R.drawable.ic_task_completed)

        Image(painter = image, contentDescription = "banner", alignment = Alignment.Center,
        contentScale = ContentScale.Inside, )
        Text("All tasks completed",
            Modifier
                .firstBaselineToTop(950.dp)
                .padding( top = 24.dp, bottom = 8.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,

            )
        Text("Nice work!",
            Modifier
                .firstBaselineToTop(1000.dp)
                .padding(top = 16.dp, bottom = 16.dp),
            fontSize = 16.sp, textAlign = TextAlign.Center

            )



    }
}






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TaskManagerTheme {
        ImageAndTextWithPaddingToBaseline()
    }
}