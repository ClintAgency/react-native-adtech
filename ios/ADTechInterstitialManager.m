#import "ADTechInterstitialManager.h"
#import "ADTechInterstitialView.h"

@interface ADTechInterstitialManager ()
@property(nonatomic, strong)ADTechInterstitialView *playerView;
@end

@implementation ADTechInterstitialManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
    return [[ADTechInterstitialView alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(placementId, NSString);
RCT_EXPORT_VIEW_PROPERTY(onSizeChange, RCTBubblingEventBlock)

@end
