<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>

<style type="text/css">
	span{
		color:blue;
	}
</style>

</head>
<body>
  <h1>jQuery copy, paste and cut example</h1>
  <form action="#">
  	<label>TextBox : </label>
	<input id="textA" type="text" size="50"
          value="Copy, paste or cut message here" />
          <input type="text" value="" id="myInput" />
          Email: <input type="textbox" id="email"><br/>
Confirm Email:    <input type="textbox" id="confirmEmail">
  </form>

  <span></span>

<script type="text/javascript">

$(document).ready(function() {

	$("#textA").bind({
		copy : function(){
			$('span').text('copy behaviour detected!');
		},
		paste : function(){
			$('span').text('paste behaviour detected!');
		},
		cut : function(){
			$('span').text('cut behaviour detected!');
		}
	});
window.onload = function() {
 var myInput = document.getElementById('myInput');
  myInput.onpaste = function(e) {
   e.preventDefault();
 }
  myInput.oncopy = function(e) {
   e.preventDefault();
 }
  myInput.oncut = function(e) {
   e.preventDefault();
 }
}
});
$('#email').bind("cut copy paste",function(e) {
     e.preventDefault();
 });
$('#confirmEmail').bind("cut copy paste",function(e) {
     e.preventDefault();
 });
</script>
</body>