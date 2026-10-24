<div align="center">
  <img src="fastlane/metadata/android/en-US/images/featureGraphic.png" alt="AppLock Android Privacy Security" width="600" />
</div>

<h1 align="center">App Lock Nothing</h1>
<p align="center"><b>Open Source Android App Locker & Privacy Guard with Anki & Usage Credit Integration</b></p>

<p align="center">
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License">
  </a>
  <a href="https://github.com/Patrick-FL/AppLockNothing/releases">
    <img src="https://img.shields.io/github/v/release/Patrick-FL/AppLockNothing?label=Release" alt="Latest Release">
  </a>
</p>

<p align="center">
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/1.png" width="30%" alt="App List"/>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/2.png" width="30%" alt="Settings"/>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/3.png" width="30%" alt="Password Screen"/>
</p>

<p align="center">
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/4.png" width="30%" alt="Set Password"/>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/5.png" width="30%" alt="Unlock time"/>
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/6.png" width="30%" alt="Unlock time"/>
</p>
<p align="center">
  <img src="fastlane/metadata/android/en-US/images/phoneScreenshots/7.png" width="30%" alt="Set Password"/>
</p>

---

## Overview

**App Lock Nothing** is a modern, open-source Android app locker designed to protect your privacy, build productive habits, and secure sensitive data. Lock any app, prevent unauthorized access, and enjoy a seamless Material You experience without root required.

This repository extends the original project with innovative learning and habit-building mechanics, such as **AnkiDroid Flashcard Reviews** and **Time-Credit App Locking**.

<br/>

## 🌟 Credits & Acknowledgements

This project is a fork based on the fantastic open-source [AppLock](https://github.com/aload0/AppLock) created by [Pranav Purwar](https://github.com/PranavPurwar).

Huge thanks and full credit to **Pranav Purwar** and all contributors of the original repository for providing such a solid, elegant, and secure foundation under the MIT License.

---

## ✨ New Features & Changes (in App Lock Nothing)

Compared to the original upstream repository, **App Lock Nothing** introduces key habit-building and study integrations:

* 🎴 **AnkiDroid Flashcard Integration**: Connects with AnkiDroid to require reviewing flashcards before unlocking blocked apps. Turn distraction into study time!
* ⏱️ **Credit-Based Usage Lock**: Set up a time-credit system where restricted apps consume usage credits. Earn credits by spending time in designated productive ("Earn") apps or completing flashcards.
* 🛡️ **Dynamic Overlay Screen Manager**: Supports seamless switching between standard PIN/Password overlays, Anki Flashcard overlays, and Usage Credit warning screens.
* ⚙️ **Expanded Preferences & Settings**: Dedicated configuration options for Anki decks, credit rules, and enhanced Shizuku / Accessibility overlay controls.

---

## Features

- **AnkiDroid Integration**: Unlock apps by answering Anki flashcards
- **Usage Credit Lock**: Lock apps when your earned usage credits run out
- **Material You design**: Automatically adapts to your system theme
- **Biometric & PIN authentication**: Fingerprint, Face Unlock, and PIN support
- **Lock any app**: Protect any installed application on your device
- **Anti-uninstall protection**: Prevent unauthorized removal of locked apps
- **Unlock timeout**: Configurable grace period for convenience
- **No root required**: Works via standard Accessibility and Shizuku services
- **Real-time protection**: On-device background monitoring with full privacy

<br/>

> [!CAUTION]
> Google Play Protect may display warnings for apps using Overlay or Accessibility Permissions under the pretext of "accessing sensitive information". If this happens to you, consider temporarily disabling Play Protect as described [here](https://www.airdroid.com/quick-guides/disable-google-play-protect).

<br/>

## Download

Releases and builds can be found on [GitHub Releases](https://github.com/Patrick-FL/AppLockNothing/releases/latest).

<br/>

## Use Cases

- **Study & Focus**: Combine flashcards/study time with app restriction
- **Digital Wellbeing**: Limit distracting app usage with earned credits
- **Shared devices & Parental control**: Prevent unauthorized app access
- **Privacy**: Keep personal and financial apps secure

---

## License

```text
MIT License

Copyright (c) 2023-2025 Pranav Purwar
Copyright (c) 2026 Patrick-FL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
