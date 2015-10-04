var React = require('react');
var RxDom = require('rx-dom');
var JsonUpdate = require('./JsonUpdate');

React.render(
    <JsonUpdate name="Lights"/>,
    document.getElementById('lights'));

React.render(
    <JsonUpdate name="Buttons"/>,
    document.getElementById('buttons'));






