var path = require('path');

module.exports = {

  entry: {
    update : './src/main/javascript/update',
  },

  output: {
    path: path.join(__dirname, 'build'),
    filename: '[name].js'
  },

  module: {
    loaders: [
      {
      test: function (filename) {
        if (filename.indexOf('node_modules') !== -1) {
          return false;
        } else {
          return /\.js$/.test(filename) !== -1;
        }
      },
      loaders: ['babel-loader']
    },
  { test: /\.css$/, loader: "style-loader!css-loader" }]
  },

  resolve: {
    modulesDirectories: [path.join(__dirname, 'src'), 'node_modules']
  }

};
