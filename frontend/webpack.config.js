const path = require("path");
const webpack = require("webpack");
const regenerator = require("regenerator-runtime");

module.exports = {
  entry: "./src/index.jsx",
  mode: "development",
  module: {
    rules: [
      // to load .jsx files
      {
        test: /\.jsx$/,
        exclude: /(node_modules)/,
        use: ["babel-loader", "eslint-loader"]
      },
      // to load style files
      {
        test: /\.(css|scss)$/,
        use: [
          // Creates `style` nodes from JS strings
          "style-loader",
          // Translates CSS into CommonJS
          "css-loader",
          // Compiles Sass to CSS
          "sass-loader"
        ]
      },
      // to load fonts
      {
        test: /\.(ttf|otf|png|ico|jpg)$/,
        use: [
          {
            loader: "file-loader",
            options: {
              name: "[name].[ext]"
            }
          }
        ]
      }
    ]
  },
  resolve: {
    extensions: ["*", ".js", ".jsx", ".scss"],
    alias: { "react-dom": "@hot-loader/react-dom" }
  },
  output: {
    path: path.resolve(__dirname, "dist/"),
    publicPath: "/dist/",
    filename: "bundle.js"
  },
  devServer: {
    contentBase: path.join(__dirname, "public/"),
    port: 3001,
    publicPath: "http://localhost:3001/dist/",
    hotOnly: true
  },
  plugins: [new webpack.HotModuleReplacementPlugin()]
};
