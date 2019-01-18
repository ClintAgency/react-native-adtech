#import "RNAdTech.h"
#import <MMAdSDK/MMAdSDK.h>

@implementation RNAdTech

int autoHideTime;
NSTimer *autoHideTimer;

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(setGDPR:(BOOL *)activate contentstring:(NSString *)contentstring)
{
  [[MMSDK sharedInstance] setConsentDataValue:contentstring forKey:MMIABConsentKey];
}
RCT_EXPORT_METHOD(setAd:(BOOL *)activate)
{
  
  RCTLogInfo(@"setAd %d", activate);
}

RCT_EXPORT_METHOD(showInterstitial:(NSString *)placementId autoHide:(int)autoHide)
{
  
  RCTLogInfo(@"showInterstitial %@", placementId);
  
  dispatch_async( dispatch_get_main_queue(), ^{
    self.interstitialAd = [[MMInterstitialAd alloc] initWithPlacementId:placementId];
    self.interstitialAd.delegate = self;
    [self.interstitialAd load:nil];
    
    RCTLogInfo(@"showInterstitial autoHide %d", autoHide);
    
    
    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;

    [self.interstitialAd showFromViewController:rootViewController];
    
    autoHideTime = autoHide;
    
    if (autoHideTimer) {
      [autoHideTimer invalidate];
      autoHideTimer = nil;
    }
  });
  
}

RCT_EXPORT_METHOD(hideInterstitial)
{
  
  RCTLogInfo(@"hideInterstitial");
  [self hideInterstitialAd];
}

- (void) hideInterstitialAd
{
  [autoHideTimer invalidate];
  autoHideTimer = nil;
  
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
  RCTLogInfo(@"interstitialAdLoadDidSucceed %d", autoHideTime);
}

- (void)interstitialAdDidDismiss:(MMInterstitialAd *)ad
{
  RCTLogInfo(@"interstitialAdDidDismiss");
  [autoHideTimer invalidate];
  autoHideTimer = nil;
}

- (void)interstitialAdDidExpire:(MMInterstitialAd *)ad {
  RCTLogInfo(@"interstitialAdDidExpire");
  [self hideInterstitialAd];
  [autoHideTimer invalidate];
  autoHideTimer = nil;
}

- (void)interstitialAdDidDisplay:(MMInterstitialAd *)ad
{
  RCTLogInfo(@"interstitialAdDidDisplay %d", autoHideTime);
  if (autoHideTime) {
    // autoHideTimer = [NSTimer scheduledTimerWithTimeInterval:autoHideTime/1000 target:self selector:@selector(hideInterstitialAd) userInfo:nil repeats:YES];
  }
}

@end
