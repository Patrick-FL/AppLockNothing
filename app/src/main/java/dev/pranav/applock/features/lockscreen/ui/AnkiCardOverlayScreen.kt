package dev.pranav.applock.features.lockscreen.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import dev.pranav.applock.core.utils.appLockRepository
import dev.pranav.applock.data.repository.AnkiCard
import dev.pranav.applock.data.repository.AnkiRepository

@Composable
fun AnkiCardOverlayScreen(
    lockedAppName: String,
    requiredCardCount: Int,
    onUnlock: () -> Unit,
    onFallbackToPin: () -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val ankiRepository = remember { AnkiRepository(context) }
    val appLockRepository = remember { context.appLockRepository() }
    val deckId = remember { appLockRepository.getAnkiDeckId() }
    val deckName = remember { appLockRepository.getAnkiDeckName() }

    var currentCard by remember { mutableStateOf<AnkiCard?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var showAnswer by remember { mutableStateOf(false) }
    var cardsRemaining by remember { mutableIntStateOf(requiredCardCount.coerceAtLeast(1)) }
    var cardStartTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    fun loadNextCard() {
        isLoading = true
        showAnswer = false
        val card = ankiRepository.getNextDueCard(deckId, deckName)
        if (card == null) {
            if (cardsRemaining < requiredCardCount) {
                // User already completed some cards
                Toast.makeText(context, "Keine weiteren Karten fällig!", Toast.LENGTH_SHORT).show()
                onUnlock()
            } else {
                // No cards due on initial load: stay on Anki screen and inform user
                currentCard = null
                isLoading = false
            }
        } else {
            currentCard = card
            cardStartTime = System.currentTimeMillis()
            isLoading = false
        }
    }

    LaunchedEffect(Unit) {
        loadNextCard()
    }

    fun handleAnswer(ease: Int) {
        val card = currentCard ?: return
        val timeTaken = System.currentTimeMillis() - cardStartTime
        ankiRepository.answerCard(card, ease, timeTaken)

        cardsRemaining--
        if (cardsRemaining <= 0) {
            onUnlock()
        } else {
            loadNextCard()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(
                            text = lockedAppName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(onClick = onClose) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Schließen"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Cards Remaining Badge
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = if (currentCard == null) "Anki Karteikarten-Modus"
                        else if (cardsRemaining == 1) "Noch 1 Anki-Karte zu beantworten"
                        else "Noch $cardsRemaining Anki-Karten zu beantworten",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    val card = currentCard
                    if (card != null) {
                        // Card Content Area
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp)
                            ) {
                                // Question
                                Text(
                                    text = "Frage:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                HtmlCardView(
                                    htmlContent = card.question,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(if (showAnswer) 1f else 2f)
                                )

                                if (showAnswer) {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(vertical = 8.dp),
                                        color = MaterialTheme.colorScheme.outlineVariant
                                    )
                                    // Answer
                                    Text(
                                        text = "Antwort:",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.secondary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    HtmlCardView(
                                        htmlContent = card.answer,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Controls
                        if (!showAnswer) {
                            Button(
                                onClick = { showAnswer = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "Antwort zeigen",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            // 4 Rating Buttons
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = { handleAnswer(AnkiRepository.EASE_AGAIN) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.error
                                        )
                                    ) {
                                        Text("Nochmal")
                                    }

                                    Button(
                                        onClick = { handleAnswer(AnkiRepository.EASE_HARD) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.tertiary
                                        )
                                    ) {
                                        Text("Schwer")
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Button(
                                        onClick = { handleAnswer(AnkiRepository.EASE_GOOD) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Text("Gut")
                                    }

                                    Button(
                                        onClick = { handleAnswer(AnkiRepository.EASE_EASY) },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = MaterialTheme.colorScheme.secondary
                                        )
                                    ) {
                                        Text("Einfach")
                                    }
                                }
                            }
                        }
                    } else {
                        // 0 cards due info card
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(64.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Keine Karteikarten fällig!",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "In AnkiDroid sind aktuell keine Wiederholungskarten für heute fällig.\n\nDu kannst die App direkt freischalten oder mit deiner PIN entsperren.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Button(
                                    onClick = onUnlock,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("App jetzt freigeben")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Fallback to PIN button
                OutlinedButton(
                    onClick = onFallbackToPin,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Mit PIN / Muster entsperren")
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlCardView(
    htmlContent: String,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val textColor = if (isDark) "#E2E2E2" else "#1C1B1F"

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                settings.javaScriptEnabled = false
                settings.defaultFontSize = 18
                setBackgroundColor(Color.TRANSPARENT)
            }
        },
        update = { webView ->
            val styledHtml = """
                <!DOCTYPE html>
                <html>
                <head>
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        color: $textColor;
                        background-color: transparent;
                        font-family: sans-serif;
                        text-align: center;
                        padding: 8px;
                        font-size: 18px;
                        word-wrap: break-word;
                    }
                    img { max-width: 100%; height: auto; }
                </style>
                </head>
                <body>
                    $htmlContent
                </body>
                </html>
            """.trimIndent()
            webView.loadDataWithBaseURL(null, styledHtml, "text/html", "utf-8", null)
        },
        modifier = modifier
    )
}
