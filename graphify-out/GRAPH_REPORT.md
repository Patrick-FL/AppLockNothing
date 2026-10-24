# Graph Report - AppLock  (2026-10-24)

## Corpus Check
- 118 files · ~111,549 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1732 nodes · 3043 edges · 108 communities (66 shown, 42 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 37 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `52e028af`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- IPackageManager
- IActivityManager
- ActivityManager
- SettingsScreen.kt
- ITaskStackListener
- AntiUninstallScreen.kt
- Dot
- AppLockRepository
- android.content.Intent
- AppLockAccessibilityService
- TaskStackListener
- android.os.IBinder
- PreferencesRepository
- UsageLockService
- android.content.ComponentName
- WindowConfiguration
- TaskInfo
- ActivityTaskManager
- TaskInfo
- ParceledListSlice
- IUidObserver
- android.graphics.Rect
- LockedAppsRepository
- PasswordOverlayActivity
- ShizukuAppLockService
- IActivityManager.java
- AppIntroScreen.kt
- AnkiRepository
- TaskDescription
- ShizukuActivityManager.kt
- AppNavigator.kt
- LockScreenOverlayManager.kt
- AppLockManager
- RunningAppProcessInfo
- IProcessObserver
- IActivityTaskManager
- LogUtils
- AdminDisableActivity.kt
- PasswordOverlayScreen.kt
- MainViewModel
- ComponentName
- WindowConfiguration.java
- IIntentSender
- NavigationManager
- IActivityTaskManage
- Dp
- android.net.Uri
- IPackageManager.java
- AlphanumericPasswordOverlayScreen
- RunningTaskInfo
- TransparentBiometricActivity.kt
- androidx.annotation.NonNull
- Screen
- AppLockApplication
- BootReceiver.kt
- IPackageDataObserver
- AppTask
- IActivityController
- vibrate
- AppIntroManager
- OnUidImportanceListener
- IProgressListener
- README.md
- AppLockServiceStarter.kt
- AppIconCache.kt
- PageIndicator.kt
- WindowManagerImpl
- ResolveInfo
- TaskSnapshot
- PreferencesRepository.kt
- ApplicationStartInfo
- MemoryInfo
- UserHandle
- ProviderInfo
- gradlew
- HomeVisibilityListener
- IApplicationStartInfoCompleteListener
- .getPackageInfoVersioned
- ExampleInstrumentedTest
- ExampleUnitTest
- Stub
- .addPermission
- .getInstalledModules
- .getChangedPackages
- .getHistoricalProcessExitReasons
- .setDeviceLocales
- .setProcessStateSummary
- ProcessErrorStateInfo
- .getApplicationInfo
- .getInstallSourceInfo
- .getPermissionGroupInfo
- .getPropertyAsUser
- DisplayInfo.java
- Line.kt
- TaskInfo

## God Nodes (most connected - your core abstractions)
1. `IActivityManager` - 247 edges
2. `IPackageManager` - 205 edges
3. `ActivityManager` - 130 edges
4. `AppLockRepository` - 91 edges
5. `PreferencesRepository` - 67 edges
6. `WindowConfiguration` - 54 edges
7. `AppLockAccessibilityService` - 38 edges
8. `TaskStackListener` - 35 edges
9. `ITaskStackListener` - 34 edges
10. `TaskInfo` - 33 edges

## Surprising Connections (you probably didn't know these)
- `PatternSetPasswordScreen()` --calls--> `PatternLock()`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/features/setpassword/ui/PatternSetPasswordScreen.kt → patternlock/src/main/java/com/mrhwsn/composelock/PatternLock.kt
- `ShizukuActivityManager` --references--> `IWindowManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/view/IWindowManager.java
- `getTasksWrapper()` --references--> `ActivityManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/app/ActivityManager.kt
- `filterVisible()` --references--> `ActivityManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/app/ActivityManager.kt
- `AppLockRepository` --calls--> `LockedAppsRepository`  [INFERRED]
  app/src/main/java/dev/pranav/applock/data/repository/AppLockRepository.kt → app/src/main/java/dev/pranav/applock/data/repository/LockedAppsRepository.kt

## Import Cycles
- None detected.

## Communities (108 total, 42 thin omitted)

### Community 2 - "ActivityManager"
Cohesion: 0.03
Nodes (11): ActivityManager, ForegroundServiceApiEvent, ForegroundServiceApiType, MoveTaskFlags, PendingIntentInfo, ProcessCapability, ProcessState, RestrictionLevel (+3 more)

### Community 3 - "SettingsScreen.kt"
Cohesion: 0.06
Nodes (63): enableAccessibilityServiceWithShizuku(), exec(), isAccessibilityServiceEnabled(), ComponentName, Context, openAccessibilitySettings(), BackendServiceManager, BackendImplementation (+55 more)

### Community 5 - "AntiUninstallScreen.kt"
Cohesion: 0.07
Nodes (31): appLockRepository(), blockUninstallForUser(), unblockUninstallForUser(), AntiUninstallScreen(), AntiUninstallViewModel, AppProtectionItem(), checkShizukuState(), isShizukuInstalled() (+23 more)

### Community 6 - "Dot"
Cohesion: 0.10
Nodes (18): LockCallback, Modifier, PatternLockScreen(), LockCallback, LockCallback, LockCallback, LockCallback, pattern (+10 more)

### Community 8 - "android.content.Intent"
Cohesion: 0.09
Nodes (5): android.content.Intent, android.os.Bundle, IApplicationThread, IIntentReceiver, ProfilerInfo

### Community 9 - "AppLockAccessibilityService"
Cohesion: 0.12
Nodes (9): AccessibilityEvent, AccessibilityNodeInfo, AccessibilityService, AppLockAccessibilityService, BiometricState, AUTH_STARTED, IDLE, Intent (+1 more)

### Community 10 - "TaskStackListener"
Cohesion: 0.11
Nodes (7): android.app.ActivityManager.RunningTaskInfo, Deprecated, dev.rikka.tools.refine.RefineAs, Override, TaskStackListener, LayoutParams, WindowManagerHidden

### Community 11 - "android.os.IBinder"
Cohesion: 0.05
Nodes (11): android.os.Binder, android.os.IBinder, android.os.IInterface, ApplicationErrorReport, Notification, Stub, IForegroundServiceObserver, ContentProviderProxy (+3 more)

### Community 13 - "UsageLockService"
Cohesion: 0.20
Nodes (8): IBinder, Intent, Notification, NotificationManager, Service, UsageLockService, Timer, UsageStatsManager

### Community 14 - "android.content.ComponentName"
Cohesion: 0.06
Nodes (6): android.content.ComponentName, android.content.IntentFilter, PendingIntent, ActivityInfo, InstrumentationInfo, ServiceInfo

### Community 15 - "WindowConfiguration"
Cohesion: 0.07
Nodes (4): Creator, Override, WindowConfig, WindowConfiguration

### Community 16 - "TaskInfo"
Cohesion: 0.08
Nodes (9): android.content.LocusId, android.content.pm.ActivityInfo, android.graphics.Point, android.os.Parcel, Override, PictureInPictureParams, TaskInfo, WindowConfiguration.ActivityType (+1 more)

### Community 17 - "ActivityTaskManager"
Cohesion: 0.08
Nodes (4): android.content.Context, ActivityTaskManager, RemoteAnimationDefinition, SplashScreenViewParcelable

### Community 18 - "TaskInfo"
Cohesion: 0.12
Nodes (11): ConfigurationInfo, FileDescriptor, ActivityInfo, Configuration, IBinder, LocusId, PictureInPictureParams, Point (+3 more)

### Community 19 - "ParceledListSlice"
Cohesion: 0.09
Nodes (3): ApplicationExitInfo, ApplicationStartInfo, ParceledListSlice

### Community 20 - "IUidObserver"
Cohesion: 0.10
Nodes (3): androidx.annotation.RequiresPermission, IUidFrozenStateChangedCallback, IUidObserver

### Community 23 - "PasswordOverlayActivity"
Cohesion: 0.15
Nodes (5): BiometricPrompt, Bundle, Configuration, FragmentActivity, PasswordOverlayActivity

### Community 24 - "ShizukuAppLockService"
Cohesion: 0.18
Nodes (6): IBinder, Intent, Notification, NotificationManager, Service, ShizukuAppLockService

### Community 25 - "IActivityManager.java"
Cohesion: 0.20
Nodes (3): android.content.pm.ApplicationInfo, android.os.Debug, android.os.ParcelFileDescriptor

### Community 26 - "AppIntroScreen.kt"
Cohesion: 0.13
Nodes (21): hasUsagePermission(), AppIntroScreen(), AppUsageMethod, ACCESSIBILITY, SHIZUKU, USAGE_STATS, ImageVector, NavController (+13 more)

### Community 27 - "AnkiRepository"
Cohesion: 0.16
Nodes (12): AnkiCard, AnkiDeck, AnkiRepository, AnkiStatus, Error, Uri, NotInstalled, PermissionDenied (+4 more)

### Community 28 - "TaskDescription"
Cohesion: 0.12
Nodes (4): Activity, Builder, Bitmap, TaskDescription

### Community 29 - "ShizukuActivityManager.kt"
Cohesion: 0.17
Nodes (7): DeviceUnlockReceiver, BroadcastReceiver, Context, Intent, filterVisible(), getTasksWrapper(), ShizukuActivityManager

### Community 30 - "AppNavigator.kt"
Cohesion: 0.33
Nodes (9): AppNavHost(), createBiometricPromptInfo(), handleAuthenticationSuccess(), handleBiometricAuthentication(), BiometricPrompt, BiometricPrompt, FragmentActivity, navigateToMain() (+1 more)

### Community 31 - "LockScreenOverlayManager.kt"
Cohesion: 0.29
Nodes (10): LockScreenOverlayManager, AppLockTheme(), ComposeView, Lifecycle, LifecycleOwner, OnBackPressedDispatcherOwner, SavedStateRegistry, SavedStateRegistryOwner (+2 more)

### Community 32 - "AppLockManager"
Cohesion: 0.13
Nodes (6): AppLockConstants, AppLockManager, isDeviceLocked(), isServiceRunning(), Context, dev

### Community 33 - "RunningAppProcessInfo"
Cohesion: 0.13
Nodes (5): Importance, Bundle, Context, Intent, RunningAppProcessInfo

### Community 34 - "IProcessObserver"
Cohesion: 0.13
Nodes (3): IProcessObserver, Binder, Stub

### Community 35 - "IActivityTaskManager"
Cohesion: 0.38
Nodes (3): android.annotation.TargetApi, IActivityTaskManager, Stub

### Community 36 - "LogUtils"
Cohesion: 0.26
Nodes (4): Context, CoroutineScope, Uri, LogUtils

### Community 37 - "AdminDisableActivity.kt"
Cohesion: 0.13
Nodes (16): android, DeviceAdmin, Context, SharedPreferences, ByteArray, SecurityUtils, AdminDisableActivity, OnBackPressedCallback (+8 more)

### Community 38 - "PasswordOverlayScreen.kt"
Cohesion: 0.30
Nodes (13): addDigitToPassword(), handleKeypadSpecialButtonLogic(), KeypadRow(), KeypadSection(), Context, ImageVector, Modifier, PasswordIndicators() (+5 more)

### Community 39 - "MainViewModel"
Cohesion: 0.20
Nodes (6): AndroidViewModel, AppSearchManager, ApplicationInfo, ApplicationInfo, StateFlow, MainViewModel

### Community 40 - "ComponentName"
Cohesion: 0.16
Nodes (5): ConnectionInfo, ComponentName, PendingIntent, WorkSource, RunningServiceInfo

### Community 41 - "WindowConfiguration.java"
Cohesion: 0.15
Nodes (9): android.os.Parcelable, android.util.proto.ProtoOutputStream, androidx.annotation.IntDef, androidx.annotation.Nullable, SelfMovable, ActivityType, AlwaysOnTop, WindowingMode (+1 more)

### Community 43 - "NavigationManager"
Cohesion: 0.23
Nodes (5): NavigationManager, androidx, Bundle, FragmentActivity, MainActivity

### Community 44 - "IActivityTaskManage"
Cohesion: 0.23
Nodes (5): IActivityTaskManage, Binder, IBinder, IInterface, Stub

### Community 46 - "android.net.Uri"
Cohesion: 0.10
Nodes (11): android.content.res.AssetFileDescriptor, android.database.Cursor, android.net.Uri, androidx.annotation.RequiresApi, AttributionSource, ContentProviderHolder, ContentProviderNative, Override (+3 more)

### Community 47 - "IPackageManager.java"
Cohesion: 0.20
Nodes (4): android.content.IntentSender, android.content.pm.PackageManager.ComponentEnabledSetting, android.graphics.Bitmap, android.os.UserHandle

### Community 48 - "AlphanumericPasswordOverlayScreen"
Cohesion: 0.53
Nodes (5): AlphanumericPasswordOverlayScreen(), dev, Modifier, performVerification(), password

### Community 50 - "TransparentBiometricActivity.kt"
Cohesion: 0.31
Nodes (5): BiometricPrompt, Bundle, FragmentActivity, TransparentBiometricActivity, BiometricPrompt

### Community 52 - "Screen"
Cohesion: 0.12
Nodes (17): finishPasswordSetup(), AntiUninstall, AppIntro, ChangePassword, Main, PasswordOverlay, Screen, SetPassword (+9 more)

### Community 53 - "AppLockApplication"
Cohesion: 0.36
Nodes (3): AppLockApplication, Context, Application

### Community 54 - "BootReceiver.kt"
Cohesion: 0.44
Nodes (5): BootReceiver, BroadcastReceiver, Context, dev, Intent

### Community 56 - "AppTask"
Cohesion: 0.22
Nodes (3): AppTask, RecentTaskInfo, WindowingLayer

### Community 58 - "vibrate"
Cohesion: 0.51
Nodes (9): createBatteryOptimizationIntent(), getVibrator(), Context, Intent, launchBatterySettings(), launchGeneralBatterySettings(), showBatteryOptimizationToast(), vibrate() (+1 more)

### Community 60 - "OnUidImportanceListener"
Cohesion: 0.25
Nodes (3): Debug, OnUidImportanceListener, IntArray

### Community 62 - "README.md"
Cohesion: 0.29
Nodes (6): Download, Features, License, Overview, Support Development, Use Cases

### Community 63 - "AppLockServiceStarter.kt"
Cohesion: 0.53
Nodes (3): AppLockServiceStarter, Context, dev

### Community 64 - "AppIconCache.kt"
Cohesion: 0.43
Nodes (4): AppIconCache, ApplicationInfo, Context, ImageBitmap

### Community 65 - "PageIndicator.kt"
Cohesion: 0.90
Nodes (4): Color, Modifier, PageIndicator(), PageIndicators()

### Community 68 - "TaskSnapshot"
Cohesion: 0.47
Nodes (3): Parcel, Parcelable, TaskSnapshot

### Community 74 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **54 isolated node(s):** `AppIntro`, `SetPassword`, `SetPasswordPattern`, `SetPasswordAlphanumeric`, `ChangePassword` (+49 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **42 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IActivityManager` connect `IActivityManager` to `ITaskStackListener`, `android.content.Intent`, `android.os.IBinder`, `android.content.ComponentName`, `TaskInfo`, `ParceledListSlice`, `IUidObserver`, `android.graphics.Rect`, `IActivityManager.java`, `RunningAppProcessInfo`, `IProcessObserver`, `ComponentName`, `WindowConfiguration.java`, `IIntentSender`, `android.net.Uri`, `RunningTaskInfo`, `IPackageDataObserver`, `IActivityController`, `IProgressListener`, `MemoryInfo`, `IApplicationStartInfoCompleteListener`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.220) - this node is a cross-community bridge._
- **Why does `ActivityManager` connect `ActivityManager` to `TaskInfo`, `AppIntroScreen.kt`, `TaskDescription`, `ShizukuActivityManager.kt`, `AppLockManager`, `RunningAppProcessInfo`, `ComponentName`, `RunningTaskInfo`, `IPackageDataObserver`, `AppTask`, `OnUidImportanceListener`, `ApplicationStartInfo`, `MemoryInfo`, `UserHandle`, `HomeVisibilityListener`, `.getHistoricalProcessExitReasons`, `.setDeviceLocales`, `.setProcessStateSummary`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.207) - this node is a cross-community bridge._
- **Why does `AppLockRepository` connect `AppLockRepository` to `SettingsScreen.kt`, `AdminDisableActivity.kt`, `PasswordOverlayScreen.kt`, `MainViewModel`, `AppLockAccessibilityService`, `PreferencesRepository`, `UsageLockService`, `.getBackendImplementation`, `AppLockApplication`, `LockedAppsRepository`, `PasswordOverlayActivity`, `ShizukuAppLockService`, `vibrate`, `ShizukuActivityManager.kt`?**
  _High betweenness centrality (0.201) - this node is a cross-community bridge._
- **Are the 2 inferred relationships involving `AppLockRepository` (e.g. with `LockedAppsRepository` and `PreferencesRepository`) actually correct?**
  _`AppLockRepository` has 2 INFERRED edges - model-reasoned connections that need verification._
- **What connects `AppIntro`, `SetPassword`, `SetPasswordPattern` to the rest of the system?**
  _54 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `IPackageManager` be split into smaller, more focused modules?**
  _Cohesion score 0.015625 - nodes in this community are weakly interconnected._
- **Should `IActivityManager` be split into smaller, more focused modules?**
  _Cohesion score 0.01639344262295082 - nodes in this community are weakly interconnected._