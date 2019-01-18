#import "React/RCTViewManager.h"
#import <UIKit/UIKit.h>
#import <MMAdSDK/MMAdSDK.h>

@interface ADTechInlineView : UIView <MMInlineDelegate> {}
  
  @property (nonatomic, retain) MMRequestInfo *requestInfo;
  @property (strong, strong) MMInlineAd *inlineAd;
@property(nonatomic, strong)NSDictionary *options;
@property(nonatomic, strong)NSString *identifier;
@property(nonatomic, strong)NSString *placementId;

  @property(nonatomic, copy)RCTBubblingEventBlock onSizeChange;

@end
