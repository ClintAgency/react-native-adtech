
# react-native-ad-tech

## Getting started

`$ npm install react-native-ad-tech --save`

### Mostly automatic installation

`$ react-native link react-native-ad-tech`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-ad-tech` and add `RNAdTech.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAdTech.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAdTechPackage;` to the imports at the top of the file
  - Add `new RNAdTechPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-ad-tech'
  	project(':react-native-ad-tech').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-ad-tech/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-ad-tech')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNAdTech.sln` in `node_modules/react-native-ad-tech/windows/RNAdTech.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Ad.Tech.RNAdTech;` to the usings at the top of the file
  - Add `new RNAdTechPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNAdTech from 'react-native-ad-tech';

// TODO: What to do with the module?
RNAdTech;
```
  