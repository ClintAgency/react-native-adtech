#import "React/RCTViewManager.h"
#import <MMAdSDK/MMAdSDK.h>

@interface RNAdTech : RCTViewManager <MMInterstitialDelegate>

@property (strong, nonatomic) MMInterstitialAd *interstitialAd;

+(void)initializeWithSiteId:(NSString *)siteId;

@end
