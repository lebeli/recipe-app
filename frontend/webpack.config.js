const path = require("path");
const webpack = require("webpack");

require.extensions['.css'] = () => {
  return;
};

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
        test: /\.scss$/,
        use: [
          // Creates `style` nodes from JS strings
          'style-loader',
          // Translates CSS into CommonJS
          'css-loader',
          // Compiles Sass to CSS
          'sass-loader',
        ]
      },
      // to load fonts
      {
        test: /\.(ttf|otf)$/,
        use: ['file-loader']
      },  
    ]
  },
  resolve: { extensions: ["*", ".js", ".jsx", ".scss"] },
  output: {
    path: path.resolve(__dirname, "dist/"),
    publicPath: "/dist/",
    filename: "bundle.js"
  },
  devServer: {
    contentBase: path.join(__dirname, "public/"),
    port: 3000,
    publicPath: "http://localhost:3000/dist/",
    hotOnly: true
  },
  plugins: [new webpack.HotModuleReplacementPlugin()]
};
