import React, { Component } from 'react';
import RecommenderImage from './RecommenderImage';
import RecommenderHeader from './RecommenderHeader';
import RecommenderReloadButton from './RecommenderReloadButton';
import wolfImage from './resources/wolf.jpg';
import toastImage from './resources/toast.jpg';
import './Recommender.scss';

class Recommender extends Component {
  constructor() {
    super();
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
    const { image } = this.state;
    return (
      <div className="Recommender" id="Container">
        <RecommenderHeader />
        <RecommenderReloadButton onClick="changeImage()" />
        <RecommenderImage teaserImage={image} />
      </div>
    );
  }
}

export default Recommender;
