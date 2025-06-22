import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
    val listState = rememberLazyListState()

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A0B33),
                            Color(0xFF0F0620),
                            Color(0xFF140B2E)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Toolbar
                MainToolBar(title = title.toString()) {}

                // Content
                val myFinalList = getlist().filter {
                    it.title == title.toString()
                }

                if (myFinalList.isNotEmpty()) {
                    val finalList = myFinalList[0]

                    finalList.list?.let { list ->
                        LazyColumn(
                            state = listState,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            contentPadding = PaddingValues(
                                horizontal = 20.dp,
                                vertical = 16.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(list) { item ->
                                ShayriCard(
                                    shayriText = item,
                                    onClick = {
                                        navHostController?.navigate(
                                            ShayriScreenItem.FinalScreen.route + "/${item}"
                                        )
                                    }
                                )
                            }

                            // Add some bottom padding for the last item
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                } else {
                    // Empty state
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No shayri found for this category",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShayriCard(
    shayriText: String,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable {
                isPressed = true
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isPressed) 12.dp else 6.dp,
            pressedElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2A1B47).copy(alpha = 0.9f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF018786).copy(alpha = 0.1f),
                            Color(0xFF372372).copy(alpha = 0.8f),
                            Color(0xFF018786).copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Text(
                text = shayriText,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                lineHeight = 26.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // Reset pressed state after animation
    LaunchedEffect(isPressed) {
        if (isPressed) {
            kotlinx.coroutines.delay(100)
            isPressed = false
        }
    }
}

// Alternative compact card design
@Composable
fun CompactShayriCard(
    shayriText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF372372).copy(alpha = 0.9f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF018786))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = shayriText,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
    }
}