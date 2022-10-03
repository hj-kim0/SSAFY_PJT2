import React, { Component } from 'react';
import CanvasJSReact from '../../assets/canvasjs.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class PieChart extends Component {

	render() {
		const sum = 5;
		
		const datas = this.props.data;

		console.log(datas);

		const datass = 
		[
			{
				"accordClassName": "herbal",
				"y": 1
			},
			{
				"accordClassName": "animalic",
				"y": 1
			},
			{
				"accordClassName": "floral",
				"y": 1
			},
			{
				"accordClassName": "synthetic",
				"y": 1
			}
		];
		

		const options = {
			exportEnabled: true,
			animationEnabled: true,
			data: [{
				type: "pie",
				startAngle: 75,
				toolTipContent: "<b>{accordClassName}</b>: {y}%",
				showInLegend: "true",
				legendText: "{accordClassName}",
				indexLabelFontSize: 16,
				indexLabel: "{accordClassName} - {y}%",
				dataPoints: datas
			}]
		}

		// [
		// 	{ y: datas[0].accordClassCount, label: datas[0].accordClassName },
		// 	{ y: 1, label: "Organic Search" },
		// 	{ y: 1, label: "Paid Search" },
		// 	{ y: 1, label: "Referral" },
		// 	{ y: 1, label: "Social" }
		// ]
		
		return (
		<div>
			<CanvasJSChart options = {options} 
				/* onRef={ref => this.chart = ref} */
			/>
			{/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
		</div>
		);
	}
}

export default PieChart;