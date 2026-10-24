# Graph Report - AppLock  (2026-10-18)

## Corpus Check
- 118 files · ~111,616 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 1727 nodes · 3038 edges · 103 communities (64 shown, 39 thin omitted)
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
- Dp
- AnkiRepository
- TaskDescription
- ShizukuActivityManager.kt
- AppNavigator.kt
- LockScreenOverlayManager.kt
- AppLockManager
- RunningAppProcessInfo
- IProcessObserver
- android.os.Binder
- LogUtils
- AdminDisableActivity.kt
- PasswordOverlayScreen.kt
- MainViewModel
- ComponentName
- WindowConfiguration.java
- IIntentSender
- NavigationManager
- IActivityTaskManage
- android.os.Parcel
- IContentProvider
- IPackageManager.java
- AlphanumericPasswordOverlayScreen
- androidx.annotation.Nullable
- TransparentBiometricActivity.kt
- Screen
- AppLockApplication
- BootReceiver.kt
- IPackageDataObserver
- AppTask
- IActivityController
- SecurityUtils
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
4. `AppLockRepository` - 89 edges
5. `PreferencesRepository` - 65 edges
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

## Communities (103 total, 39 thin omitted)

### Community 2 - "ActivityManager"
Cohesion: 0.03
Nodes (11): ActivityManager, ForegroundServiceApiEvent, ForegroundServiceApiType, MoveTaskFlags, PendingIntentInfo, ProcessCapability, ProcessState, RestrictionLevel (+3 more)

### Community 3 - "SettingsScreen.kt"
Cohesion: 0.06
Nodes (61): enableAccessibilityServiceWithShizuku(), exec(), isAccessibilityServiceEnabled(), ComponentName, Context, openAccessibilitySettings(), BackendServiceManager, BackendImplementation (+53 more)

### Community 5 - "AntiUninstallScreen.kt"
Cohesion: 0.08
Nodes (30): blockUninstallForUser(), unblockUninstallForUser(), AntiUninstallScreen(), AntiUninstallViewModel, AppProtectionItem(), checkShizukuState(), isShizukuInstalled(), ApplicationInfo (+22 more)

### Community 6 - "Dot"
Cohesion: 0.09
Nodes (29): createBatteryOptimizationIntent(), getVibrator(), Context, Intent, launchBatterySettings(), launchGeneralBatterySettings(), showBatteryOptimizationToast(), vibrate() (+21 more)

### Community 8 - "android.content.Intent"
Cohesion: 0.08
Nodes (6): android.content.Intent, android.os.Bundle, ContentProviderHolder, IApplicationThread, IIntentReceiver, ProfilerInfo

### Community 9 - "AppLockAccessibilityService"
Cohesion: 0.12
Nodes (9): AccessibilityEvent, AccessibilityNodeInfo, AccessibilityService, AppLockAccessibilityService, BiometricState, AUTH_STARTED, IDLE, Intent (+1 more)

### Community 10 - "TaskStackListener"
Cohesion: 0.11
Nodes (7): android.app.ActivityManager.RunningTaskInfo, Deprecated, dev.rikka.tools.refine.RefineAs, Override, TaskStackListener, LayoutParams, WindowManagerHidden

### Community 11 - "android.os.IBinder"
Cohesion: 0.06
Nodes (6): android.os.IBinder, ApplicationErrorReport, Notification, Stub, IForegroundServiceObserver, ContentProviderProxy

### Community 13 - "UsageLockService"
Cohesion: 0.11
Nodes (14): android, DeviceAdmin, Context, SharedPreferences, hasUsagePermission(), IBinder, Intent, Notification (+6 more)

### Community 14 - "android.content.ComponentName"
Cohesion: 0.07
Nodes (6): android.content.ComponentName, android.content.IntentFilter, PendingIntent, ActivityInfo, InstrumentationInfo, ServiceInfo

### Community 15 - "WindowConfiguration"
Cohesion: 0.08
Nodes (4): Creator, Override, WindowConfig, WindowConfiguration

### Community 16 - "TaskInfo"
Cohesion: 0.09
Nodes (8): android.content.pm.ActivityInfo, android.content.res.Configuration, android.graphics.Point, Override, PictureInPictureParams, TaskInfo, WindowConfiguration.ActivityType, WindowConfiguration.WindowingMode

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

### Community 21 - "android.graphics.Rect"
Cohesion: 0.11
Nodes (3): android.graphics.Rect, androidx.annotation.NonNull, RootTaskInfo

### Community 23 - "PasswordOverlayActivity"
Cohesion: 0.15
Nodes (5): BiometricPrompt, Bundle, Configuration, FragmentActivity, PasswordOverlayActivity

### Community 24 - "ShizukuAppLockService"
Cohesion: 0.18
Nodes (6): IBinder, Intent, Notification, NotificationManager, Service, ShizukuAppLockService

### Community 25 - "IActivityManager.java"
Cohesion: 0.11
Nodes (5): android.content.LocusId, android.content.pm.ApplicationInfo, android.net.Uri, android.os.Debug, android.os.ParcelFileDescriptor

### Community 26 - "Dp"
Cohesion: 0.05
Nodes (27): AppIntroManager, Context, AppIntroScreen(), AppUsageMethod, ACCESSIBILITY, SHIZUKU, USAGE_STATS, ImageVector (+19 more)

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
Cohesion: 0.20
Nodes (14): AppNavHost(), createBiometricPromptInfo(), finishPasswordSetup(), handleAuthenticationSuccess(), handleBiometricAuthentication(), BiometricPrompt, BiometricPrompt, FragmentActivity (+6 more)

### Community 31 - "LockScreenOverlayManager.kt"
Cohesion: 0.20
Nodes (12): LockScreenOverlayManager, AppLockTheme(), ComposeView, WindowManagerImpl, Lifecycle, LifecycleOwner, OnBackPressedDispatcherOwner, SavedStateRegistry (+4 more)

### Community 33 - "RunningAppProcessInfo"
Cohesion: 0.13
Nodes (5): Importance, Bundle, Context, Intent, RunningAppProcessInfo

### Community 34 - "IProcessObserver"
Cohesion: 0.13
Nodes (3): IProcessObserver, Binder, Stub

### Community 35 - "android.os.Binder"
Cohesion: 0.13
Nodes (11): android.annotation.TargetApi, android.os.Binder, android.os.IInterface, android.window.TaskSnapshot, IActivityTaskManager, Stub, IBinder, Stub (+3 more)

### Community 36 - "LogUtils"
Cohesion: 0.26
Nodes (4): Context, CoroutineScope, Uri, LogUtils

### Community 37 - "AdminDisableActivity.kt"
Cohesion: 0.33
Nodes (11): appLockRepository(), AdminDisableActivity, OnBackPressedCallback, AdminDisablePasswordScreen(), AdminDisablePatternScreen(), AdminDisableScreen(), Bundle, ComponentName (+3 more)

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
Cohesion: 0.19
Nodes (8): android.os.Parcelable, android.util.proto.ProtoOutputStream, androidx.annotation.IntDef, SelfMovable, ActivityType, AlwaysOnTop, WindowingMode, java.lang.annotation.Retention

### Community 43 - "NavigationManager"
Cohesion: 0.24
Nodes (5): NavigationManager, androidx, Bundle, FragmentActivity, MainActivity

### Community 44 - "IActivityTaskManage"
Cohesion: 0.23
Nodes (5): IActivityTaskManage, Binder, IBinder, IInterface, Stub

### Community 46 - "IContentProvider"
Cohesion: 0.16
Nodes (9): android.content.res.AssetFileDescriptor, android.database.Cursor, androidx.annotation.RequiresApi, AttributionSource, ContentProviderNative, Override, IContentProvider, ICancellationSignal (+1 more)

### Community 47 - "IPackageManager.java"
Cohesion: 0.20
Nodes (4): android.content.IntentSender, android.content.pm.PackageManager.ComponentEnabledSetting, android.graphics.Bitmap, android.os.UserHandle

### Community 48 - "AlphanumericPasswordOverlayScreen"
Cohesion: 0.43
Nodes (5): AlphanumericPasswordOverlayScreen(), dev, Modifier, performVerification(), password

### Community 50 - "TransparentBiometricActivity.kt"
Cohesion: 0.31
Nodes (5): BiometricPrompt, Bundle, FragmentActivity, TransparentBiometricActivity, BiometricPrompt

### Community 52 - "Screen"
Cohesion: 0.17
Nodes (11): AntiUninstall, AppIntro, ChangePassword, Main, PasswordOverlay, Screen, SetPassword, SetPasswordAlphanumeric (+3 more)

### Community 53 - "AppLockApplication"
Cohesion: 0.36
Nodes (3): AppLockApplication, Context, Application

### Community 54 - "BootReceiver.kt"
Cohesion: 0.44
Nodes (5): BootReceiver, BroadcastReceiver, Context, dev, Intent

### Community 56 - "AppTask"
Cohesion: 0.22
Nodes (3): AppTask, RecentTaskInfo, WindowingLayer

### Community 60 - "OnUidImportanceListener"
Cohesion: 0.25
Nodes (3): Debug, OnUidImportanceListener, IntArray

### Community 62 - "README.md"
Cohesion: 0.29
Nodes (6): Download, Features, License, Overview, Support Development, Use Cases

### Community 63 - "AppLockServiceStarter.kt"
Cohesion: 0.21
Nodes (7): AppLockServiceStarter, Context, dev, AppLockConstants, isDeviceLocked(), isServiceRunning(), Context

### Community 64 - "AppIconCache.kt"
Cohesion: 0.43
Nodes (4): AppIconCache, ApplicationInfo, Context, ImageBitmap

### Community 68 - "TaskSnapshot"
Cohesion: 0.47
Nodes (3): Parcel, Parcelable, TaskSnapshot

### Community 74 - "gradlew"
Cohesion: 0.83
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **54 isolated node(s):** `AppIntro`, `SetPassword`, `SetPasswordPattern`, `SetPasswordAlphanumeric`, `ChangePassword` (+49 more)
  These have ≤1 connection - possible missing edges or undocumented components.
- **39 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ActivityManager` connect `ActivityManager` to `ITaskStackListener`, `TaskInfo`, `Dp`, `TaskDescription`, `ShizukuActivityManager.kt`, `RunningAppProcessInfo`, `ComponentName`, `IPackageDataObserver`, `AppTask`, `OnUidImportanceListener`, `AppLockServiceStarter.kt`, `ApplicationStartInfo`, `MemoryInfo`, `UserHandle`, `HomeVisibilityListener`, `.getHistoricalProcessExitReasons`, `.setDeviceLocales`, `.setProcessStateSummary`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.219) - this node is a cross-community bridge._
- **Why does `IActivityManager` connect `IActivityManager` to `ITaskStackListener`, `android.content.Intent`, `android.os.IBinder`, `UsageLockService`, `android.content.ComponentName`, `ParceledListSlice`, `IUidObserver`, `android.graphics.Rect`, `IActivityManager.java`, `RunningAppProcessInfo`, `IProcessObserver`, `ComponentName`, `IIntentSender`, `androidx.annotation.Nullable`, `IPackageDataObserver`, `IActivityController`, `IProgressListener`, `MemoryInfo`, `IApplicationStartInfoCompleteListener`, `ProcessErrorStateInfo`?**
  _High betweenness centrality (0.203) - this node is a cross-community bridge._
- **Why does `AppLockRepository` connect `AppLockRepository` to `SettingsScreen.kt`, `AdminDisableActivity.kt`, `Dot`, `MainViewModel`, `PasswordOverlayScreen.kt`, `AppLockAccessibilityService`, `PreferencesRepository`, `UsageLockService`, `.getBackendImplementation`, `AppLockApplication`, `LockedAppsRepository`, `PasswordOverlayActivity`, `ShizukuAppLockService`, `ShizukuActivityManager.kt`?**
  _High betweenness centrality (0.191) - this node is a cross-community bridge._
- **Are the 2 inferred relationships involving `AppLockRepository` (e.g. with `LockedAppsRepository` and `PreferencesRepository`) actually correct?**
  _`AppLockRepository` has 2 INFERRED edges - model-reasoned connections that need verification._
- **What connects `AppIntro`, `SetPassword`, `SetPasswordPattern` to the rest of the system?**
  _54 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `IPackageManager` be split into smaller, more focused modules?**
  _Cohesion score 0.015625 - nodes in this community are weakly interconnected._
- **Should `IActivityManager` be split into smaller, more focused modules?**
  _Cohesion score 0.01639344262295082 - nodes in this community are weakly interconnected._