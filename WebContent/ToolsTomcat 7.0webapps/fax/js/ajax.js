
/*
* creates a new XMLHttpRequest object which is the backbone of AJAX 
* or returns false if the browser doesn't support it
*/
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
	alert("lfgkd");
  var xmlHttpRequest = getXMLHttpRequest();
  alert ("xmlHttpRequest=" + xmlHttpRequest);
  xmlHttpRequest.onreadystatechange = getReadyStateHandler(xmlHttpRequest);
  alert("xhr");
  xmlHttpRequest.open("POST", "PatientControlServlet", true);  
  alert("open");
  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  alert("inside makeRequest()!");
  xmlHttpRequest.send(null);
  alert("sent!");
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
        document.getElementById("hello").innerHTML = xmlHttpRequest.responseText;
        alert("200");
      } else {
        alert("Http error " + xmlHttpRequest.status + ":" + xmlHttpRequest.statusText);
      }
    }
  };
}
