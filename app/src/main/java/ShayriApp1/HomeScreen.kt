import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shayriapp.ShayriAppScreen.ShayriScreenItem
import kotlinx.coroutines.delay

@Composable
fun HomeScreenExample(navHostController: NavHostController) {
    var isVisible by remember { mutableStateOf(false) }
    var showContent by remember { mutableStateOf(false) }

    // Animation states
    val fadeInAlpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "fadeIn"
    )

    val slideInOffset by animateIntAsState(
        targetValue = if (showContent) 0 else 100,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "slideIn"
    )

    val scaleAnimation by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "scale"
    )

    // Rotation animation for rose image
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteRotation")
    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    // Pulsing animation for logo
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Shimmer effect for the main text
    val shimmerTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerOffset by shimmerTransition.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerOffset"
    )

    // Start animations
    LaunchedEffect(Unit) {
        isVisible = true
        delay(500)
        showContent = true
        delay(3000) // Wait for animations to complete
        navHostController.navigate(ShayriScreenItem.catagoryScreen.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF2A1B47),
                        Color(0xFF1A0B33),
                        Color(0xFF0F0620),
                        Color(0xFF140B2E)
                    ),
                    radius = 1200f
                )
            )
            .clickable {
                navHostController.navigate(ShayriScreenItem.catagoryScreen.route)
            }
    ) {
        // Background decorative elements
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = fadeInAlpha * 0.1f
                }
        ) {
            // Decorative circles
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size((100 + index * 50).dp)
                        .offset(
                            x = (50 + index * 80).dp,
                            y = (100 + index * 150).dp
                        )
                        .rotate(rotationAngle * (0.3f + index * 0.1f))
                        .background(
                            Color(0xFF018786).copy(alpha = 0.1f),
                            CircleShape
                        )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Main title with shimmer effect
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = fadeInAlpha
                        translationY = slideInOffset.toFloat()
                        scaleX = scaleAnimation
                        scaleY = scaleAnimation
                    }
            ) {
                Text(
                    "Share your thought with RHYTHM",
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(com.example.shayriapp.R.color.teal_200),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.3f),
                                    Color.Transparent
                                ),
                                start = androidx.compose.ui.geometry.Offset(shimmerOffset - 200, 0f),
                                end = androidx.compose.ui.geometry.Offset(shimmerOffset + 200, 0f)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Rose image with rotation and scale
            AnimatedVisibility(
                visible = showContent,
                enter = scaleIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) + fadeIn(animationSpec = tween(1000))
            ) {
                Card(
                    modifier = Modifier
                        .size(220.dp)
                        .rotate(rotationAngle * 0.1f),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                ) {
                    Image(
                        painter = painterResource(com.example.shayriapp.R.drawable.rose),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF018786).copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Logo with pulsing animation
            AnimatedVisibility(
                visible = showContent,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) + fadeIn(animationSpec = tween(800))
            ) {
                Card(
                    modifier = Modifier
                        .size(90.dp)
                        .scale(pulseScale),
                    shape = CircleShape,
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2A1B47).copy(alpha = 0.9f)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        Color(0xFF018786).copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(com.example.shayriapp.R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // App name with slide animation
            AnimatedVisibility(
                visible = showContent,
                enter = slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) + fadeIn(animationSpec = tween(1000))
            ) {
                Card(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF372372).copy(alpha = 0.8f)
                    )
                ) {
                    Text(
                        "Shayri Duniya",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = colorResource(id = com.example.shayriapp.R.color.white),
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Elegant loading indicator
            AnimatedVisibility(
                visible = showContent,
                enter = scaleIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ) + fadeIn(animationSpec = tween(1200))
            ) {
                LoadingDots()
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Tap to continue hint
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 2000,
                        delayMillis = 1500
                    )
                )
            ) {
                Text(
                    "Tap anywhere to continue...",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun LoadingDots() {
    val infiniteTransition = rememberInfiniteTransition(label = "loadingDots")

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 600,
                        delayMillis = index * 200,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dot$index"
            )

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(scale)
                    .background(
                        Color(0xFF018786),
                        CircleShape
                    )
            )
        }
    }
}
