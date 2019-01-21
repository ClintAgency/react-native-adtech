
# react-native-adtech

## Getting started

`$ npm install react-native-adtech --save`

or

`$ yarn add react-native-adtech`

## Installation

You can use npm or Yarn to install the latest beta version:

**npm:**

    npm i --save react-native-adtech

**Yarn:**

    yarn add react-native-adtech

In order to use this library, you have to link it to your project first. There's excellent documentation on how to do this in the [React Native Docs](https://facebook.github.io/react-native/docs/linking-libraries-ios.html#content).

`$ react-native link react-native-adtech`

### Specifique installation installation

#### iOS

Add MMAdSDK to iOS project. [Download iOS SDK](http://docs.onemobilesdk.aol.com/ios-ad-sdk/index.html)

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-adtech` and add `RNAdTech.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAdTech.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.clintagency.adtech.RNAdTechPackage;` to the imports at the top of the file
  - Add `new RNAdTechPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-adtech'
  	project(':react-native-adtech').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-adtech/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      implementation project(':react-native-adtech')
  	```

## Usage

### Inline Ad

```jsx
import { ADTechInline } from 'react-native-adtech';

<ADTechInline
  options={{
    identifier: 'identifier',
    placementId: '123456',
    size: 0,
  }}
/>
```

#### Props

<table>
  <thead>
    <tr>
      <th>Value</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>options</code></td>
      <td>An object of InlineAd Options</td>
    </tr>
  </tbody>
</table>


##### options

<table>
  <thead>
    <tr>
      <th>Value</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>identifier</code></td>
      <td>for reuse cell. With this identifier, if an InlineAd is recreated, no impression is sent to statistics</td>
    </tr>
    <tr>
      <td><code>placementId</code></td>
      <td>ADTech placement id</td>
    </tr>
    <tr>
      <td><code>size</code></td>
      <td>
size identifier:

  - `0`: Banner (350x50)
  - `1`: Large Banner (320x100)
  - `2`: Medium Rectangle (300x250)  
  - `3`: Full Banner (468x60)
  - `4`: Leaderboard (728x90)

      </td>
    </tr>
  </tbody>
</table>

### Interstitial

```jsx
import RNAdTech from 'react-native-adtech';

RNAdTech.hideInterstitial();
RNAdTech.showInterstitial(placementId);
```

### GDPR

```jsx
import { ConsentString } from 'consent-string';
import RNAdTech from 'react-native-adtech';
import vendorList from 'path/to/vendorList.json';

const consentData = new ConsentString();

consentData.setGlobalVendorList(vendorList);
consentData.setCmpId(1);
consentData.setCmpVersion(1);
consentData.setConsentScreen(1);
consentData.setConsentLanguage('fr');

RNAdTech.setGDPR(true, consentData.getConsentString());
```

