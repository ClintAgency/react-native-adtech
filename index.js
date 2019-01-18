
import { NativeModules } from 'react-native';

export { default as ADTechInline } from './ADTechInline';
export { default as ADTechInterstitial } from './ADTechInterstitial';

const { RNAdTech } = NativeModules;

export default RNAdTech;
