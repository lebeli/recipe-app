import React, { Component } from 'react';
import RecommenderImage from './RecommenderImage';
import RecommenderHeader from './RecommenderHeader';
import RecommenderReloadButton from './RecommenderReloadButton';
import wolfImage from './resources/wolf.jpg';
import toastImage from './resources/toast.jpg';
import './Recommender.scss';

class Recommender extends Component {
  constructor(props) {
    super(props);
    this.state = {
      image: toastImage,
    };
  }

  changeImage() {
    this.setState = {
      image: wolfImage,
    };
  }

  render() {
    return (
      <div className="Recommender" id="Container">
        <RecommenderHeader />
        <RecommenderReloadButton onClick="changeImage()" />
        <RecommenderImage teaserImage={this.state.image} />
      </div>
    );
  }
}

export default Recommender;
