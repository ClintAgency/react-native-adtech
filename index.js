
import { NativeModules } from 'react-native';

export { default as ADTechInline } from './ADTechInline';
export { default as ADTechInterstitial } from './ADTechInterstitial';

const { ADTechManager } = NativeModules;

export default ADTechManager;
