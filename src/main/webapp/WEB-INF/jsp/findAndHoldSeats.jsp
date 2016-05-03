<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<title>Ticket Service - Find & Hold</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchBtn").click(function(){
			$("#userAction").val('search');
			$("#findAndHoldSeatsForm").submit();
		});
		$("#holdBtn").click(function(){
			$("#userAction").val('hold');
			$("#findAndHoldSeatsForm").submit();
		});
	});	
</script>
<body>
<h1>Find Seats</h1>
	<form name="findAndHoldSeatsForm" id="findAndHoldSeatsForm" method="post" action='#springUrl("/walmart/findAndHoldSeats")'>
		<label>Find and Hold Seats:</label><br/><br/>
		<table>
			<tr>
				<td><label>Min Level: </label></td>
				<td>
					#springBind("searchDTO.minLevel")
					<select name="$!{status.expression}" size="4" tabindex="1">
						<option value="1" #if($!status.value eq '1') selected #end >Orchestra</option>
						<option value="2" #if($!status.value eq '2') selected #end >Main</option>
						<option value="3" #if($!status.value eq '3') selected #end >Balcony 1</option>
						<option value="4" #if($!status.value eq '4') selected #end >Balcony 2</option>
					</select>
				</td>				
			</tr>
			<tr>
				<td><label>Max Level: </label></td>
				<td>
					#springBind("searchDTO.maxLevel")
					<select name="$!{status.expression}" size="4" tabindex="1">
						<option value="1" #if($!status.value eq '1') selected #end >Orchestra</option>
						<option value="2" #if($!status.value eq '2') selected #end >Main</option>
						<option value="3" #if($!status.value eq '3') selected #end >Balcony 1</option>
						<option value="4" #if($!status.value eq '4') selected #end >Balcony 2</option>
					</select>
				</td>				
			</tr>
			<tr>
				<td><label># of seats:</label></td>
				<td>
					#springBind("searchDTO.numSeats")
					<input type="text" name="$!{status.expression}"} id="$!{status.expression}" value="$!{status.value}" size=4/>
				</td>				
			</tr>			
			<tr>
				<td><label>Email: </label></td>
				<td>
					#springBind("searchDTO.customerEmail")
					<input type="text" name="$!{status.expression}"} id="$!{status.expression}" value="$!{status.value}" size=20/>
				</td>				
			</tr>			
		</table>		
		<input type="button" id="searchBtn" value="Search"/>
		<input type="button" id="holdBtn" value="Hold"/>
		<input type="hidden" name="userAction" id="userAction" value="" />
	</form>
	<hr/>
	Search result: $!totalSeatsAvailable
	<hr/>
	#if($!searchDTO.numSeats gt $!totalSeatsAvailable)
		<font color="red">Sorry, there are not enough seats available for your selection.</font>
	#else
		There are enough seats available for your selection.
		<font color="blue"><input type="button" onclick="window.open('reserve');" value="Reserve"/></font>
		#if($!seatHold.seatHoldId)
		<br/><font color="blue">Success! Your SeatHold ID is $!seatHold.seatHoldId.</font>
		#end
	#end
	<br/>
</body>
</html>