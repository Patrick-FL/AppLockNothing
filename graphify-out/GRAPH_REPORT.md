# Graph Report - AppLock  (2026-10-24)

## Corpus Check
- 118 files · ~111,591 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1733 nodes · 3044 edges · 105 communities (59 shown, 46 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 37 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `19b638d7`
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
- androidx.annotation.RequiresPermission
- android.graphics.Rect
- LockedAppsRepository
- PasswordOverlayActivity
- ShizukuAppLockService
- IActivityManager.java
- Dp
- AnkiRepository
- IUidObserver
- ShizukuActivityManager.kt
- AppNavigator.kt
- android.os.Parcel
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
- LockScreenOverlayManager.kt
- IActivityTaskManage
- SecurityUtils
- android.net.Uri
- android.graphics.Bitmap
- AlphanumericPasswordOverlayScreen
- RunningTaskInfo
- android.content.IntentSender
- android.content.pm.PackageManager.ComponentEnabledSetting
- Screen
- AppLockApplication
- BootReceiver.kt
- IPackageDataObserver
- AppTask
- IActivityController
- Stub
- OnUidImportanceListener
- IProgressListener
- README.md
- AppLockServiceStarter.kt
- AppIconCache.kt
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
- BackendImplementation

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
- `ShizukuActivityManager` --references--> `IWindowManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/view/IWindowManager.java
- `getTasksWrapper()` --references--> `ActivityManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/app/ActivityManager.kt
- `filterVisible()` --references--> `ActivityManager`  [EXTRACTED]
  app/src/main/java/dev/pranav/applock/shizuku/ShizukuActivityManager.kt → hidden-api/src/main/java/android/app/ActivityManager.kt
- `AppLockRepository` --calls--> `LockedAppsRepository`  [INFERRED]
  app/src/main/java/dev/pranav/applock/data/repository/AppLockRepository.kt → app/src/main/java/dev/pranav/applock/data/repository/LockedAppsRepository.kt
- `AppLockRepository` --calls--> `PreferencesRepository`  [INFERRED]
  app/src/main/java/dev/pranav/applock/data/repository/AppLockRepository.kt → app/src/main/java/dev/pranav/applock/data/repository/PreferencesRepository.kt

## Import Cycles
- None detected.

## Communities (105 total, 46 thin omitted)

### Community 2 - "ActivityManager"
Cohesion: 0.03
Nodes (11): ActivityManager, ForegroundServiceApiEvent, ForegroundServiceApiType, MoveTaskFlags, PendingIntentInfo, ProcessCapability, ProcessState, RestrictionLevel (+3 more)

### Community 3 - "SettingsScreen.kt"
Cohesion: 0.05
Nodes (60): enableAccessibilityServiceWithShizuku(), exec(), isAccessibilityServiceEnabled(), ComponentName, Context, openAccessibilitySettings(), AddProtectedAppsSheetContent(), CommunityDialog() (+52 more)

### Community 5 - "AntiUninstallScreen.kt"
Cohesion: 0.07
Nodes (31): appLockRepository(), blockUninstallForUser(), unblockUninstallForUser(), AntiUninstallScreen(), AntiUninstallViewModel, AppProtectionItem(), checkShizukuState(), isShizukuInstalled() (+23 more)

### Community 6 - "Dot"
Cohesion: 0.09
Nodes (30): finishPasswordSetup(), createBatteryOptimizationIntent(), getVibrator(), Context, Intent, launchBatterySettings(), launchGeneralBatterySettings(), showBatteryOptimizationToast() (+22 more)

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
Cohesion: 0.06
Nodes (5): android.os.IBinder, ApplicationErrorReport, Notification, IForegroundServiceObserver, ContentProviderProxy

### Community 13 - "UsageLockService"
Cohesion: 0.13
Nodes (13): android, DeviceAdmin, Context, SharedPreferences, IBinder, Intent, Notification, NotificationManager (+5 more)

### Community 14 - "android.content.ComponentName"
Cohesion: 0.06
Nodes (6): android.content.ComponentName, android.content.IntentFilter, PendingIntent, ActivityInfo, InstrumentationInfo, ServiceInfo

### Community 15 - "WindowConfiguration"
Cohesion: 0.07
Nodes (5): androidx.annotation.NonNull, Creator, Override, WindowConfig, WindowConfiguration

### Community 16 - "TaskInfo"
Cohesion: 0.09
Nodes (8): android.content.pm.ActivityInfo, android.content.res.Configuration, android.graphics.Point, Override, PictureInPictureParams, TaskInfo, WindowConfiguration.ActivityType, WindowConfiguration.WindowingMode

### Community 17 - "ActivityTaskManager"
Cohesion: 0.08
Nodes (4): android.content.Context, ActivityTaskManager, RemoteAnimationDefinition, SplashScreenViewParcelable

### Community 18 - "TaskInfo"
Cohesion: 0.10
Nodes (14): Activity, ConfigurationInfo, FileDescriptor, ActivityInfo, Bitmap, Configuration, IBinder, Intent (+6 more)

### Community 19 - "ParceledListSlice"
Cohesion: 0.09
Nodes (3): ApplicationExitInfo, ApplicationStartInfo, ParceledListSlice

### Community 23 - "PasswordOverlayActivity"
Cohesion: 0.15
Nodes (5): BiometricPrompt, Bundle, Configuration, FragmentActivity, PasswordOverlayActivity

### Community 24 - "ShizukuAppLockService"
Cohesion: 0.18
Nodes (6): IBinder, Intent, Notification, NotificationManager, Service, ShizukuAppLockService

### Community 25 - "IActivityManager.java"
Cohesion: 0.11
Nodes (9): android.content.LocusId, android.content.pm.ApplicationInfo, android.os.Binder, android.os.Debug, android.os.IInterface, android.os.ParcelFileDescriptor, Stub, IWindowManager (+1 more)

### Community 26 - "Dp"
Cohesion: 0.06
Nodes (26): hasUsagePermission(), AppIntroScreen(), AppUsageMethod, ACCESSIBILITY, SHIZUKU, USAGE_STATS, ImageVector, NavController (+18 more)

### Community 27 - "AnkiRepository"
Cohesion: 0.16
Nodes (12): AnkiCard, AnkiDeck, AnkiRepository, AnkiStatus, Error, Uri, NotInstalled, PermissionDenied (+4 more)

### Community 29 - "ShizukuActivityManager.kt"
Cohesion: 0.17
Nodes (7): DeviceUnlockReceiver, BroadcastReceiver, Context, Intent, filterVisible(), getTasksWrapper(), ShizukuActivityManager

### Community 30 - "AppNavigator.kt"
Cohesion: 0.33
Nodes (9): AppNavHost(), createBiometricPromptInfo(), handleAuthenticationSuccess(), handleBiometricAuthentication(), BiometricPrompt, BiometricPrompt, FragmentActivity, navigateToMain() (+1 more)

### Community 32 - "AppLockManager"
Cohesion: 0.13
Nodes (6): AppLockConstants, AppLockManager, isDeviceLocked(), isServiceRunning(), Context, dev

### Community 33 - "RunningAppProcessInfo"
Cohesion: 0.17
Nodes (3): Importance, Context, RunningAppProcessInfo

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
Cohesion: 0.35
Nodes (9): AdminDisableActivity, OnBackPressedCallback, AdminDisablePasswordScreen(), AdminDisablePatternScreen(), AdminDisableScreen(), Bundle, ComponentName, Modifier (+1 more)

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

### Community 43 - "LockScreenOverlayManager.kt"
Cohesion: 0.07
Nodes (24): NavigationManager, AppIntroManager, Context, LockScreenOverlayManager, androidx, BiometricPrompt, Bundle, FragmentActivity (+16 more)

### Community 44 - "IActivityTaskManage"
Cohesion: 0.23
Nodes (5): IActivityTaskManage, Binder, IBinder, IInterface, Stub

### Community 46 - "android.net.Uri"
Cohesion: 0.10
Nodes (11): android.content.res.AssetFileDescriptor, android.database.Cursor, android.net.Uri, androidx.annotation.RequiresApi, AttributionSource, ContentProviderHolder, ContentProviderNative, Override (+3 more)

### Community 48 - "AlphanumericPasswordOverlayScreen"
Cohesion: 0.43
Nodes (5): AlphanumericPasswordOverlayScreen(), dev, Modifier, performVerification(), password

### Community 52 - "Screen"
Cohesion: 0.13
Nodes (14): AntiUninstall, AppIntro, ChangePassword, Main, PasswordOverlay, Screen, SetPassword, SetPasswordAlphanumeric (+6 more)

### Community 53 - "AppLockApplication"
Cohesion: 0.36
Nodes (3): AppLockApplication, Context, Application

### Community 54 - "BootReceiver.kt"
Cohesion: 0.44
Nodes (5): BootReceiver, BroadcastReceiver, Context, dev, Intent

### Community 56 - "AppTask"
Cohesion: 0.17
Nodes (4): AppTask, Bundle, RecentTaskInfo, WindowingLayer

### Community 60 - "OnUidImportanceListener"
Cohesion: 0.25
Nodes (3): Debug, OnUidImportanceListener, IntArray

### Community 62 - "README.md"
Cohesion: 0.25
Nodes (7): 🌟 Credits & Acknowledgements, Download, Features, License, ✨ New Features & Changes (in App Lock Nothing), Overview, Use Cases

### Community 63 - "AppLockServiceStarter.kt"
Cohesion: 0.53
Nodes (3): AppLockServiceStarter, Context, dev

### Community 64 - "AppIconCache.kt"
Cohesion: 0.43
Nodes (4): AppIconCache, ApplicationInfo, Context, ImageBitmap

### Community 68 - "TaskSnapshot"
Cohesion: 0.47
Nodes (3): Parcel, Parcelable, TaskSnapshot

### Community 74 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

### Community 111 - "BackendImplementation"
Cohesion: 0.17
Nodes (5): BackendServiceManager, BackendImplementation, ACCESSIBILITY, SHIZUKU, USAGE_STATS

## Knowledge Gaps
- **55 isolated node(s):** `AppIntro`, `SetPassword`, `SetPasswordPattern`, `SetPasswordAlphanumeric`, `ChangePassword` (+50 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **46 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `IActivityManager` connect `IActivityManager` to `ITaskStackListener`, `android.content.Intent`, `android.os.IBinder`, `android.content.ComponentName`, `ParceledListSlice`, `androidx.annotation.RequiresPermission`, `android.graphics.Rect`, `IActivityManager.java`, `IUidObserver`, `RunningAppProcessInfo`, `IProcessObserver`, `ComponentName`, `WindowConfiguration.java`, `IIntentSender`, `android.net.Uri`, `RunningTaskInfo`, `IPackageDataObserver`, `IActivityController`, `IProgressListener`, `MemoryInfo`, `IApplicationStartInfoCompleteListener`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.221) - this node is a cross-community bridge._
- **Why does `ActivityManager` connect `ActivityManager` to `SettingsScreen.kt`, `TaskInfo`, `Dp`, `ShizukuActivityManager.kt`, `AppLockManager`, `RunningAppProcessInfo`, `ComponentName`, `RunningTaskInfo`, `IPackageDataObserver`, `AppTask`, `OnUidImportanceListener`, `ApplicationStartInfo`, `MemoryInfo`, `UserHandle`, `HomeVisibilityListener`, `.getHistoricalProcessExitReasons`, `.setDeviceLocales`, `.setProcessStateSummary`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.207) - this node is a cross-community bridge._
- **Why does `AppLockRepository` connect `AppLockRepository` to `SettingsScreen.kt`, `AdminDisableActivity.kt`, `Dot`, `MainViewModel`, `PasswordOverlayScreen.kt`, `AppLockAccessibilityService`, `PreferencesRepository`, `UsageLockService`, `BackendImplementation`, `AppLockApplication`, `LockedAppsRepository`, `PasswordOverlayActivity`, `ShizukuAppLockService`, `ShizukuActivityManager.kt`?**
  _High betweenness centrality (0.200) - this node is a cross-community bridge._
- **Are the 2 inferred relationships involving `AppLockRepository` (e.g. with `LockedAppsRepository` and `PreferencesRepository`) actually correct?**
  _`AppLockRepository` has 2 INFERRED edges - model-reasoned connections that need verification._
- **What connects `AppIntro`, `SetPassword`, `SetPasswordPattern` to the rest of the system?**
  _55 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `IPackageManager` be split into smaller, more focused modules?**
  _Cohesion score 0.015625 - nodes in this community are weakly interconnected._
- **Should `IActivityManager` be split into smaller, more focused modules?**
  _Cohesion score 0.01639344262295082 - nodes in this community are weakly interconnected._