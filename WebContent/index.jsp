<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/ajax.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script>
  function datepick() {
    $( "#datepicker" ).datepicker();
  }
  </script>
  
  </head>
<body>

	



<div id="heading1">
	<div id="divlogoimage">
		<img id="logoimage" alt="image" src="images/bcare_logo_150.gif">
	</div>
</div>
<div id="heading2">

</div>
<div id="center">
	<div id="leftPanel"></div>
	<div id="rightPanel">
	<form method="post">
		<% for(int i=0;i<18;i++){ 
				String bgclr ;
				if(i%2 == 0){
					bgclr = "#F1F0F1";
				}else{
					bgclr = "white";
				}
		
		%>
				<div class="lines" style="background-color: <%=bgclr %>">
					<input class="check1" type="checkbox">
					<input class="text1" type="text" disabled>
					<input class="button1" type="submit" disabled="disabled" value="Btn <%=i%>" style="width: 60px;" >
					<script type="text/javascript">
						document.getElementsByClassName('check1')[<%=i %>].onchange = function(){					
							var text = document.getElementsByClassName('text1')[<%=i %>];
							var button = document.getElementsByClassName('button1')[<%=i %>];
							if(document.getElementsByClassName('check1')[<%=i %>].checked){						
								text.disabled = false ;
								button.disabled = false ;
							}else{						
								text.disabled = true ;
								button.disabled = true ;
							}
						}
					</script>			
			</div>
		<%} %>
		<input type="text" id="datepicker" onclick="datepick()">
		<input type="button" onclick="makeRequest()">
	
		
		
	
	</form>
	<div id="hello"> fgjioj</div>
	</div>
	
</div>
<div id="bottom1"></div>
<div id="bottom2">
	<a href="PatientCRUD.jsp" style="text-decoration: none; color: yellow;">Patient Search</a>
</div>
</body>
</html>
