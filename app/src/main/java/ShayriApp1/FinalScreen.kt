import android.content.Context
import android.content.Intent
import android.graphics.*
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun FinalScreen(FinalShayri: String) {
    var isFavourite by rememberSaveable { mutableStateOf(false) }
    var isSharing by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Function to create a beautiful image of the shayri
    suspend fun createShayriImage(): File? = withContext(Dispatchers.IO) {
        try {
            val width = 1080
            val height = 1080
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)

            // Create gradient background
            val gradientPaint = Paint().apply {
                shader = LinearGradient(
                    0f, 0f, 0f, height.toFloat(),
                    intArrayOf(
                        Color(0xFF1A0B33).toArgb(),
                        Color(0xFF0F0620).toArgb(),
                        Color(0xFF140B2E).toArgb()
                    ),
                    null,
                    Shader.TileMode.CLAMP
                )
            }
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gradientPaint)

            // Add subtle pattern/texture
            val patternPaint = Paint().apply {
                color = Color(0xFF018786).copy(alpha = 0.1f).toArgb()
                style = Paint.Style.FILL
            }

            // Draw some decorative circles
            canvas.drawCircle(200f, 200f, 150f, patternPaint)
            canvas.drawCircle(width - 200f, height - 200f, 180f, patternPaint)
            canvas.drawCircle(width - 150f, 300f, 100f, patternPaint)

            // Create main card background
            val cardPaint = Paint().apply {
                color = Color(0xFF2A1B47).copy(alpha = 0.9f).toArgb()
                style = Paint.Style.FILL
                isAntiAlias = true
            }

            val cardRect = RectF(80f, 300f, width - 80f, height - 300f)
            canvas.drawRoundRect(cardRect, 60f, 60f, cardPaint)

            // Add card glow effect
            val glowPaint = Paint().apply {
                color = Color(0xFF018786).copy(alpha = 0.3f).toArgb()
                style = Paint.Style.STROKE
                strokeWidth = 8f
                isAntiAlias = true
            }
            canvas.drawRoundRect(cardRect, 60f, 60f, glowPaint)

            // Draw shayri text
            val textPaint = Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 52f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }

            // Split text into lines and draw
            val lines = FinalShayri.split("\n")
            val lineHeight = textPaint.textSize + 20f
            val startY = (height / 2f) - ((lines.size - 1) * lineHeight / 2f)

            lines.forEachIndexed { index, line ->
                val y = startY + (index * lineHeight)
                canvas.drawText(line, width / 2f, y, textPaint)
            }

            // Add app branding at bottom
            val brandPaint = Paint().apply {
                color = Color(0xFF018786).toArgb()
                textSize = 32f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("✨ Shayri App", width / 2f, height - 100f, brandPaint)

            val subBrandPaint = Paint().apply {
                color = android.graphics.Color.WHITE
                alpha = 150
                textSize = 24f
                isAntiAlias = true
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("Beautiful Poetry for Beautiful Hearts", width / 2f, height - 60f, subBrandPaint)

            // Save image to cache directory
            val cacheDir = context.cacheDir
            val imageFile = File(cacheDir, "shayri_share_${System.currentTimeMillis()}.jpg")

            val fos = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, fos)
            fos.flush()
            fos.close()
            bitmap.recycle()

            imageFile
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Function to share the image
    fun shareShayriImage() {
        coroutineScope.launch {
            isSharing = true
            try {
                val imageFile = createShayriImage()
                if (imageFile != null) {
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        imageFile
                    )

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "image/jpeg"
                        putExtra(Intent.EXTRA_STREAM, uri)
                        putExtra(Intent.EXTRA_TEXT, "Check out this beautiful shayri! 🌟")
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }

                    context.startActivity(Intent.createChooser(shareIntent, "Share Beautiful Shayri"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isSharing = false
            }
        }
    }

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
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Main Shayri Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2A1B47).copy(alpha = 0.9f)
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF018786).copy(alpha = 0.3f),
                                    Color(0xFF2A1B47).copy(alpha = 0.8f)
                                ),
                                radius = 800f
                            )
                        )
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = FinalShayri,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Action Buttons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Share Button - Now creates and shares beautiful image
                FloatingActionButton(
                    onClick = { shareShayriImage() },
                    modifier = Modifier.size(56.dp),
                    containerColor = if (isSharing) Color(0xFF018786).copy(alpha = 0.7f) else Color(0xFF018786),
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    if (isSharing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Beautiful Shayri Image",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Favorite Button
                FloatingActionButton(
                    onClick = { isFavourite = !isFavourite },
                    modifier = Modifier.size(56.dp),
                    containerColor = if (isFavourite) Color(0xFFE91E63) else Color(0xFF424242),
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 12.dp
                    )
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite) "Remove from favorites" else "Add to favorites",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Updated hint text
            Text(
                text = if (isSharing) "Creating beautiful image..." else "Tap ❤️ to save • Tap ↗️ to share image",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinalScreenPreview() {
    FinalScreen("বন্ধুত্বের আসল মানে হলো অনুভব।\nসত্যিকারের বন্ধু হৃদয়ের কাছাকাছি থাকে।")
}