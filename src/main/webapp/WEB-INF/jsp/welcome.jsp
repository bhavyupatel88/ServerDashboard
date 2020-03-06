<%@page session="false"%>
<html>
<head>
<title>Welcome</title>

<script src="js/Chart.min.js"></script>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="css/Chart.min.css"></script>
</head>
<style>
    .chartTitle {
        text-align: center;
    	margin-bottom: 35px;
    }
    .pad50{
    	padding: 50px !important;
    }
</style>
<body>

<table>
	<tbody>
	</tbody>
</table>

	<script>
		

		$.ajax({
			url : "http://localhost:8081/getData",
			success : function(result) {
				console.log('DAta =>', result);
				var col = 0;
				var row = 0;
				$.each(result, function(chart, value) {
					console.log(chart, value);

					let id = 'row_'+row;
					if(col === 0){
						$('tbody').append("<tr id='"+id+"'><td  class='pad50'><h2 class='chartTitle'>"+chart+"<h2><canvas id='"+chart+"' width='500px' height='500px'></canvas></td></tr>");
						col++;
					}
					else if(col === 1){
						row++;
						col = 0
						$('#'+id).append("<td class='pad50'><h2 class='chartTitle'>"+chart+"<h2><canvas id='"+chart+"' width='500px' height='500px'></canvas></td>");
					}
					
					var ctx = document.getElementById(chart);
					var myChart = new Chart(ctx, {
						type : 'pie',
						data : {
							labels : value.labels,
							datasets : [ {
								label : value.datasets[0].label,
								data : value.datasets[0].data,
								backgroundColor : [ 'rgba(255, 99, 132, 0.2)',//
								'rgba(54, 162, 235, 0.2)',
										'rgba(255, 206, 86, 0.2)',
										'rgba(75, 192, 192, 0.2)' ],
								borderColor : [ 'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)' ],
								borderWidth : 1
							} ]
						},
						options : {
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ]
							}
						}
					});

				});
			}
		});
	</script>
</body>
</html>