#!/bin/bash

# Arabic Learning App Build Script

echo "🚀 Building Arabic Learning App..."

# Check if Android SDK is available
if [ -z "$ANDROID_HOME" ]; then
    echo "❌ ANDROID_HOME is not set. Please set your Android SDK path."
    exit 1
fi

# Clean previous builds
echo "🧹 Cleaning previous builds..."
./gradlew clean

# Build the project
echo "🔨 Building project..."
./gradlew build

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo ""
    echo "📱 To run the app:"
    echo "1. Connect an Android device or start an emulator"
    echo "2. Run: ./gradlew installDebug"
    echo "3. Or open the project in Android Studio and click Run"
    echo ""
    echo "📁 APK location: app/build/outputs/apk/debug/app-debug.apk"
else
    echo "❌ Build failed!"
    exit 1
fi