package dev.pranav.applock.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dev.pranav.applock.core.utils.SecurityUtils

/**
 * Repository for managing application preferences and settings.
 * Handles all SharedPreferences operations with proper separation of concerns.
 */
class PreferencesRepository(context: Context) {

    private val appLockPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME_APP_LOCK, Context.MODE_PRIVATE)

    private val settingsPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME_SETTINGS, Context.MODE_PRIVATE)

    fun setPassword(password: String) {
        val salt = SecurityUtils.generateSalt()
        val saltedHash = SecurityUtils.hashPassword(password, salt)
        appLockPrefs.edit(commit = true) { putString(KEY_PASSWORD, saltedHash) }
    }

    fun getPassword(): String? {
        return appLockPrefs.getString(KEY_PASSWORD, null)
    }

    fun validatePassword(input: String): Boolean {
        val stored = getPassword()
        if (stored.isNullOrBlank()) return false

        val sanitizedInput = SecurityUtils.sanitizePassword(input)

        if (SecurityUtils.isSaltedHash(stored)) {
            return SecurityUtils.verifyPassword(sanitizedInput, stored)
        }

        if (stored == input || stored == sanitizedInput) {
            setPassword(sanitizedInput)
            return true
        }

        return false
    }

    fun setPattern(pattern: String) {
        appLockPrefs.edit(commit = true) { putString(KEY_PATTERN, pattern) }
    }

    fun getPattern(): String? {
        return appLockPrefs.getString(KEY_PATTERN, null)
    }

    fun validatePattern(inputPattern: String): Boolean {
        val storedPattern = getPattern()
        return storedPattern != null && inputPattern == storedPattern
    }

    fun setLockType(lockType: String) {
        settingsPrefs.edit(commit = true) { putString(KEY_LOCK_TYPE, lockType) }
    }

    fun getLockType(): String {
        return settingsPrefs.getString(KEY_LOCK_TYPE, LOCK_TYPE_PIN) ?: LOCK_TYPE_PIN
    }

    fun setAnkiCardsPerLock(count: Int) {
        settingsPrefs.edit { putInt(KEY_ANKI_CARDS_PER_LOCK, count.coerceAtLeast(1)) }
    }

    fun getAnkiCardsPerLock(): Int {
        return settingsPrefs.getInt(KEY_ANKI_CARDS_PER_LOCK, DEFAULT_ANKI_CARDS_PER_LOCK)
    }

    fun setAnkiDeck(deckId: Long, deckName: String) {
        settingsPrefs.edit {
            putLong(KEY_ANKI_DECK_ID, deckId)
            putString(KEY_ANKI_DECK_NAME, deckName)
        }
    }

    fun getAnkiDeckId(): Long {
        return settingsPrefs.getLong(KEY_ANKI_DECK_ID, 0L)
    }

    fun getAnkiDeckName(): String {
        return settingsPrefs.getString(KEY_ANKI_DECK_NAME, "Alle Stapel") ?: "Alle Stapel"
    }

    fun getEarnApps(): Set<String> {
        val defaultApps = setOf("com.ichi2.anki", "com.ichi2.anki.debug")
        return settingsPrefs.getStringSet(KEY_EARN_APPS, defaultApps) ?: defaultApps
    }

    fun setEarnApps(apps: Set<String>) {
        settingsPrefs.edit { putStringSet(KEY_EARN_APPS, apps) }
    }

    fun addEarnApp(packageName: String) {
        val current = getEarnApps().toMutableSet()
        current.add(packageName)
        setEarnApps(current)
    }

    fun removeEarnApp(packageName: String) {
        val current = getEarnApps().toMutableSet()
        current.remove(packageName)
        setEarnApps(current)
    }

    fun isEarnApp(packageName: String): Boolean {
        return getEarnApps().contains(packageName)
    }

    fun getCreditBalanceSeconds(): Float {
        checkAndApplyCreditReset()
        return settingsPrefs.getFloat(KEY_CREDIT_BALANCE_SECONDS, 0.0f)
    }

    fun setCreditBalanceSeconds(seconds: Float) {
        settingsPrefs.edit { putFloat(KEY_CREDIT_BALANCE_SECONDS, seconds.coerceAtLeast(0.0f)) }
    }

    fun addCreditSeconds(seconds: Float) {
        val newBalance = (getCreditBalanceSeconds() + seconds).coerceAtLeast(0.0f)
        setCreditBalanceSeconds(newBalance)
    }

    fun consumeCreditSeconds(seconds: Float): Boolean {
        val current = getCreditBalanceSeconds()
        if (current <= 0f) return false
        val newBalance = (current - seconds).coerceAtLeast(0.0f)
        setCreditBalanceSeconds(newBalance)
        return true
    }

    fun getCreditRatio(): Float {
        return settingsPrefs.getFloat(KEY_CREDIT_RATIO, 1.0f)
    }

    fun setCreditRatio(ratio: Float) {
        settingsPrefs.edit { putFloat(KEY_CREDIT_RATIO, ratio) }
    }

    fun getCreditResetInterval(): String {
        return settingsPrefs.getString(KEY_CREDIT_RESET_INTERVAL, "daily") ?: "daily"
    }

    fun setCreditResetInterval(interval: String) {
        settingsPrefs.edit { putString(KEY_CREDIT_RESET_INTERVAL, interval) }
    }

    fun checkAndApplyCreditReset() {
        val interval = settingsPrefs.getString(KEY_CREDIT_RESET_INTERVAL, "daily") ?: "daily"
        if (interval == "never") return

        val lastReset = settingsPrefs.getLong(KEY_LAST_CREDIT_RESET_TIMESTAMP, 0L)
        val now = System.currentTimeMillis()

        val calNow = java.util.Calendar.getInstance().apply { timeInMillis = now }
        val calLast = java.util.Calendar.getInstance().apply { timeInMillis = lastReset }

        val shouldReset = when (interval) {
            "daily" -> {
                calNow.get(java.util.Calendar.DAY_OF_YEAR) != calLast.get(java.util.Calendar.DAY_OF_YEAR) ||
                        calNow.get(java.util.Calendar.YEAR) != calLast.get(java.util.Calendar.YEAR)
            }
            "weekly" -> {
                calNow.get(java.util.Calendar.WEEK_OF_YEAR) != calLast.get(java.util.Calendar.WEEK_OF_YEAR) ||
                        calNow.get(java.util.Calendar.YEAR) != calLast.get(java.util.Calendar.YEAR)
            }
            else -> false
        }

        if (shouldReset) {
            settingsPrefs.edit {
                putFloat(KEY_CREDIT_BALANCE_SECONDS, 0.0f)
                putLong(KEY_LAST_CREDIT_RESET_TIMESTAMP, now)
            }
        }
    }

    fun setBiometricAuthEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_BIOMETRIC_AUTH_ENABLED, enabled) }
    }

    fun isBiometricAuthEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_BIOMETRIC_AUTH_ENABLED, false)
    }

    fun setUseMaxBrightness(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_USE_MAX_BRIGHTNESS, enabled) }
    }

    fun shouldUseMaxBrightness(): Boolean {
        return settingsPrefs.getBoolean(KEY_USE_MAX_BRIGHTNESS, false)
    }

    fun setDisableHaptics(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_DISABLE_HAPTICS, enabled) }
    }

    fun shouldDisableHaptics(): Boolean {
        return settingsPrefs.getBoolean(KEY_DISABLE_HAPTICS, false)
    }

    fun setShowSystemApps(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_SHOW_SYSTEM_APPS, enabled) }
    }

    fun shouldShowSystemApps(): Boolean {
        return settingsPrefs.getBoolean(KEY_SHOW_SYSTEM_APPS, false)
    }

    fun setAntiUninstallEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_ANTI_UNINSTALL, enabled) }
    }

    fun isAntiUninstallEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_ANTI_UNINSTALL, false)
    }

    fun setProtectEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_APPLOCK_ENABLED, enabled) }
    }

    fun isProtectEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_APPLOCK_ENABLED, DEFAULT_PROTECT_ENABLED)
    }

    fun setUnlockTimeDuration(minutes: Int) {
        settingsPrefs.edit { putInt(KEY_UNLOCK_TIME_DURATION, minutes) }
    }

    fun getUnlockTimeDuration(): Int {
        return settingsPrefs.getInt(KEY_UNLOCK_TIME_DURATION, DEFAULT_UNLOCK_DURATION)
    }

    fun setAutoUnlockEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_AUTO_UNLOCK, enabled) }
    }

    fun isAutoUnlockEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_AUTO_UNLOCK, false)
    }

    fun setBackendImplementation(backend: BackendImplementation) {
        settingsPrefs.edit { putString(KEY_BACKEND_IMPLEMENTATION, backend.name) }
    }

    fun getBackendImplementation(): BackendImplementation {
        val backend = settingsPrefs.getString(
            KEY_BACKEND_IMPLEMENTATION,
            BackendImplementation.ACCESSIBILITY.name
        )
        return try {
            BackendImplementation.valueOf(backend ?: BackendImplementation.ACCESSIBILITY.name)
        } catch (_: IllegalArgumentException) {
            BackendImplementation.ACCESSIBILITY
        }
    }

    fun isShowCommunityLink(): Boolean {
        return !settingsPrefs.getBoolean(KEY_COMMUNITY_LINK_SHOWN, false)
    }

    fun setCommunityLinkShown(shown: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_COMMUNITY_LINK_SHOWN, shown) }
    }

    fun isShowDonateLink(context: Context): Boolean {
        return settingsPrefs.getBoolean(KEY_SHOW_DONATE_LINK, false)
    }

    fun setShowDonateLink(context: Context, show: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_SHOW_DONATE_LINK, show) }
    }

    fun isLoggingEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_LOGGING_ENABLED, false)
    }

    fun setLoggingEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_LOGGING_ENABLED, enabled) }
    }

    fun isAppLockSelfLockEnabled(): Boolean {
        return settingsPrefs.getBoolean(KEY_APPLOCK_SELF_LOCK_ENABLED, false)
    }

    fun setAppLockSelfLockEnabled(enabled: Boolean) {
        settingsPrefs.edit { putBoolean(KEY_APPLOCK_SELF_LOCK_ENABLED, enabled) }
    }

    companion object {
        private const val PREFS_NAME_APP_LOCK = "app_lock_prefs"
        private const val PREFS_NAME_SETTINGS = "app_lock_settings"

        private const val KEY_PASSWORD = "password"
        private const val KEY_PATTERN = "pattern"
        private const val KEY_BIOMETRIC_AUTH_ENABLED = "use_biometric_auth"
        private const val KEY_DISABLE_HAPTICS = "disable_haptics"
        private const val KEY_USE_MAX_BRIGHTNESS = "use_max_brightness"
        private const val KEY_ANTI_UNINSTALL = "anti_uninstall"
        private const val KEY_UNLOCK_TIME_DURATION = "unlock_time_duration"
        private const val KEY_BACKEND_IMPLEMENTATION = "backend_implementation"
        private const val KEY_COMMUNITY_LINK_SHOWN = "community_link_shown"
        private const val KEY_SHOW_DONATE_LINK = "show_donate_link"
        private const val KEY_LOGGING_ENABLED = "logging_enabled"
        private const val KEY_APPLOCK_SELF_LOCK_ENABLED = "applock_self_lock_enabled"
        private const val LAST_VERSION_CODE = "last_version_code"
        private const val KEY_APPLOCK_ENABLED = "applock_enabled"
        private const val KEY_AUTO_UNLOCK = "auto_unlock"
        private const val KEY_SHOW_SYSTEM_APPS = "show_system_apps"
        private const val KEY_LOCK_TYPE = "lock_type"
        private const val KEY_ANKI_CARDS_PER_LOCK = "anki_cards_per_lock"
        private const val KEY_ANKI_DECK_ID = "anki_deck_id"
        private const val KEY_ANKI_DECK_NAME = "anki_deck_name"
        private const val KEY_EARN_APPS = "earn_apps"
        private const val KEY_CREDIT_BALANCE_SECONDS = "credit_balance_seconds"
        private const val KEY_CREDIT_RATIO = "credit_ratio"
        private const val KEY_CREDIT_RESET_INTERVAL = "credit_reset_interval"
        private const val KEY_LAST_CREDIT_RESET_TIMESTAMP = "last_credit_reset_timestamp"

        private const val DEFAULT_PROTECT_ENABLED = true
        private const val DEFAULT_UNLOCK_DURATION = 0
        private const val DEFAULT_ANKI_CARDS_PER_LOCK = 1

        const val LOCK_TYPE_PIN = "pin"
        const val LOCK_TYPE_PATTERN = "pattern"
        const val LOCK_TYPE_PASSWORD = "password"
        const val LOCK_TYPE_ANKI = "anki"
    }
}
