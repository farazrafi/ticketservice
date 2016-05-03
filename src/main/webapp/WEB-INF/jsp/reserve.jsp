<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<title>Ticket Service - Reservation</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
	});	
</script>
<body>

<h1>Reserve Seats</h1>
	<form name="reserveForm" id="reserveForm" method="post" action='#springUrl("/walmart/reserve")'>
		The following seats are held for you for 1 minute:
		<table border=1>
			<tr>
				<td>S.no</td>
				<td>Seat Number</td>
				<td>Level</td>
			</tr>
		#foreach($seat in $seatHoldDTO.selectedSeats.keySet())
			<tr>
				<td>$velocityCount</td>
				<td>$seat.seatNumber</td>
				<td>$seat.levelId.levelName</td>
			</tr>
		#end
		</table><br/>
		<table>
			<tr>
				<td><label>Email address: </label></td>
				<td>
					#springBind("searchDTO.customerEmail")
					<input type="text" name="$!{status.expression}"} id="$!{status.expression}" value="$!{status.value}" size=15/>
				</td>				
			</tr>			
		</table>				
		<br/>
		<input type="submit" value="Confirm Reservation"/>
	</form>
	#if($!confirmationCode)
	<hr/>
	<font color="blue">Thank you for reservation. The above tickets have been reserved under your name. Your confirmation code is: $!confirmationCode</font>
	#end
</body>
</html>