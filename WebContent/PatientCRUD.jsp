<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.json.simple.parser.ParseException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.FileReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient Search</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="css/dataTables.css" rel="stylesheet" type="text/css" media="screen" />
<script src="js/ajax.js"></script>
<script src="js/dataTablesMin.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>



<script>

$(document).ready(function() {
	$("#example_wrapper").bind("DOMSubtreeModified",function(){				
		
			$buttonController = "SESSION";
						
			$.post("PatientCRUDServlet", {buttonController:$buttonController}, function(msg) {				
				$("#transactionLine").html("");
				$("#transactionLine").html("<div style=\"position:absolute;height: inherit;width: 25%;margin-left: 75%;\">"+msg+ " </div>");
			});		
		
	});
});

var complete = false;

	function getXMLHttpRequest() {
	  var xmlHttpReq;
	  // to create XMLHttpRequest object in non-Microsoft browsers
	  if (window.XMLHttpRequest) {
	    xmlHttpReq = new XMLHttpRequest();
	  } else if (window.ActiveXObject) {
	    try {
	      //to create XMLHttpRequest object in later versions of Internet Explorer
	      xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	    } catch (exp1) {
	      try {
	        //to create XMLHttpRequest object in later versions of Internet Explorer
	        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	      } catch (exp2) {
	        //xmlHttpReq = false;
	        alert("Exception in getXMLHttpRequest()!");
	      }
	    }
	  }
	  return xmlHttpReq;
	}
	 
	/*
	* AJAX call starts with this function
	*/
	function makeRequest() {	
		//var r = $.Deferred();
	  var xmlHttpRequest = getXMLHttpRequest();
	  //alert ("xmlHttpRequest=" + xmlHttpRequest);
	  xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
	  //alert("xhr");
	  xmlHttpRequest.open("POST", "PatientSearchServlet?country="+document.getElementById("country").value, true);  
	  //alert("open");
	  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  //alert("inside makeRequest()!");
	  xmlHttpRequest.send(null);
	 // setTimeout(function () {
		    // and call `resolve` on the deferred object, once you're done
		//    r.resolve();
		//  }, 2500);
	 // return r;
	  //alert("sent!");
	}
	
	 
	/*
	* Returns a function that waits for the state change in XMLHttpRequest
	*/
	function getReadyStateHandler(xmlHttpRequest) {
	  // an anonynous function returned
	  // it listens to the XMLHttpRequest instance
	  return function() {
	    if (xmlHttpRequest.readyState == 4) {
	      if (xmlHttpRequest.status == 200) {
	        //document.getElementById("hello").innerHTML = 
	        	var JasonCountryObject = eval('('+xmlHttpRequest.responseText+')');
	        	//readTextFile("file:///E:/a.txt");
	        	//alert(xmlHttpRequest.responseText);
	        	var dropdown = document.getElementById("city");
	        	dropdown.innerHTML="";
	        	var city = JasonCountryObject.country.city;
	        	var i = 0;
	        	while(i < city.length){	        		
	        		dropdown.options[dropdown.options.length] = new Option(city[i].name, city[i].name);	 
	        		i++;
	        	}	        	
	        	//complete = true;
	        	//alert("True in function");
	        	
	        	      	
	        
	      } else {
	        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
	      }
	    }
	  };
	}
	
	function readTextFile(file)
	{
	    var rawFile = new XMLHttpRequest();
	    rawFile.open("GET", file, false);
	    rawFile.onreadystatechange = function ()
	    {
	        if(rawFile.readyState === 4)
	        {
	            if(rawFile.status === 200 || rawFile.status == 0)
	            {
	                var allText = rawFile.responseText;
	                alert(allText);
	            }
	        }
	    }
	    rawFile.send(null);
	}
	/////////////////////////////////////////////////////////////// Add Patient Start///////////////////////////////////////////////////////////////////////
	
	function makeAddRequest() {	
		//alert("Request");
		  var xmlHttpRequest = getXMLHttpRequest();
		  //alert ("xmlHttpRequest=" + xmlHttpRequest);
		  xmlHttpRequest.onreadystatechange = getAddPatientReadyStateHandler(xmlHttpRequest);
		 // alert("xhr");
		  var buttonController = "add";
		  var firstname = document.getElementById("firstName").value;
		  var lastName = document.getElementById("lastName").value;
		  var birthDate = escape(document.getElementById("bDate").value);
		  var ecountry = document.getElementById("country");
		  //alert("before select");
		  //alert("DAte   "+birthDate);
		  var country = ecountry.options[ecountry.selectedIndex].text;
		  var ecity = document.getElementById("city");		 
		  var city = ecity.options[ecity.selectedIndex].text;
		  //alert("asdsd");
		  //xmlHttpRequest.open("POST", "AddPatientServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&city="+city, true);
		  xmlHttpRequest.open("POST", "PatientCRUDServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&city="+city+"&buttonController="+buttonController, true);
		  //alert("open");
		  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  //alert("inside makeRequest()!");
		  xmlHttpRequest.send(null);
		  //alert("sent!");
		}
	
	function getAddPatientReadyStateHandler(xmlHttpRequest) {
		  // an anonynous function returned
		  // it listens to the XMLHttpRequest instance
		  return function() {
		    if (xmlHttpRequest.readyState == 4) {
		      if (xmlHttpRequest.status == 200) {	
		    	  	//alert("response");
		        	var msg = xmlHttpRequest.responseText;
		        	alert(msg);	
		        	makeSearchAllRequest();
		        
		      } else {
		        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
		      }
		    }
		  };
		}
	
	//////////////////////////////////////////////Jquery////////////////////////////////////////////////////////////////////////////////////////////////////////
	$(document).ready(function() {
		$("#addBtn").click(function(){			
			$buttonController = "add";
			$firstName = $("#firstName").val();
			$lastName = $("#lastName").val();
			$birthDate = $("#bDate").val();			
			$country = $("#country option:selected").text();//$ecountry.options[ecountry.selectedIndex].text;
			//$ecity = document.getElementById("city");		 
			$city = $("#city option:selected").text();//$ecity.options[ecity.selectedIndex].text;
			alert($firstName);
			alert($city);
			$.post("PatientCRUDServlet", {firstName:$firstName,lastName:$lastName,birthDate:$birthDate,country:$country,city:$city,buttonController:$buttonController}, function(msg) {
				$("#dialog-message-p").html(" <span class=\"ui-icon ui-icon-circle-check\" style=\"float:left; margin:0 7px 50px 0;\"></span>"+msg); 
				$( "#dialog-message" ).dialog({
				      modal: true,				      
				      buttons: {
				        Ok: function() {
				          $( this ).dialog( "close" );
				        }
				      }
				    });
				searchAll();
				var val = "<%= request.getSession().getAttribute("transactiontype")%>";
				//alert(val);
				//$("#transactionLine").html("");
				//$("#transactionLine").html("<div style=\"position:absolute;height: inherit;width: 25%;margin-left: 75%;\">"+val+ " </div>");
				
			});
		});
	});
	//////////////////////////////////////////////////////////////Add Patient End/////////////////////////////////////////////////////////////////////////////////

		
	//////////////////////////////////////////////////////////////Search Patient Start/////////////////////////////////////////////////////////////////////////////////
	
	function makeSearchRequest() {	
		//alert("Request");
		  var xmlHttpRequest = getXMLHttpRequest();
		  //alert ("xmlHttpRequest=" + xmlHttpRequest);
		  xmlHttpRequest.onreadystatechange = getSearchPatientReadyStateHandler(xmlHttpRequest);
		 // alert("xhr");
		  var buttonController = "search";
		  var firstname = document.getElementById("firstName").value;
		  var lastName = document.getElementById("lastName").value;
		  var birthDate = escape(document.getElementById("bDate").value);
		  var ecountry = document.getElementById("country");
		  //alert("before select");
		  //alert("DAte   "+birthDate);
		  var country = ecountry.options[ecountry.selectedIndex].text;
		  //var ecity = document.getElementById("city");		 
		  //var city = ecity.options[ecity.selectedIndex].text;
		  //alert("asdsd");
		  //xmlHttpRequest.open("POST", "SearchPatientServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country, true);
		  xmlHttpRequest.open("POST", "PatientCRUDServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&buttonController="+buttonController, true);
		  //alert("open");
		  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  //alert("inside makeRequest()!");
		  xmlHttpRequest.send(null);
		  //alert("sent!");
		}
	
	function getSearchPatientReadyStateHandler(xmlHttpRequest) {
		  // an anonynous function returned
		  // it listens to the XMLHttpRequest instance
		  return function() {
		    if (xmlHttpRequest.readyState == 4) {
		      if (xmlHttpRequest.status == 200) {	
		    	  	//alert("response");
		        	var msg = xmlHttpRequest.responseText;
		        	//alert(msg);
		        	
		        	//alert("not null");
		        		document.getElementById("example_wrapper").innerHTML= msg;
		        		
		      } else {
		        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
		      }
		    }
		  };
		}
	
//////////////////////////////////////////////Jquery////////////////////////////////////////////////////////////////////////////////////////////////////////
	$(document).ready(function() {
		$("#searchBtn").click(function(){			
			$buttonController = "search";
			$firstName =$("#firstName").val();
			$lastName = $("#lastName").val();
			$birthDate = $("#bDate").val();				
			$country = $("#country option:selected").text();
			$city = $("#city option:selected").text();
			
			$.post("PatientCRUDServlet", {firstName:$firstName,lastName:$lastName,birthDate:$birthDate,country:$country,city:$city,buttonController:$buttonController}, function(msg) {
				$("#example_wrapper").html(msg);
				//document.getElementById("example_wrapper").innerHTML= msg;
				var val = "<%= request.getSession().getAttribute("transactiontype")%>";
				//alert(val);
				//$("#transactionLine").html("");
				//$("#transactionLine").html("<div style=\"position:absolute;height: inherit;width: 25%;margin-left: 75%;\">"+val+ " </div>");
			});
		});
	});
	//////////////////////////////////////////////////////////////Search Patient End/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////Search All Patient Start/////////////////////////////////////////////////////////////////////////////////
	
	function makeSearchAllRequest() {	
		//alert("Request");
		  var xmlHttpRequest = getXMLHttpRequest();
		  //alert ("xmlHttpRequest=" + xmlHttpRequest);
		  xmlHttpRequest.onreadystatechange = getSearchAllPatientReadyStateHandler(xmlHttpRequest);
		 // alert("xhr");
		  var buttonController = "search";
		  var firstname = "";
		  var lastName = "";
		  var birthDate = "";
		  var country = "Select Country";
		  //var ecity = document.getElementById("city");		 
		  //var city = ecity.options[ecity.selectedIndex].text;
		  //alert("asdsd");
		  //xmlHttpRequest.open("POST", "SearchPatientServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country, true);
		  xmlHttpRequest.open("POST", "PatientCRUDServlet?firstName="+firstname+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&buttonController="+buttonController, true);
		  //alert("open");
		  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  //alert("inside makeRequest()!");
		  xmlHttpRequest.send(null);
		  //alert("sent!");
		}
	
	function getSearchAllPatientReadyStateHandler(xmlHttpRequest) {
		  // an anonynous function returned
		  // it listens to the XMLHttpRequest instance
		  return function() {
		    if (xmlHttpRequest.readyState == 4) {
		      if (xmlHttpRequest.status == 200) {	
		    	  	//alert("response");
		        	var msg = xmlHttpRequest.responseText;
		        	//alert(msg);
		        	
		        	//alert("not null");
		        		document.getElementById("example_wrapper").innerHTML= msg;
		        		
		      } else {
		        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
		      }
		    }
		  };
		}	
	
	function searchAll(){		
				
				$buttonController = "search";
				$firstName ="";
				$lastName = "";
				$birthDate = "";			
				$country = "Select Country";
				$city = "Select City";
				
				$.post("PatientCRUDServlet", {firstName:$firstName,lastName:$lastName,birthDate:$birthDate,country:$country,city:$city,buttonController:$buttonController}, function(msg) {
					$("#example_wrapper").html(msg);
					
				});
			
	}

	
	//////////////////////////////////////////////////////////////Search All Patient End/////////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////Select Patient Start/////////////////////////////////////////////////////////////////////////////////
	function run(){
		//alert("run");
	    document.getElementById('example').onclick = function(event){
	        event = event || window.event; //for IE8 backward compatibility	        
	        var target = event.target || event.srcElement; //for IE8 backward compatibility	        
	        while (target && target.nodeName != 'TR') {
	            target = target.parentElement;	           
	        }
	        var cells = target.cells; //cells collection	        
	        //var cells = target.getElementsByTagName('td'); //alternative
	       // if (!cells.length || target.parentNode.nodeName == 'TBODY') { // if clicked row is within thead	        
	        //	return;
	      //  }
	        function selectItemByValue(elmnt, value){
				//alert("selectItemByValue");
	            for(var i=0; i < elmnt.options.length; i++)
	            {
	              if(elmnt.options[i].value == value){
	                elmnt.selectedIndex = i;
	              	//alert(elmnt.options[i].value);
	              }
	            }
	          }
	        
	        var f0 = document.getElementById("patientId");
	        var f1 = document.getElementById('firstName');
	       	var f2 = document.getElementById('lastName');
	        var f3 = document.getElementById('bDate');
	        var f4 = document.getElementById('country');
	        var f5 = document.getElementById('city');
	       // var f6 = document.getElementById('diff');
	       	f0.value = cells[0].innerHTML;
	        f1.value = cells[1].innerHTML;
	        f2.value = cells[2].innerHTML;
	        f3.value = cells[3].innerHTML;
	        f4.value = cells[5].innerHTML;
	        //makeRequest().done(selectItemByValue(f5,cells[6].innerHTML));	
	       // $.when(makeRequest(),getReadyStateHandler()).done(function(a1, a2){
	       // 	alert("1");
	      //  	selectItemByValue(f5,cells[6].innerHTML);
	       // 	alert("2");
			//});
	        //while(complete == false){
	        	
	        //}
	        makeRequest();
	        setTimeout(function(){	        	
		        selectItemByValue(f5,cells[6].innerHTML);
		        //complete = false;
			}, 500);
	        
	        //f5.value = cells[6].innerHTML;
	        //f6.value = cells[5].innerHTML;
	       //alert(cells[6].innerHTML);
	       //alert(cells[0].innerHTML);
	       //alert(document.getElementById("patientId").value);
	    }
	}
	
	//////////////////////////////////////////////////////////////Select Patient Start/////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////Delete Patient Start/////////////////////////////////////////////////////////////////////////////////
	
		$(document).ready(function() {
		$("#deleteBtn").click(function(){	
			$( "#dialog-delete-confirm" ).dialog({
			      resizable: false,
			      height:200,
			      modal: true,
			      buttons: {
			        "Delete": function() {
			        	$buttonController = "delete";
						$firstName = $("#firstName").val();
						$lastName = $("#lastName").val();
						$birthDate = $("#bDate").val();					
						$patientId = $("#patientId").val();
						
						$.post("PatientCRUDServlet", {firstName:$firstName,lastName:$lastName,birthDate:$birthDate,patientId:$patientId,buttonController:$buttonController}, function(msg) {
							$("#dialog-message-p").html(" <span class=\"ui-icon ui-icon-circle-check\" style=\"float:left; margin:0 7px 50px 0;\"></span>"+msg); 
							$( "#dialog-message" ).dialog({
							      modal: true,
							      buttons: {
							        Ok: function() {
							          $( this ).dialog( "close" );
							        }
							      }
							    });
							searchAll();
							var val = "<%= request.getSession().getAttribute("transactiontype")%>";
							//alert(val);
							//$("#transactionLine").html("");
							//$("#transactionLine").html("<div style=\"position:absolute;height: inherit;width: 25%;margin-left: 75%;\">"+val+ " </div>");
						});
			          	$( this ).dialog( "close" );
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
			
			
			
		});
	});
	
	function ConfirmationDelete() {
    	var x = confirm("Are you sure to delete?");
    	if(x){
    		makeDeleteRequest();
    	}
	}
	
	function makeDeleteRequest() {	
		//alert("makeDeleteRequest");
		  var xmlHttpRequest = getXMLHttpRequest();
		  //alert ("xmlHttpRequest=" + xmlHttpRequest);
		  xmlHttpRequest.onreadystatechange = getDeletePatientReadyStateHandler(xmlHttpRequest);
		 // alert("xhr");
		  var buttonController = "delete";
		  var patientId = document.getElementById("patientId").value;
		  var firstname = document.getElementById("firstName").value;
		  var lastName = document.getElementById("lastName").value;
		  var birthDate = escape(document.getElementById("bDate").value);
		  //var ecountry = document.getElementById("country");
		  //alert("before select");
		  //alert("DAte   "+birthDate);
		 // var country = ecountry.options[ecountry.selectedIndex].text;
		  //var ecity = document.getElementById("city");		 
		 // var city = ecity.options[ecity.selectedIndex].text;
		  //alert("asdsd");
		 // xmlHttpRequest.open("POST", "DeletePatientServlet?firstName="+firstname+"&patientId="+patientId+"&lastName="+lastName+"&birthDate="+birthDate, true);
		  xmlHttpRequest.open("POST", "PatientCRUDServlet?firstName="+firstname+"&patientId="+patientId+"&lastName="+lastName+"&birthDate="+birthDate+"&buttonController="+buttonController, true);
		  //alert("open");
		  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  //alert("inside makeRequest()!");
		  xmlHttpRequest.send(null);
		  //alert("sent!");
		}
	
	function getDeletePatientReadyStateHandler(xmlHttpRequest) {
		  // an anonynous function returned
		  // it listens to the XMLHttpRequest instance
		  return function() {
		    if (xmlHttpRequest.readyState == 4) {
		      if (xmlHttpRequest.status == 200) {	
		    	  	//alert("response");
		        	var msg = xmlHttpRequest.responseText;
		        	alert(msg);		        	
		        	makeSearchAllRequest();
		        		//document.getElementById("example_wrapper").innerHTML= msg;
		        		
		      } else {
		        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
		      }
		    }
		  };
		}
	
	
	//////////////////////////////////////////////////////////////Delete Patient End/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////Update Patient Start/////////////////////////////////////////////////////////////////////////////////
	
	$(document).ready(function() {
		$("#updateBtn").click(function(){	
			
			    $( "#dialog-confirm" ).dialog({
			      resizable: false,
			      height:200,
			      modal: true,
			      buttons: {
			        "Update": function() {
			        	$buttonController = "update";
						$firstName = $("#firstName").val();
						$lastName = $("#lastName").val();
						$birthDate = $("#bDate").val();			
						$country = $("#country option:selected").text();
						$city = $("#city option:selected").text();
						$patientId = $("#patientId").val();
						
						$.post("PatientCRUDServlet", {firstName:$firstName,lastName:$lastName,birthDate:$birthDate,country:$country,city:$city,patientId:$patientId,buttonController:$buttonController}, function(msg) {
								$("#dialog-message-p").html(" <span class=\"ui-icon ui-icon-circle-check\" style=\"float:left; margin:0 7px 50px 0;\"></span>"+msg); 
							    $( "#dialog-message" ).dialog({
							      modal: true,
							      buttons: {
							        Ok: function() {
							          $( this ).dialog( "close" );
							        }
							      }
							    });
							 
							searchAll();
							var val = "<%= request.getSession().getAttribute("transactiontype")%>";
							//alert(val);
							//$("#transactionLine").html("");
							//$("#transactionLine").html("<div style=\"position:absolute;height: inherit;width: 25%;margin-left: 75%;\">"+val+ " </div>");
						});
			          $( this ).dialog( "close" );
			        },
			        Cancel: function() {
			          $( this ).dialog( "close" );
			        }
			      }
			    });
			  
					
			
			
		});
	});
	
	function ConfirmationUpdate() {
    	var x = confirm("Are you sure to Update this?");
    	if(x){
    		makeUpdateRequest();
    	}
	}
	
	function makeUpdateRequest() {	
		//alert("makeDeleteRequest");
		  var xmlHttpRequest = getXMLHttpRequest();
		  //alert ("xmlHttpRequest=" + xmlHttpRequest);
		  xmlHttpRequest.onreadystatechange = getUpdatePatientReadyStateHandler(xmlHttpRequest);
		 // alert("xhr");
		  var buttonController = "update";
		  var patientId = document.getElementById("patientId").value;
		  var firstname = document.getElementById("firstName").value;
		  var lastName = document.getElementById("lastName").value;
		  var birthDate = escape(document.getElementById("bDate").value);
		  var ecountry = document.getElementById("country");
		  //alert("before select");
		  //alert("DAte   "+birthDate);
		  var country = ecountry.options[ecountry.selectedIndex].text;
		  var ecity = document.getElementById("city");		 
		  var city = ecity.options[ecity.selectedIndex].text;
		  //alert("asdsd");
		  //xmlHttpRequest.open("POST", "UpdatePatientServlet?firstName="+firstname+"&patientId="+patientId+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&city="+city, true);
		  xmlHttpRequest.open("POST", "PatientCRUDServlet?firstName="+firstname+"&patientId="+patientId+"&lastName="+lastName+"&birthDate="+birthDate+"&country="+country+"&city="+city+"&buttonController="+buttonController, true);
		  //alert("open");
		  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		  //alert("inside makeRequest()!");
		  xmlHttpRequest.send(null);
		  //alert("sent!");
		}
	
	function getUpdatePatientReadyStateHandler(xmlHttpRequest) {
		  // an anonynous function returned
		  // it listens to the XMLHttpRequest instance
		  return function() {
		    if (xmlHttpRequest.readyState == 4) {
		      if (xmlHttpRequest.status == 200) {	
		    	  	//alert("response");
		        	var msg = xmlHttpRequest.responseText;
		        	alert(msg);		        	
		        	makeSearchAllRequest();
		        		//document.getElementById("example_wrapper").innerHTML= msg;
		        		
		      } else {
		        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
		      }
		    }
		  };
		}
	
	
	//////////////////////////////////////////////////////////////Update Patient End/////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////Clear all/////////////////////////////////////////////////////////////////////////////////
	function ClearAll(){
		 document.getElementById("firstName").value = "";
		 document.getElementById("lastName").value = "";
		 document.getElementById("bDate").value = "";
		 //var ecountry = document.getElementById("country");
		 //ecountry.options[ecountry.selectedIndex].text = "Select Country";
		 document.getElementById("country").value = "Select Country";
		 document.getElementById("city").value = "Select City";
		 //var ecity = document.getElementById("city");		 
		 //var city = ecity.options[ecity.selectedIndex].text = "Select City";
	}
//////////////////////////////////////////////////////////////Clear all End/////////////////////////////////////////////////////////////////////////////////
</script>

<script>
	$(document).ready(function() {
	    var table = $('#example').DataTable();
	 
	    $('#example tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	    } );
	 
	    $('#button').click( function () {
	        alert( table.rows('.selected').data().length +' row(s) selected' );
	    } );
	} );
	</script>		

<!-- script>
		$(document).ready(function(){
			console.log( "document loaded" );
			$.getJSON("all_countries_cities_in_the_world.json", function(data) {
                //alert("My data: " + data["mydata"]);                
				$.each(data["name"], function(val, text) {
					$('#country').append(
				        $('<option></option>').val(val).html(text)
				    );
				});
                
            });
		});
       
    </script-->

</head>
<body>

<SCRIPT> 
 
function myDateValidation() {
 
    var lre = /^\s*/;
    var datemsg = "";
    
    var inputDate = document.getElementById("bDate").value;
    inputDate = inputDate.replace(lre, "");
    document.getElementById("bDate").value = inputDate;
    datemsg = isValidDate(inputDate);
        if (datemsg != "") {        	
            alert(datemsg);      
            document.getElementById("bDate").value="";
            return;
        }
        else {
            //Process your form
        }
 
}
 
function isValidDate(dateStr) {
 
    
    var msg = "";
    // Checks for the following valid date formats:
    // MM/DD/YY   MM/DD/YYYY   MM-DD-YY   MM-DD-YYYY
    // Also separates date into month, day, and year variables
 
    // To require a 2 & 4 digit year entry, use this line instead:
    //var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
    // To require a 4 digit year entry, use this line instead:
    var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/;
 
    var matchArray = dateStr.match(datePat); // is the format ok?
    if (matchArray == null) {
        msg = "Date is not in a valid format.";        
        return msg;
    }
 
    day = matchArray[3]; // parse date into variables
    month = matchArray[1];
    year = matchArray[4];
 
    
    if (month < 1 || month > 12) { // check month range
        msg = "Month must be between 1 and 12.";
        return msg;
    }
 
    if (day < 1 || day > 31) {
        msg = "Day must be between 1 and 31.";
        return msg;
    }
 
    if ((month==4 || month==6 || month==9 || month==11) && day==31) {
    	var monthName;
    	if(month==4){
    		monthName="April";
    	}else if(month==6){
    		monthName="June";
    	}else if(month==9){
    		monthName="September";
    	}else if(month==11){
    		monthName="November";
    	}
        msg = "Month "+monthName+" doesn't have 31 days!";
        return msg;
    }
 
    if (month == 2) { // check for february 29th
    var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    if (day>29 || (day==29 && !isleap)) {
        msg = "February " + year + " doesn't have " + day + " days!";
        return msg;
    }
    }
 
    if (day.charAt(0) == '0') day= day.charAt(1);
    
    //Incase you need the value in CCYYMMDD format in your server program
    //msg = (parseInt(year,10) * 10000) + (parseInt(month,10) * 100) + parseInt(day,10);
    
    return msg;  // date is valid
}

function datepick(){
	$("#bDate").datepicker({
		changeMonth: true,
	    changeYear: true,	    
	    
	});
 
}
 
</SCRIPT>
<div id="dialog-confirm" title="Update Selection?" style="display: none;">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Are you sure to update this?</p>
</div>

<div id="dialog-delete-confirm" title="Delete Selection?" style="display: none;">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>Are you sure to permanantly delete this?</p>
</div>

<div id="dialog-message" title="Successful" style="display: none;">
  <p id="dialog-message-p"> </p>  
</div>

	<%String trns; %>
	<div id="transactionLine">
		
	</div>
	<div id="psCenter">
		<div id="psMainHeasing">
			<label>Patient CRUD</label>
		</div>
		<div class="line">
			<input type="hidden" id="patientId">
			<div class="label"><label>First Name</label></div>
			<div class="content"><input type="text" id="firstName" placeHolder="Enter Your First Name">	</div>
		</div>
		<div class="line">
			<div class="label"><label>Last Name</label></div>
			<div class="content"><input type="text" id="lastName" placeHolder="Enter Your Last Name">	</div>
		</div>
		<div class="line">
			<div class="label"><label>Birth Date</label></div>
			<div class="content"><input type="text" id="bDate" placeHolder="Enter in MM/DD/YYYY format" onclick="datepick()" onchange="myDateValidation()"/>	</div>
		</div>
		<%
		/*JSONParser parser = new JSONParser();
		Iterator<String> iterator = null;
		try {
			 
			Object obj = parser.parse(new FileReader("C:\\Users\bsharp.BSTLANKA\\Desktop\\New folder\\New folder\\countries_in_the_world.json"));
	 
			JSONObject jsonObject = (JSONObject) obj;
	 
			String name = (String) jsonObject.get("name");
			System.out.println(name);
	 
			long age = (Long) jsonObject.get("age");
			System.out.println(age);
	 
			// loop array
			JSONArray msg = jsonObject.
			iterator = msg.iterator();
			*/
				//System.out.println(iterator.next());
			
			//String url = "https://raw.githubusercontent.com/snvrthndev/CountriesAndCities/master/countries_in_the_world.json";
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\bsharp.BSTLANKA\\Desktop\\New folder\\New folder\\countries_in_the_world.json"));
			StringBuffer json = new StringBuffer();
			String s;
			while((s = br.readLine()) != null) {
				json.append(s);
			}
			JsonArray countries = new JsonParser().parse(json.toString()).getAsJsonArray();
			br.close();
				%>
				<!-- script>
				
					var dropdown = document.getElementById("country"); 
					dropdown.options[dropdown.options.length] = new Option();
				
				</script-->
				
				<%
			
	 
		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} */
	 	
	 
		
		%>
		<div class="line">
			<div class="label"><label>Country</label></div>
			<div class="content">
				<select id="country" onchange="makeRequest()">
					<option value="Select Country">Select Country</option>					
					<%
					Iterator<JsonElement> iterator = countries.iterator();
					int i=0;
					while (iterator.hasNext()) {
						String cntry = iterator.next().getAsString();
						%>
						<option value="<%=cntry %>"><%=cntry %></option>
						<%
					}
					
					%>
				</select>
			</div>
		</div>
		<div class="line">
			<div class="label"><label>City</label></div>
			<div class="content">
				<select id="city">
					<option value="Select City">Select City</option>
				</select>
			</div>
		</div>
		<div class="buttonDiv">
			<input type="button" value="Search" class="button-primary" id="searchBtn" >
			<input type="button" value="Submit" class="button-primary" id="addBtn">
			<input type="button" value="Update" class="button-primary" id="updateBtn">
			<input type="button" value="Delete" class="button-primary" id="deleteBtn">
			<input type="button" value="Clear All" onclick="ClearAll()"class="button-primary">
		</div>
	</div>
	<br>
	<div id="example_wrapper" class="dataTables_wrapper" style="height: 300px">
	
	</div>

</body>
</html>