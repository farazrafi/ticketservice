<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<title>Ticket Service - Test Data</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
	});	
</script>
<body>
<h2>Customers</h2>
<table border=1>
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
	#if($!customers.size() eq 0)
		<tr><td colspan="3">None</tr></td>		
	#end
</table>
<br/>Total customers: $!customers.size()
<hr/>
<h2>Levels</h2>
<table border=1>
	<tr>
		<td width="150">Name</td>
		<td width="150">Price</td>
		<td width="150"># of rows</td>
		<td width="150"># of seats/row</td>
	</tr>
	#foreach($level in $levels)
		<tr>
			<td>${level.levelName}</td>
			<td>${level.price}</td>
			<td>${level.numberOfRows}</td>
			<td>${level.seatsInRow}</td>
		</tr>
	#end
	#if($!levels.size() eq 0)
		<tr><td colspan="4">None</tr></td>		
	#end
</table>
<br/>Total levels: $!levels.size()
<hr/>
<h2>Reservations</h2>
<table border=1>
	<tr>
		<td width="150">Customer</td>
		<td width="150">Time of reservation</td>
		<td width="150">Confirmation Code</td>
	</tr>
	#foreach($reservation in $reservations)
		<tr>
			<td>${reservation.customerId.email}</td>
			<td>${reservation.reservationTimeStamp}</td>
			<td>${reservation.confirmationCode}</td>
		</tr>
	#end
	#if($!reservations.size() eq 0)
		<tr><td colspan="4">None</tr></td>		
	#end
</table>
<br/>Total reservations: $!reservations.size()
<hr/>
<h2>Seats</h2>
<table border=1>
	<tr>
		<td width="150">Number</td>
		<td width="150">Level</td>
		<td width="150">Held</td>
	</tr>
	#foreach($seat in $seats)
		<tr>
			<td>${seat.seatNumber}</td>
			<td>${seat.levelId.levelName}</td>
			<td>${seat.held}</td>
		</tr>
	#end
	#if($!seats.size() eq 0)
		<tr><td colspan="4">None</tr></td>		
	#end
</table>
<br/>Total seats: $!seats.size()
<hr/>
<h2>Seat Hold</h2>
<table border=1>
	<tr>
		<td width="150">SeatHold ID</td>
		<td width="150">Hold</td>
		<td width="150">Reservation</td>
	</tr>
	#foreach($seatHold in $seatHolds)
		<tr>
			<td>$!seatHold.seatHoldId</td>
			<td>$!seatHold.hold</td>
			<td>${seatHold.reservationId.reservationTimeStamp}</td>
		</tr>
	#end
	#if($!seatHolds.size() eq 0)
		<tr><td colspan="4">None</tr></td>		
	#end
</table>
<br/>Total seat holds: $!seatHolds.size()
</body>
</html>