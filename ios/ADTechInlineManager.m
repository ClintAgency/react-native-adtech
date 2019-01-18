#import "ADTechInlineManager.h"
#import "ADTechInlineView.h"

@interface ADTechInlineManager ()
@property(nonatomic, strong)ADTechInlineView *playerView;
@end

@implementation ADTechInlineManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
    return [[ADTechInlineView alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(options, NSDictionary);
RCT_EXPORT_VIEW_PROPERTY(onSizeChange, RCTBubblingEventBlock)

@end
