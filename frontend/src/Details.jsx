import React, { Component } from "react";
import "./Details.scss";

class Details extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="Details">
        <Ingredients />
        <Instructions />
      </div>
    );
  }
}

function Ingredients(params) {
  return (
    <div className="Ingredients">
      <h4>INGREDIENTS</h4>
      <ul>
        <li>Flour</li>
        <li>Tomatoes</li>
        <li>Basil</li>
      </ul>
    </div>
  );
}

function Instructions() {
  return (
    <div className="Instructions">
      <p>
        blablabalbalbala shefoia ehzfiuhaebf vsjvobswkjv kvbskjfsbflsbclasbfses
        jsfshoijefiodjwoifwioj fasohfoshosnsefsbvb jkfbvkhds ljsbnclsnvkjsybvbv
        idshfous ush fushefiu ouhs eiuhf hfsuhidh gserhgise uhrg uaehr rhguhrhu
        nfosnefkdn drhrf uaeh hrf hushefiuaehf uhfuh fuhouehf hkufvhur huhfausku
        ishfoaiw foiawejf awiehf iuaehrf auwhefiuaw hfawhefa eurfhiue aiugeruag
        ksnfjkeunr uhrf iuhrf urf eshfj suhfui eurhfiue urhfiu rugfigf urfgg hd
        sjefhiu urhf uerhf rhfu hfh hf drruhfiufh durhgud fuushv dfh ihfiueajfj
      </p>
    </div>
  );
}

export default Details;
