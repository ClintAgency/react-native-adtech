#import "RNAdTech.h"
#import <MMAdSDK/MMAdSDK.h>

@implementation RNAdTech

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(setGDPR:(BOOL *)activate contentstring:(NSString *)contentstring)
{
  [[MMSDK sharedInstance] setConsentDataValue:contentstring forKey:MMIABConsentKey];
}

RCT_EXPORT_METHOD(showInterstitial:(NSString *)placementId)
{
  
  RCTLogInfo(@"showInterstitial %@", placementId);
  
  dispatch_async( dispatch_get_main_queue(), ^{
    self.interstitialAd = [[MMInterstitialAd alloc] initWithPlacementId:placementId];
    self.interstitialAd.delegate = self;
    [self.interstitialAd load:nil];    
    
    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;

    [self.interstitialAd showFromViewController:rootViewController];
  });
  
}

RCT_EXPORT_METHOD(hideInterstitial)
{
  
  RCTLogInfo(@"hideInterstitial");
  [self hideInterstitialAd];
}

+(void)initializeWithSiteId:(NSString *)siteId
{
    MMAppSettings* appSettings = [[MMAppSettings alloc] init];
    appSettings.siteId = siteId;
    
    MMUserSettings* userSettings = [[MMUserSettings alloc] init];
    
    [[MMSDK sharedInstance] initializeWithSettings:appSettings withUserSettings:userSettings];
    [[MMSDK sharedInstance] setConsentRequired:YES];
}


- (void) hideInterstitialAd
{
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;

  dispatch_async( dispatch_get_main_queue(), ^{
    RCTLogInfo(@"showInterstitial hide");
    [rootViewController dismissViewControllerAnimated:true completion:^{
      RCTLogInfo(@"showInterstitial dismissed");
    }];
  });
  
}

- (void)interstitialAdLoadDidSucceed:(MMInterstitialAd *)ad
{
    RCTLogInfo(@"interstitialAdLoadDidSucceed");
}


- (void)interstitialAdDidDismiss:(MMInterstitialAd *)ad
{
  RCTLogInfo(@"interstitialAdDidDismiss");
}

- (void)interstitialAdDidExpire:(MMInterstitialAd *)ad {
  RCTLogInfo(@"interstitialAdDidExpire");
  [self hideInterstitialAd];
}

- (void)interstitialAdDidDisplay:(MMInterstitialAd *)ad
{
  RCTLogInfo(@"interstitialAdDidDisplay");
}

@end
