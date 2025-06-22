import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinalScreen(FinalShayri : String ) {

    val favourite by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFF140B2E)),
        contentAlignment = Alignment.Center
    ) {

        Column {
            Box {
                Card(
                    modifier = Modifier.padding(start = 20.dp),
                    shape = RoundedCornerShape(25.dp),
                    border = _root_ide_package_.androidx.compose.foundation.BorderStroke(
                        1.dp,
                        color = colorResource(com.example.shayriapp.R.color.white)
                    ),
                    colors = CardDefaults.cardColors(
                        _root_ide_package_.androidx.compose.ui.graphics.Color(0xFF018786).copy(0.4f)
                    )
                ) {
                    Text(
                        text = FinalShayri,
                        modifier = Modifier.padding(
                            start = 15.dp,
                            top = 5.dp,
                            end = 15.dp,
                            bottom = 10.dp
                        ),
                        color = colorResource(com.example.shayriapp.R.color.white),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }

                Row (modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceAround){
                    Button(
                        onClick = {}
                    ) {
                        Icon(imageVector = Icons.Default.Share , contentDescription = null)
                    }
                    Button(

                        onClick = {
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null)
                    }
                }

        }



        }

    }




//@Preview
//@Composable
//fun prev () {
//
//    FinalScreen("বন্ধুত্বের আসল মানে হলো অনুভব।")
//
//}