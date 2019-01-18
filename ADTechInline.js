import React from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent, View } from 'react-native';


class ADTechInlineView extends React.Component {
  static propTypes = {
    onSizeChange: PropTypes.func,
    options: PropTypes.object.isRequired,
    style: PropTypes.object,
  };

  constructor(props) {
    super(props);

    this.state = {
      style: {
        width: 0,
        height: 0,
      },
    };
  }

  shouldComponentUpdate(nextProps, nextState) {
    return (
      this.props.options.placementId !== nextProps.options.placementId
      || this.props.options.size !== nextProps.options.size
      || this.state.style !== nextState.style
    );
  }

  handleSizeChange = (event) => {
    const { height, width } = event.nativeEvent;
    this.setState({ style: { width, height } });
    if (this.props.onSizeChange) {
      this.props.onSizeChange({ width, height });
    }
  }

  render() {
    if (!this.props.options) {
      return null;
    }

    return (
      <View
        style={[{
          justifyContent: 'center',
          alignItems: 'center',
        }, this.props.style]}
      >
        <ADTechInline
          {...this.props}
          style={this.state.style}
          onSizeChange={this.handleSizeChange}
        />
      </View>
    );
  }
}

const ADTechInline = requireNativeComponent('ADTechInline'); // eslint-disable-line


export default ADTechInlineView;
