#import "ADTechInlineView.h"
#import "ADTechConfig.h"

@implementation ADTechInlineView

- (instancetype)init
{
  if (self = [super init]) {
  }
  return self;
}

- (void)createAd:(NSDictionary *)options
{
  ADTechConfig *adtechConfig = [ADTechConfig sharedManager];
  MMInlineAdSize adSize = MMInlineAdSizeBanner;

  int size = [[options valueForKey:@"size"] intValue];
  self.placementId = [options valueForKey:@"placementId"];
  self.identifier = [options valueForKey:@"identifier"];

  if (size == 0)
    adSize = MMInlineAdSizeBanner;
  else if (size == 1)
    adSize = MMInlineAdSizeLargeBanner;
  else if (size == 2)
    adSize = MMInlineAdSizeMediumRectangle;
  else if (size == 3)
    adSize = MMInlineAdSizeFullBanner;
  else if (size == 4)
    adSize = MMInlineAdSizeLeaderboard;
  else
    adSize = MMInlineAdSizeFlexible;

  if (![adtechConfig.inlineAds objectForKey:self.identifier]) {
    self.inlineAd = [[MMInlineAd alloc] initWithPlacementId:self.placementId adSize:adSize];
    self.inlineAd.delegate = self;
    
    [self.inlineAd request:nil];
  } else {

    self.inlineAd = [adtechConfig.inlineAds objectForKey:self.identifier];
    NSArray *subviews = [adtechConfig.inlineAdViews objectForKey:self.identifier];
    for (UIView *subview in subviews) {
      [self.inlineAd.view addSubview:subview];
    }
    [self addSubview:self.inlineAd.view];
  }
}

-(void)setOptions:(NSDictionary *)options
{
  [self createAd:options];
}

- (void)traitCollectionDidChange:(nullable UITraitCollection *)previousTraitCollection {
  CGSize size = self.inlineAd.view.frame.size;
  
  if(!CGSizeEqualToSize(size, self.bounds.size)) {
    self.onSizeChange(@{
      @"width": @(size.width),
      @"height": @(size.height)
    });
  }
}

- (void)inlineAdRequestDidSucceed:(MMInlineAd *)ad {
  ADTechConfig *adtechConfig = [ADTechConfig sharedManager];

  UIView *view = ad.view;
  
  [adtechConfig.inlineAds setValue:ad forKey:self.identifier];
  [adtechConfig.inlineAdViews setValue:view.subviews forKey:self.identifier];

  [self addSubview:view];
}

- (void)didUpdateFocusInContext:(nonnull UIFocusUpdateContext *)context withAnimationCoordinator:(nonnull UIFocusAnimationCoordinator *)coordinator {
  NSLog(@"ADTechInlineView -- didUpdateFocusInContext --");
}

- (void)setNeedsFocusUpdate {
  NSLog(@"ADTechInlineView -- setNeedsFocusUpdate --");
}


- (void)updateFocusIfNeeded {
  NSLog(@"ADTechInlineView -- updateFocusIfNeeded --");
  
}

@end
