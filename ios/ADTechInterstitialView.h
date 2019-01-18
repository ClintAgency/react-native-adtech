#import "React/RCTViewManager.h"
#import <UIKit/UIKit.h>
#import <MMAdSDK/MMAdSDK.h>

@interface ADTechInterstitialView : UIView <MMInlineDelegate>
@property (strong, nonatomic) MMInterstitialAd *interstitialAd;
@property(nonatomic, strong)NSString *placementId;

@property(nonatomic, copy)RCTBubblingEventBlock onSizeChange;

@end
