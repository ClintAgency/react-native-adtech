#import "ADTechConfig.h"

@implementation ADTechConfig

@synthesize inlineAds;
@synthesize inlineAdViews;

#pragma mark Singleton Methods

+ (id)sharedManager {
  static ADTechConfig *sharedADTechConfig = nil;
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
    sharedADTechConfig = [[self alloc] init];
  });
  return sharedADTechConfig;
}

- (id)init {
  if (self = [super init]) {
    inlineAds = [[NSMutableDictionary alloc]init];
    inlineAdViews = [[NSMutableDictionary alloc]init];
  }
  return self;
}

- (void)dealloc {
  // Should never be called, but just here for clarity really.
}

@end
