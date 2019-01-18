#import "ADTechInterstitialView.h"

@implementation ADTechInterstitialView

- (instancetype)init
{
    if (self = [super init]) {
      
    }
    return self;
}

- (void)createAd:(NSString *)placementId
{

  self.interstitialAd = [[MMInterstitialAd alloc] initWithPlacementId:placementId];
  self.interstitialAd.delegate = self;
  [self.interstitialAd load:nil];
  
  UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
  
  [self.interstitialAd showFromViewController:rootViewController];
}

-(void)setPlacementId:(NSString *)placementId
{
    NSLog(@"-- placementId -- %@", placementId);
  [self createAd:placementId];
}

-(void)layoutSubviews
{
  [super layoutSubviews];
  //self.inlineAd.view.frame = self.frame;

}

- (void)showInterstitialAd {
  if (self.interstitialAd.ready) {
    [self.interstitialAd showFromViewController:self];
  }
}


- (void)encodeWithCoder:(nonnull NSCoder *)aCoder {
  NSLog(@"ADTechView -- encodeWithCoder --");

}


- (void)didUpdateFocusInContext:(nonnull UIFocusUpdateContext *)context withAnimationCoordinator:(nonnull UIFocusAnimationCoordinator *)coordinator {
  NSLog(@"ADTechView -- didUpdateFocusInContext --");

}

- (void)setNeedsFocusUpdate {
  NSLog(@"ADTechView -- setNeedsFocusUpdate --");

}


- (void)updateFocusIfNeeded {
  NSLog(@"ADTechView -- updateFocusIfNeeded --");

}


@end
