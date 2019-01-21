import React from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent, View } from 'react-native';


class ADTechInterstitialView extends React.PureComponent {
  static propTypes = {
    onSizeChange: PropTypes.func,
    placementId: PropTypes.string.isRequired,
    style: PropTypes.object,
  };

  constructor(props) {
    super(props);

    this.state = {};
  }

  handleSizeChange = (event) => {
    const { height, width } = event.nativeEvent;
    this.setState({ style: { width, height } });
    if (this.props.onSizeChange) {
      this.props.onSizeChange({ width, height });
    }
  }

  render() {
    return (
      <View
        style={[{
          justifyContent: 'center',
          alignItems: 'center',
        }, this.props.style]}
      >
        <ADTechInterstitial
          {...this.props}
          style={this.state.style}
          onSizeChange={this.handleSizeChange}
        />
      </View>
    );
  }
}

const ADTechInterstitial = requireNativeComponent('ADTechInterstitial'); // eslint-disable-line


export default ADTechInterstitialView;
