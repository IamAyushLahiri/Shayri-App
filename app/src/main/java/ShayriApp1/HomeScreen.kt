import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shayriapp.ShayriAppScreen.ShayriScreenItem


@Composable
fun HomeScreenExample(navHostController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFF140B2E))
    ) {

        Column {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Share your thought with RHYTHM",
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(com.example.shayriapp.R.color.teal_200),
                        fontSize = 30.sp,
                        modifier = Modifier.padding(20.dp),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        painter = painterResource(com.example.shayriapp.R.drawable.rose),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(
                                RoundedCornerShape(100.dp),
                            )

                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Image(
                        painter = painterResource(com.example.shayriapp.R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(70.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        "Shayri Duniya",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(),
                        color = colorResource(id = com.example.shayriapp.R.color.white)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Box {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            color = colorResource(com.example.shayriapp.R.color.Orange)
                        )
                    }

                    android.os.Handler(Looper.getMainLooper()).postDelayed({
navHostController.navigate(ShayriScreenItem.catagoryScreen.route)
                    },2500)


                }


            }

        }


    }

}