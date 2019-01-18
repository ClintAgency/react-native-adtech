#import <foundation/Foundation.h>

@interface ADTechConfig : NSObject {
  NSMutableDictionary *inlineAds;
  NSMutableDictionary *inlineAdViews;
}

@property (strong, retain) NSMutableDictionary *inlineAds;
@property (strong, retain) NSMutableDictionary *inlineAdViews;

+ (id)sharedManager;

@end
