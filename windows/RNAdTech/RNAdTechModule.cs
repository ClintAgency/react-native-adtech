using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Ad.Tech.RNAdTech
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNAdTechModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNAdTechModule"/>.
        /// </summary>
        internal RNAdTechModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNAdTech";
            }
        }
    }
}
