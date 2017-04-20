## Installation

1. Download and Install JDK (http://www.oracle.com/technetwork/java/javase/downloads/index.html)

2. Download and Install Android Studio (https://developer.android.com/studio/index.html)

3. Run Android Studio

4. Download and Install Git (https://git-scm.com/downloads)

5. Clone KEYPRTest project (https://github.com/Katkov/keyprtest.git)

6. Open Android Studio

7. In the "Welcome to android Studio" dialog choose "Import Project", or press in the Top menu "File=>New=>Import Project"

8. In the file chooser dialog find the KEYPRTest project and press "OK". Android Studio will automatically set up it for you.
Wait until the Gradle will finish the work.

## Deployment with virtual device

1. Press run button in the Android Studio top menu (or Shift + F10)
  
2. In the Select Deployment target dialog choose an already created emulator. 
   If you didn't have an emulator before, than you need to create it.
   Press "Create new emulator" button and choose a device, which you you want to
   emulate and press "Next", choose the system image and press "Create" button.
   (You can follow this tutorial to make this https://developer.android.com/studio/run/emulator.html)

3. Press "OK" and wait until the Gradle will build the app and run it on the Emulator

## Deployment with device

1. Turn on device in the Development mode. The way how to do that depends on the android version and the device manufacturer.
   Please follow this tutorial https://developer.android.com/studio/run/emulator.html

2. Connect the device with a USB cable to your PC or MAC.
   
3. Let your computer to install a driver of the device. 
   
4. Press run button in the Android Studio top menu (or Shift + F10)
   
5. In the Select Deployment target dialog choose you device.
  
6. Press "OK" and wait until the Gradle will build the app and run it on the device  
