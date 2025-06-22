import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shayriapp.ShayriAppScreen.ShayriScreenItem


@Preview
@Composable
 fun Example() {
ShayriListScreen(title = "Love")
}
@Composable
fun ShayriListScreen(navHostController: NavHostController? = null, title: String?) {


    Surface() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFF140B2E))
        ) {
                Column {

                    MainToolBar(title = title.toString()){}
                    val myFinalList = getlist().filter {
                        it.title==title.toString()
                    }

                    val finalList = myFinalList[0]

                    finalList.list?.let { list ->

                            LazyColumn (modifier = Modifier.weight(1f).fillMaxHeight()) {
                                items(list) { item ->
                                    Card (modifier = Modifier.padding(horizontal = 30.dp , vertical = 8.dp).fillMaxWidth().clickable{
                                        navHostController?.navigate(ShayriScreenItem.FinalScreen.route+"/${item}")
                                    },
                                        colors = CardDefaults.cardColors(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFF372372))){
                                    Text(text = item , color = colorResource(com.example.shayriapp.R.color.white), textAlign = TextAlign.Center , modifier = Modifier.padding(10.dp),
                                        fontSize = 20.sp)
                                }
                            }
                        }
                    }
                }


                }

        }


    }

