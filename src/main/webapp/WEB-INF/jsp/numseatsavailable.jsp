<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<title>Ticket Service - Seat Availability</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
	});	
</script>
<body>
<h1>Find Seats</h1>
	<form name="numSeatsAvailableForm" id="numSeatsAvailableForm" method="post" action='#springUrl("/walmart/numseatsavailable")'>
		<label>Search by Venue:</label><br/>	
		#springBind("searchDTO.venueLevel")
		<select name="$!{status.expression}" size="4" multiple="multiple" tabindex="1">
			<option value="1" #if($!status.value eq '1') selected #end >Orchestra</option>
			<option value="2" #if($!status.value eq '2') selected #end >Main</option>
			<option value="3" #if($!status.value eq '3') selected #end >Balcony 1</option>
			<option value="4" #if($!status.value eq '4') selected #end >Balcony 2</option>
		</select><br/>
		<input type="submit" value="Search"/>
	</form>
	<hr/>
	Search result: $!result
</body>
</html>