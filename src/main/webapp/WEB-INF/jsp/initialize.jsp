<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<title>Ticket Service - Initialization</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#initializeBtn").click(function(){
			$("#testDataForm").submit();
		});
	});	
</script>
<body>

<h1>Setup Test Data</h1>

<form name="initializeDataForm" id="initializeDataForm" method="post" action='#springUrl("/walmart/initialize.do")'>
	<label>Click to initialize: </label><input type="submit" value="Setup Test Data"/>
</form>
<h2>Customers</h2>
<table>
	<tr>
		<td width="150">First Name</td>
		<td width="150">Last Name</td>
		<td width="150">Email</td>
	</tr>
	#foreach($customer in $customers)
		<tr>
			<td>${customer.firstName}</td>
			<td>${customer.lastName}</td>
			<td>${customer.email}</td>
		</tr>
	#end
</table>
Total customers count: $!customers.size()
</body>
</html>