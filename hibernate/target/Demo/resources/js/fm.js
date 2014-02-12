var disablePush=false;
var inProgress=false;
function printSomething()
{
	
	var fieldNameElement = document.getElementById('div.demo-container');
	  while(fieldNameElement.childNodes.length >= 1) {
	    fieldNameElement.removeChild(fieldNameElement.firstChild);
	  }
	  fieldNameElement.appendChild(fieldNameElement.ownerDocument.createTextNode(window.disablePush));

}
function printSomething(str)
{
	
	var fieldNameElement = document.getElementById('div.demo-container');
	  while(fieldNameElement.childNodes.length >= 1) {
	    fieldNameElement.removeChild(fieldNameElement.firstChild);
	  }
	  fieldNameElement.appendChild(fieldNameElement.ownerDocument.createTextNode(str));

}
function checkDouble()
{
	
	if(!inProgress){
	inProgress=true;
	if(!disablePush){
		disablePush=true;
	var btn = document.getElementById('form:btn');
	
	btn.click();

	}else{
		
		printSomething("Processing");
		
	}
	inProgress=false;
}
	
	return disablePush;
}
function disablePushCmd()
{
	
	disablePush=false;
	
}
function disablePushInterval()
{
	

setInterval(function(){disablePushCmd()},3000);
}
function createEventTimer()
{
setInterval(function(){createEvent()},400);
}

function createEvent()
{
var index=	Math.floor((Math.random()*3));
	passEvent1 = new Object();
	passEvent1.name = "Alex topu Hakana attı.";
	passEvent1.value = 10;

	passEvent2 = new Object();
	passEvent2.name = "Hakan sert bir şut attı.";
	passEvent2.value = 10;

	passEvent3 = new Object();
	passEvent3.name = "Kaleci Cengiz son anda topu kornere çeliyor.";
	passEvent3.value = 10;

	var eventList = new Array();
	eventList[0] =passEvent1;
	eventList[1] =passEvent2;
	eventList[2] =passEvent3;
	for(i = 0; i <1; i++){
	index=	Math.floor((Math.random()*3));
	var fieldNameElement = document.getElementById('div.demo-container');
	  while(fieldNameElement.childNodes.length >= 1) {
	    fieldNameElement.removeChild(fieldNameElement.firstChild);
	  }
	  fieldNameElement.appendChild(fieldNameElement.ownerDocument.createTextNode(eventList[index].name));


	}
}
function myFunction()
{

var myString = "10,39,45,22";
var myAttName = "Kondisyon,Bitiricilik,Şut,Pas";

var mySplitResult = myString.split(",");
var mySplitResultName = myAttName.split(",");



person = new Object();
person.name = "ccc";
	person.height = "6Ft";
	
	
	var attrList = new Array();
	
	document.write("<ul  class=\"ui-datalist-data\">");
for(i = 0; i < mySplitResult.length; i++){
	attr1 = new Object();
	attr1.value=mySplitResult[i];
	attr1.name = mySplitResultName[i];
	attrList[i] =attr1;
	document.write("<li class=\"ui-datalist-item\"><div class=\"propMin\">"+attrList[i].name+ "<div class=\"progress-container\">" +"<div style=\"width: "+attrList[i].value+ "%\"></div></div></div></li>"); 
}
window.pattrList=attrList;
document.write("</ul>");




}

function myFunctionNew(div,myString,myAttName)
{

alert(div.html());
var mySplitResult = myString.split(",");
var mySplitResultName = myAttName.split(",");



person = new Object();
person.name = "ccc";
	person.height = "6Ft";
	
	
	var attrList = new Array();
	
	document.write("<ul  class=\"ui-datalist-data\">");
for(i = 0; i < mySplitResult.length; i++){
	attr1 = new Object();
	attr1.value=mySplitResult[i];
	attr1.name = mySplitResultName[i];
	attrList[i] =attr1;
	div.html("<li class=\"ui-datalist-item\"><div class=\"propMin\">"+attrList[i].name+ "<div class=\"progress-container\">" +"<div style=\"width: "+attrList[i].value+ "%\"></div></div></div></li></ul>");
	//document.write("<li class=\"ui-datalist-item\"><div class=\"propMin\">"+attrList[i].name+ "<div class=\"progress-container\">" +"<div style=\"width: "+attrList[i].value+ "%\"></div></div></div></li>"); 
}
//window.pattrList=attrList;
//document.write("</ul>");




}