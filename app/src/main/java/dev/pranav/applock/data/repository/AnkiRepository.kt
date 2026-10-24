package dev.pranav.applock.data.repository

import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat

data class AnkiCard(
    val noteId: Long,
    val cardOrd: Int,
    val cardId: Long,
    val question: String,
    val answer: String,
    val buttonCount: Int,
    val deckName: String?
)

data class AnkiDeck(
    val id: Long,
    val name: String
)

sealed class AnkiStatus {
    object NotInstalled : AnkiStatus()
    object PermissionDenied : AnkiStatus()
    data class Ready(val cardAvailable: Boolean) : AnkiStatus()
    data class Error(val message: String) : AnkiStatus()
}

class AnkiRepository(private val context: Context) {

    companion object {
        private const val TAG = "AnkiRepository"

        const val ANKI_PERMISSION = "com.ichi2.anki.permission.READ_WRITE_DATABASE"

        // ReviewInfo columns
        const val COL_NOTE_ID = "note_id"
        const val COL_CARD_ORD = "card_ord"
        const val COL_CARD_ID = "card_id"
        const val COL_QUESTION = "question"
        const val COL_ANSWER = "answer"
        const val COL_BUTTON_COUNT = "button_count"
        const val COL_DECK_NAME = "deck_name"
        const val COL_EASE = "ease"
        const val COL_TIME_TAKEN = "time_taken"

        val PROJECTION = arrayOf(
            COL_NOTE_ID,
            COL_CARD_ORD,
            COL_CARD_ID,
            COL_QUESTION,
            COL_ANSWER,
            COL_BUTTON_COUNT,
            COL_DECK_NAME
        )

        // Ease constants
        const val EASE_AGAIN = 1
        const val EASE_HARD = 2
        const val EASE_GOOD = 3
        const val EASE_EASY = 4
    }

    /**
     * Checks if runtime permission for AnkiDroid is granted.
     */
    fun hasAnkiPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            ANKI_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Resolves the correct ContentProvider authority for AnkiDroid on this device.
     */
    fun getAnkiAuthority(): String {
        val pm = context.packageManager
        val knownPackages = listOf(
            "com.ichi2.anki",
            "com.ichi2.anki.debug",
            "com.ichi2.anki.alpha",
            "com.ichi2.anki.parallel"
        )

        for (pkg in knownPackages) {
            try {
                val packageInfo = pm.getPackageInfo(pkg, PackageManager.GET_PROVIDERS)
                val providers = packageInfo.providers
                if (providers != null) {
                    for (provider in providers) {
                        val auth = provider.authority
                        if (auth != null && auth.contains("flashcards")) {
                            Log.d(TAG, "Found Anki authority '$auth' in package '$pkg'")
                            return auth
                        }
                    }
                }
            } catch (_: Exception) {
            }
        }

        try {
            val allPackages = pm.getInstalledPackages(PackageManager.GET_PROVIDERS)
            for (pkgInfo in allPackages) {
                if (pkgInfo.packageName.contains("anki", ignoreCase = true)) {
                    val providers = pkgInfo.providers
                    if (providers != null) {
                        for (provider in providers) {
                            val auth = provider.authority
                            if (auth != null && auth.contains("flashcards")) {
                                Log.d(TAG, "Found Anki authority '$auth' in package '${pkgInfo.packageName}'")
                                return auth
                            }
                        }
                    }
                }
            }
        } catch (_: Exception) {
        }

        return "com.ichi2.anki.flashcards"
    }

    /**
     * Checks if AnkiDroid application is installed on the device.
     */
    fun isAnkiInstalled(): Boolean {
        val pm = context.packageManager
        val knownPackages = listOf(
            "com.ichi2.anki",
            "com.ichi2.anki.debug",
            "com.ichi2.anki.alpha",
            "com.ichi2.anki.parallel"
        )
        for (pkg in knownPackages) {
            try {
                pm.getPackageInfo(pkg, 0)
                return true
            } catch (_: Exception) {
            }
        }
        return false
    }

    /**
     * Queries all available decks from AnkiDroid with resilient column index resolution.
     */
    fun getDecks(): List<AnkiDeck> {
        if (!isAnkiInstalled() || !hasAnkiPermission()) return emptyList()

        val authority = getAnkiAuthority()
        val decksUri = Uri.parse("content://$authority/decks")
        val result = mutableListOf<AnkiDeck>()

        try {
            val cursor = context.contentResolver.query(
                decksUri,
                null,
                null,
                null,
                null
            ) ?: return result

            cursor.use { c ->
                var idIdx = c.getColumnIndex("_id")
                if (idIdx < 0) idIdx = c.getColumnIndex("deck_id")
                if (idIdx < 0) idIdx = c.getColumnIndex("id")

                var nameIdx = c.getColumnIndex("deck_name")
                if (nameIdx < 0) nameIdx = c.getColumnIndex("name")
                if (nameIdx < 0) nameIdx = c.getColumnIndex("dname")

                var rowIndex = 0
                while (c.moveToNext()) {
                    val id = if (idIdx >= 0) {
                        try { c.getLong(idIdx) } catch (_: Exception) { (rowIndex + 1).toLong() }
                    } else {
                        (rowIndex + 1).toLong()
                    }

                    val name = if (nameIdx >= 0) {
                        try { c.getString(nameIdx) ?: "" } catch (_: Exception) { "" }
                    } else if (c.columnCount > 1) {
                        try { c.getString(1) ?: "" } catch (_: Exception) { "" }
                    } else if (c.columnCount > 0) {
                        try { c.getString(0) ?: "" } catch (_: Exception) { "" }
                    } else ""

                    if (name.isNotBlank()) {
                        result.add(AnkiDeck(id, name))
                    }
                    rowIndex++
                }
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Error querying decks from AnkiDroid", e)
        }

        return result
    }

    /**
     * Checks connection and card status for diagnostic display without throwing exceptions.
     */
    fun checkAnkiStatus(targetDeckId: Long = 0L, targetDeckName: String? = null): AnkiStatus {
        if (!isAnkiInstalled()) {
            return AnkiStatus.NotInstalled
        }

        if (!hasAnkiPermission()) {
            return AnkiStatus.PermissionDenied
        }

        return try {
            val card = getNextDueCard(targetDeckId, targetDeckName)
            if (card != null) {
                AnkiStatus.Ready(cardAvailable = true)
            } else {
                AnkiStatus.Ready(cardAvailable = false)
            }
        } catch (e: SecurityException) {
            AnkiStatus.PermissionDenied
        } catch (e: Throwable) {
            val msg = e.message ?: e.localizedMessage ?: "Unbekannter Fehler"
            if (msg.contains("is not supported", ignoreCase = true) ||
                msg.contains("disabled", ignoreCase = true) ||
                msg.contains("not allowed", ignoreCase = true)
            ) {
                AnkiStatus.PermissionDenied
            } else {
                AnkiStatus.Error("AnkiDroid: $msg")
            }
        }
    }

    /**
     * Queries the next due card from AnkiDroid.
     * Searches specified deck or iterates across all available decks.
     */
    fun getNextDueCard(targetDeckId: Long = 0L, targetDeckName: String? = null): AnkiCard? {
        if (!isAnkiInstalled() || !hasAnkiPermission()) {
            Log.d(TAG, "AnkiDroid is not installed or permission missing")
            return null
        }

        val authority = getAnkiAuthority()

        // 1. If a specific deck was selected, query that deck
        if (targetDeckId > 0L || !targetDeckName.isNullOrBlank()) {
            val card = fetchCardForDeck(authority, targetDeckId, targetDeckName)
            if (card != null) return card
        }

        // 2. Query default (currently selected deck in AnkiDroid)
        val defaultCard = fetchCardForDeck(authority, 0L, null)
        if (defaultCard != null) return defaultCard

        // 3. Fallback: Iterate across all discovered decks
        val decks = getDecks()
        for (deck in decks) {
            val deckCard = fetchCardForDeck(authority, deck.id, deck.name)
            if (deckCard != null) return deckCard
        }

        return null
    }

    private fun fetchCardForDeck(authority: String, deckId: Long, deckName: String?): AnkiCard? {
        val baseUri = Uri.parse("content://$authority/review_info")
        val candidates = mutableListOf<Pair<Uri, Pair<String?, Array<String>?>>>()

        if (deckId > 0L) {
            val uriWithId = baseUri.buildUpon().appendQueryParameter("deck_id", deckId.toString()).build()
            candidates.add(uriWithId to (null to null))
            candidates.add(baseUri to ("deck_id = ?" to arrayOf(deckId.toString())))
            candidates.add(baseUri to ("deck_id = $deckId" to null))
        }

        if (!deckName.isNullOrBlank() && deckName != "Alle Stapel") {
            val uriWithName = baseUri.buildUpon().appendQueryParameter("deck_name", deckName).build()
            candidates.add(uriWithName to (null to null))
            candidates.add(baseUri to ("deck_name = ?" to arrayOf(deckName)))
        }

        candidates.add(baseUri to (null to null))

        for ((uri, selectPair) in candidates) {
            val (selection, selectionArgs) = selectPair
            val card = queryReviewInfoSingle(uri, selection, selectionArgs)
            if (card != null) {
                Log.d(TAG, "Successfully fetched card using URI: $uri, selection: $selection")
                return card
            }
        }

        return null
    }

    private fun queryReviewInfoSingle(uri: Uri, selection: String?, selectionArgs: Array<String>?): AnkiCard? {
        try {
            val cursor = context.contentResolver.query(
                uri,
                null,
                selection,
                selectionArgs,
                null
            ) ?: return null

            cursor.use { c ->
                if (c.moveToFirst()) {
                    val cols = c.columnNames.joinToString(", ")
                    Log.d(TAG, "review_info columns: $cols")

                    var noteIdIdx = c.getColumnIndex(COL_NOTE_ID)
                    if (noteIdIdx < 0) noteIdIdx = c.getColumnIndex("note_id")
                    if (noteIdIdx < 0) noteIdIdx = c.getColumnIndex("nid")

                    var cardOrdIdx = c.getColumnIndex(COL_CARD_ORD)
                    if (cardOrdIdx < 0) cardOrdIdx = c.getColumnIndex("card_ord")
                    if (cardOrdIdx < 0) cardOrdIdx = c.getColumnIndex("ord")

                    var cardIdIdx = c.getColumnIndex(COL_CARD_ID)
                    if (cardIdIdx < 0) cardIdIdx = c.getColumnIndex("card_id")
                    if (cardIdIdx < 0) cardIdIdx = c.getColumnIndex("cid")

                    var questionIdx = c.getColumnIndex(COL_QUESTION)
                    if (questionIdx < 0) questionIdx = c.getColumnIndex("question")
                    if (questionIdx < 0) questionIdx = c.getColumnIndex("q")

                    var answerIdx = c.getColumnIndex(COL_ANSWER)
                    if (answerIdx < 0) answerIdx = c.getColumnIndex("answer")
                    if (answerIdx < 0) answerIdx = c.getColumnIndex("a")

                    var buttonCountIdx = c.getColumnIndex(COL_BUTTON_COUNT)
                    if (buttonCountIdx < 0) buttonCountIdx = c.getColumnIndex("button_count")

                    var deckNameIdx = c.getColumnIndex(COL_DECK_NAME)
                    if (deckNameIdx < 0) deckNameIdx = c.getColumnIndex("deck_name")

                    val noteId = try { if (noteIdIdx >= 0) c.getLong(noteIdIdx) else 0L } catch (_: Exception) { 0L }
                    val cardOrd = try { if (cardOrdIdx >= 0) c.getInt(cardOrdIdx) else 0 } catch (_: Exception) { 0 }
                    val cardId = try { if (cardIdIdx >= 0) c.getLong(cardIdIdx) else 0L } catch (_: Exception) { 0L }
                    val question = try { if (questionIdx >= 0) c.getString(questionIdx) ?: "" else "" } catch (_: Exception) { "" }
                    val answer = try { if (answerIdx >= 0) c.getString(answerIdx) ?: "" else "" } catch (_: Exception) { "" }
                    val buttonCount = try { if (buttonCountIdx >= 0) c.getInt(buttonCountIdx) else 4 } catch (_: Exception) { 4 }
                    val deckName = try { if (deckNameIdx >= 0) c.getString(deckNameIdx) else null } catch (_: Exception) { null }

                    if (question.isNotBlank() || answer.isNotBlank()) {
                        return AnkiCard(
                            noteId = noteId,
                            cardOrd = cardOrd,
                            cardId = cardId,
                            question = question,
                            answer = answer,
                            buttonCount = buttonCount,
                            deckName = deckName
                        )
                    }
                }
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Error querying $uri with selection $selection", e)
        }
        return null
    }

    /**
     * Answers a card in AnkiDroid.
     * @param card The card being answered
     * @param ease Ease value (1=Again, 2=Hard, 3=Good, 4=Easy)
     * @param timeTakenMs Time taken to answer in milliseconds
     * @return true if update succeeded
     */
    fun answerCard(card: AnkiCard, ease: Int, timeTakenMs: Long): Boolean {
        if (!hasAnkiPermission()) return false
        val authority = getAnkiAuthority()
        val reviewUri = Uri.parse("content://$authority/review_info")
        try {
            val values = ContentValues().apply {
                put(COL_EASE, ease)
                put(COL_TIME_TAKEN, timeTakenMs)
                put(COL_NOTE_ID, card.noteId)
                put(COL_CARD_ORD, card.cardOrd)
            }

            val updatedRows = context.contentResolver.update(
                reviewUri,
                values,
                null,
                null
            )
            return updatedRows > 0
        } catch (e: Throwable) {
            Log.e(TAG, "Error answering Anki card", e)
            return false
        }
    }
}
