<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.0.js"></script>
<script type="text/javascript">
	function requestJson(){
		
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/requestJson.action',
			//如果请求的是JSON数据，一定要指明contentType为application/json
			contentType:'application/json;charset=utf-8',
			data:'{"name":"手机","price":999}',
			success:function(jsonData){
				alert(jsonData)
			}
		});
	}
	
	function responseJson(){
		
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/responseJson.action',
			//如果是key/value，这里就不需要指定contentType
			//contentType:'application/json;charset=utf-8',
			data:'name=手机&price=999',
			success:function(jsonData){
				alert(jsonData)
			}
		});
	}
</script>
<title>Json测试</title>
</head>
<body>
	<input type="button" onclick="requestJson();" value="请求JSON串，返回JSON串"/> 
	&nbsp;&nbsp;
	<input type="button" onclick="responseJson();" value="请求key/value串，返回JSON串"/>
</body>
</html>