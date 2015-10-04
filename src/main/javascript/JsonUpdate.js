var React = require('react');
var RxDom = require('rx-dom');
var Editor = require('react-json');


class JsonUpdate extends React.Component {

    constructor(props) {
        super(props);
        this.componentDidMount = this.componentDidMount.bind(this);
        this.postChange = this.postChange.bind(this);

        this.state = {
            value: {},
            settings: null
        };
    }

    componentDidMount() {
        Rx.DOM.ajax("/status/" + this.props.name)
            .subscribe(
            (data) => {
                var info = JSON.parse(data.response);
                this.setState({
                    value: info.data,
                    settings: info.settings
                });

            },
            (error)  => {
                console.log(error);
            }
        );
    }

    postChange(value) {
        var str = JSON.stringify(value);
        Rx.DOM.ajax({url: "/apply/" + this.props.name, body: str, method: "POST"})
            .subscribe(
            (data) => {
            },
            (error) => {
                console.log(error);
            }
        );
    }

    render() {
        if (this.state.settings == null)
            return <div />;
        else
            return (
                <Editor value={this.state.value} settings={this.state.settings} onChange={this.postChange}></Editor>);
    }
}

module.exports = JsonUpdate;
