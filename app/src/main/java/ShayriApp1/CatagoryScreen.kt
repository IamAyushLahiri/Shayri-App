import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shayriapp.ShayriAppScreen.ShayriScreenItem

@Composable
fun CatagoryScreen(navHostController: NavHostController) {
    Surface() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFF140B2E))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                    MainToolBar(title = "Catagories for you", { })

                LazyColumn {
                    items(getlist()){ item ->
                        Card (
                            modifier = Modifier.fillMaxWidth().height(90.dp).padding(10.dp).clickable(){
                                navHostController.navigate(ShayriScreenItem.ShayriListScreen.route+"/${item.title.toString()}")
                            },
                            shape = RoundedCornerShape(30.dp),
                            colors = CardDefaults.cardColors(_root_ide_package_.androidx.compose.ui.graphics.Color(0xFFBB86FC))
                        ){
                            Box (
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ){

                                Text(text = item.title.toString(), fontWeight = FontWeight.Bold , fontSize = 25.sp)

                            }



                        }
                    }
                }

            }

        }
    }




}