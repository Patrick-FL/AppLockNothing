package dev.pranav.applock.features.applist.ui

import android.annotation.SuppressLint
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material.icons.rounded.Forum
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.net.toUri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.pranav.applock.R
import dev.pranav.applock.core.broadcast.DeviceAdmin
import dev.pranav.applock.core.navigation.Screen
import dev.pranav.applock.core.utils.appLockRepository
import dev.pranav.applock.core.utils.hasUsagePermission
import dev.pranav.applock.core.utils.isAccessibilityServiceEnabled
import dev.pranav.applock.core.utils.openAccessibilitySettings
import dev.pranav.applock.data.repository.BackendImplementation
import dev.pranav.applock.ui.components.BentoCard
import dev.pranav.applock.ui.components.BentoTextField
import dev.pranav.applock.ui.components.BentoToggle
import dev.pranav.applock.ui.components.CircleButton
import dev.pranav.applock.ui.components.DonateModalBottomSheet
import dev.pranav.applock.ui.components.DotMatrixText
import dev.pranav.applock.ui.components.PillButton
import dev.pranav.applock.ui.theme.NothingBlack
import dev.pranav.applock.ui.theme.NothingBorder
import dev.pranav.applock.ui.theme.NothingDarkGray
import dev.pranav.applock.ui.theme.NothingRed
import dev.pranav.applock.ui.theme.NothingSage
import dev.pranav.applock.ui.theme.NothingSurface
import dev.pranav.applock.ui.theme.NothingTextPrimary
import dev.pranav.applock.ui.theme.NothingTextSecondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import rikka.shizuku.Shizuku

@SuppressLint("LocalContextGetResourceValueCall")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    val isLoading by mainViewModel.isLoading.collectAsState()
    val lockedApps by mainViewModel.lockedAppsFlow.collectAsState()
    val unlockedApps by mainViewModel.unlockedAppsFlow.collectAsState()

    var showAddAppsSheet by remember { mutableStateOf(false) }
    var applockEnabled by remember { mutableStateOf(true) }

    val lifecycleOwner = LocalLifecycleOwner.current
    var firstMissingPermission by remember { mutableStateOf<MissingPermission?>(null) }

    LaunchedEffect(Unit) {
        val appLockRepository = context.appLockRepository()
        applockEnabled = appLockRepository.isProtectEnabled()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                val appLockRepository = context.appLockRepository()
                val backend = appLockRepository.getBackendImplementation()
                val isAntiUninstallEnabled = appLockRepository.isAntiUninstallEnabled()
                val dpm =
                    context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
                val component = ComponentName(context, DeviceAdmin::class.java)

                firstMissingPermission = when {
                    !Settings.canDrawOverlays(context) -> MissingPermission.OVERLAY
                    backend == BackendImplementation.ACCESSIBILITY && !context.isAccessibilityServiceEnabled() -> MissingPermission.ACCESSIBILITY
                    backend == BackendImplementation.USAGE_STATS && !context.hasUsagePermission() -> MissingPermission.USAGE_STATS
                    backend == BackendImplementation.SHIZUKU && (runCatching { !Shizuku.pingBinder() || Shizuku.checkSelfPermission() == PackageManager.PERMISSION_DENIED }.getOrDefault(
                        true
                    )) -> MissingPermission.SHIZUKU

                    isAntiUninstallEnabled && !context.isAccessibilityServiceEnabled() -> MissingPermission.ACCESSIBILITY
                    isAntiUninstallEnabled && !dpm.isAdminActive(component) -> MissingPermission.DEVICE_ADMIN
                    else -> null
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val appLockRepository = context.appLockRepository()
    var showCommunityLink by remember { mutableStateOf(appLockRepository.isShowCommunityLink()) }

    if (showCommunityLink) {
        CommunityDialog(
            onDismiss = {
                appLockRepository.setCommunityLinkShown(true)
                showCommunityLink = false
            },
            onJoin = {
                appLockRepository.setCommunityLinkShown(true)
                showCommunityLink = false
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        "https://discord.gg/46wCMRVAre".toUri()
                    )
                )
            }
        )
    }

    var showDonateDialog by remember { mutableStateOf(appLockRepository.isShowDonateLink()) }
    if (showDonateDialog && !showCommunityLink) {
        DonateModalBottomSheet {
            appLockRepository.setShowDonateLink(false); showDonateDialog = false
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = NothingBlack,
        floatingActionButton = {
            if (!isLoading) {
                FloatingActionButton(
                    onClick = { showAddAppsSheet = true },
                    containerColor = NothingTextPrimary,
                    contentColor = NothingBlack,
                    shape = CircleShape
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.main_screen_search_cd),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header Section - Nothing OS Bento Title & Quick Action Buttons
            BentoCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = NothingSurface
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            DotMatrixText(
                                text = "SYSTEM STATUS",
                                color = NothingTextSecondary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            DotMatrixText(
                                text = if (applockEnabled) "PROTECTED" else "UNPROTECTED",
                                color = if (applockEnabled) NothingSage else NothingRed,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        BentoToggle(
                            checked = applockEnabled,
                            onCheckedChange = { enabled ->
                                appLockRepository.setProtectEnabled(enabled)
                                applockEnabled = enabled
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Quick Action Circle Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleButton(
                            onClick = {
                                navController.navigate(Screen.Settings.route) {
                                    launchSingleTop = true
                                }
                            },
                            icon = Icons.Default.Settings,
                            size = 48.dp
                        )
                        CircleButton(
                            onClick = {
                                navController.navigate(Screen.TriggerExclusions.route) {
                                    launchSingleTop = true
                                }
                            },
                            icon = Icons.Default.Block,
                            size = 48.dp
                        )
                        CircleButton(
                            onClick = {
                                navController.navigate(Screen.AntiUninstall.route) {
                                    launchSingleTop = true
                                }
                            },
                            icon = Icons.Outlined.Lock,
                            size = 48.dp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Missing Permission Warning Banner
            firstMissingPermission?.let { missingPerm ->
                PermissionWarningBanner(
                    missingPermission = missingPerm,
                    onClick = {
                        when (missingPerm) {
                            MissingPermission.OVERLAY -> {
                                context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
                                    data = "package:${context.packageName}".toUri()
                                })
                            }

                            MissingPermission.ACCESSIBILITY -> {
                                openAccessibilitySettings(context)
                            }

                            MissingPermission.USAGE_STATS -> {
                                context.startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
                            }

                            MissingPermission.SHIZUKU -> {
                                try {
                                    if (Shizuku.isPreV11()) {
                                        Toast.makeText(
                                            context,
                                            context.getString(R.string.main_screen_shizuku_manual_permission_toast),
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        Shizuku.requestPermission(423)
                                    }
                                } catch (_: Exception) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.main_screen_shizuku_not_available_toast),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                            MissingPermission.DEVICE_ADMIN -> {
                                val component = ComponentName(context, DeviceAdmin::class.java)
                                val intent =
                                    Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
                                        putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component)
                                        putExtra(
                                            DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                            context.getString(R.string.main_screen_device_admin_explanation)
                                        )
                                    }
                                context.startActivity(intent)
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (isLoading) {
                LoadingContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {
                ProtectedAppsDashboard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    lockedApps = lockedApps,
                    onUnlockApp = { appInfo ->
                        mainViewModel.unlockApp(appInfo.packageName)
                    }
                )
            }
        }
    }

    if (showAddAppsSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val selectedPackages = remember { mutableStateListOf<String>() }
        var bottomSheetSearchQuery by remember { mutableStateOf("") }

        ModalBottomSheet(
            onDismissRequest = {
                bottomSheetSearchQuery = ""
                showAddAppsSheet = false
            },
            sheetState = sheetState,
            containerColor = NothingDarkGray,
            scrimColor = NothingBlack.copy(alpha = 0.8f)
        ) {
            AddProtectedAppsSheetContent(
                unlockedApps = unlockedApps,
                searchQuery = bottomSheetSearchQuery,
                onSearchQueryChanged = { bottomSheetSearchQuery = it },
                selectedPackages = selectedPackages,
                onToggleSelection = { packageName ->
                    if (selectedPackages.contains(packageName)) {
                        selectedPackages.remove(packageName)
                    } else {
                        selectedPackages.add(packageName)
                    }
                },
                onSave = {
                    mainViewModel.lockApps(selectedPackages)
                    bottomSheetSearchQuery = ""
                    showAddAppsSheet = false
                }
            )
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(44.dp),
                color = NothingRed,
                strokeWidth = 3.dp
            )
            Spacer(modifier = Modifier.height(20.dp))
            DotMatrixText(
                text = stringResource(R.string.main_screen_loading_applications_text),
                color = NothingTextSecondary,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ProtectedAppsDashboard(
    modifier: Modifier = Modifier,
    lockedApps: List<ApplicationInfo>,
    onUnlockApp: (ApplicationInfo) -> Unit
) {
    if (lockedApps.isEmpty()) {
        EmptyDashboardState(modifier = modifier)
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(bottom = 88.dp, top = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DotMatrixText(
                        text = "LOCKED APPS",
                        color = NothingTextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    DotMatrixText(
                        text = "${lockedApps.size}",
                        color = NothingRed,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items(lockedApps, key = { it.packageName }) { appInfo ->
                ProtectedAppItem(
                    appInfo = appInfo,
                    onUnlock = { onUnlockApp(appInfo) }
                )
            }
        }
    }
}

@Composable
private fun EmptyDashboardState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BentoCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            backgroundColor = NothingSurface
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Shield,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = NothingRed
                )
                Spacer(modifier = Modifier.height(16.dp))
                DotMatrixText(
                    text = "NO PROTECTED APPS",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tap the + button to select apps for protection.",
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = dev.pranav.applock.ui.theme.DotMatrixFontFamily,
                        fontSize = 12.sp,
                        color = NothingTextSecondary
                    ),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun AddProtectedAppsSheetContent(
    unlockedApps: List<ApplicationInfo>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    selectedPackages: List<String>,
    onToggleSelection: (String) -> Unit,
    onSave: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val filteredApps by produceState(
        initialValue = unlockedApps,
        unlockedApps,
        searchQuery
    ) {
        value = if (searchQuery.isBlank()) {
            unlockedApps
        } else {
            withContext(Dispatchers.Default) {
                val lowerQuery = searchQuery.lowercase()
                unlockedApps.filter { appInfo ->
                    val label = AppIconCache.getLabel(context, appInfo).lowercase()
                    label.contains(lowerQuery) || appInfo.packageName.contains(
                        lowerQuery,
                        ignoreCase = true
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotMatrixText(
                text = "SELECT APPS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            PillButton(
                text = "PROTECT (${selectedPackages.size})",
                onClick = onSave,
                enabled = selectedPackages.isNotEmpty(),
                containerColor = NothingRed,
                contentColor = NothingTextPrimary,
                modifier = Modifier.height(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Bar
        BentoTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            placeholder = "SEARCH APPLICATIONS",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.main_screen_search_cd),
                    tint = NothingTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // App List
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(filteredApps, key = { it.packageName }) { appInfo ->
                val isSelected = selectedPackages.contains(appInfo.packageName)
                SelectableAppItem(
                    appInfo = appInfo,
                    isSelected = isSelected,
                    onClick = { onToggleSelection(appInfo.packageName) }
                )
            }
        }
    }
}

@Composable
private fun ProtectedAppItem(
    appInfo: ApplicationInfo,
    onUnlock: () -> Unit
) {
    val context = LocalContext.current

    var appName by remember(appInfo) { mutableStateOf<String?>(null) }
    var icon by remember(appInfo) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(appInfo) {
        withContext(Dispatchers.IO) {
            appName = AppIconCache.getLabel(context, appInfo)
            icon = AppIconCache.getIcon(context, appInfo)
        }
    }

    BentoCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = NothingSurface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(NothingDarkGray)
                        .border(1.dp, NothingBorder, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (icon != null) {
                        Image(
                            bitmap = icon!!,
                            contentDescription = appName,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    appName?.let { name ->
                        Text(
                            text = name,
                            style = androidx.compose.ui.text.TextStyle(
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = NothingTextPrimary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    DotMatrixText(
                        text = "LOCKED",
                        color = NothingSage,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            IconButton(
                onClick = onUnlock,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(NothingDarkGray)
                    .border(1.dp, NothingBorder, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LockOpen,
                    contentDescription = "Unlock ${appName ?: "app"}",
                    tint = NothingTextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun SelectableAppItem(
    appInfo: ApplicationInfo,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    var appName by remember(appInfo) { mutableStateOf<String?>(null) }
    var icon by remember(appInfo) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(appInfo) {
        withContext(Dispatchers.IO) {
            appName = AppIconCache.getLabel(context, appInfo)
            icon = AppIconCache.getIcon(context, appInfo)
        }
    }

    BentoCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        backgroundColor = if (isSelected) NothingSurface else NothingDarkGray,
        borderColor = if (isSelected) NothingRed else NothingBorder
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(NothingBlack),
                    contentAlignment = Alignment.Center
                ) {
                    if (icon != null) {
                        Image(
                            bitmap = icon!!,
                            contentDescription = appName,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    appName?.let { name ->
                        Text(
                            text = name,
                            style = androidx.compose.ui.text.TextStyle(
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = NothingTextPrimary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    DotMatrixText(
                        text = appInfo.packageName,
                        color = NothingTextSecondary,
                        fontSize = 9.sp
                    )
                }
            }

            Checkbox(
                checked = isSelected,
                onCheckedChange = null,
                colors = androidx.compose.material3.CheckboxDefaults.colors(
                    checkedColor = NothingRed,
                    uncheckedColor = NothingBorder,
                    checkmarkColor = NothingTextPrimary
                )
            )
        }
    }
}

@Composable
private fun CommunityDialog(
    onDismiss: () -> Unit,
    onJoin: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        containerColor = NothingSurface,
        icon = {
            Icon(
                Icons.Rounded.Groups,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = NothingRed
            )
        },
        title = {
            DotMatrixText(
                text = stringResource(R.string.join_community),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(Modifier.fillMaxWidth(0.85f)) {
                Text(
                    text = stringResource(R.string.join_community_desc),
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                        fontSize = 13.sp,
                        color = NothingTextSecondary
                    )
                )
            }
        },
        confirmButton = {
            PillButton(
                text = stringResource(R.string.join_discord),
                onClick = onJoin,
                containerColor = NothingRed,
                contentColor = NothingTextPrimary,
                modifier = Modifier.height(40.dp)
            )
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                DotMatrixText(
                    text = stringResource(R.string.maybe_later),
                    color = NothingTextSecondary,
                    fontSize = 12.sp
                )
            }
        }
    )
}

private enum class MissingPermission(val titleResId: Int, val descriptionResId: Int) {
    OVERLAY(
        titleResId = R.string.permission_warning_overlay_title,
        descriptionResId = R.string.permission_warning_overlay_desc
    ),
    ACCESSIBILITY(
        titleResId = R.string.permission_warning_accessibility_title,
        descriptionResId = R.string.permission_warning_accessibility_desc
    ),
    USAGE_STATS(
        titleResId = R.string.permission_warning_usage_stats_title,
        descriptionResId = R.string.permission_warning_usage_stats_desc
    ),
    SHIZUKU(
        titleResId = R.string.permission_warning_shizuku_title,
        descriptionResId = R.string.permission_warning_shizuku_desc
    ),
    DEVICE_ADMIN(
        titleResId = R.string.permission_warning_device_admin_title,
        descriptionResId = R.string.permission_warning_device_admin_desc
    )
}

@Composable
private fun PermissionWarningBanner(
    missingPermission: MissingPermission,
    onClick: () -> Unit
) {
    BentoCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        backgroundColor = NothingSurface,
        borderColor = NothingRed,
        badge = "!"
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.Warning,
                contentDescription = "Warning",
                tint = NothingRed,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                DotMatrixText(
                    text = "${stringResource(missingPermission.titleResId)} ${stringResource(R.string.permission_warning_title_suffix)}",
                    color = NothingTextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(missingPermission.descriptionResId),
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
                        fontSize = 12.sp,
                        color = NothingTextSecondary
                    )
                )
            }
        }
    }
}
