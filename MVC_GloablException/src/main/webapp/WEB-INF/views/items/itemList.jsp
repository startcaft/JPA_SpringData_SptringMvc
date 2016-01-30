<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${ pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function deleteItems(){
		document.itemsForm.action = "${ctx}/items/deleteItems.action";
		document.itemsForm.submit();
	}
	function queryItems(){
		document.itemsForm.action = "${ ctx }/items/queryItems.action";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
	<form name="itemsForm" action="${ ctx }/items/queryItems.action" method="post">
		<table width="100%" border=1>
			<tr>
				<td>商品名称:<input type="text" name="name" />
				</td>
				<td>
					<input type="button" value="查询" onclick="queryItems();" />
					&nbsp;&nbsp;
					<input type="button" value="批量删除 " onclick="deleteItems();" />
				</td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>选择</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${ page.content }" var="item">
				<tr>
					<td>
						<input type="checkbox" name="itemsId" value="${ item.id }" />
					</td>
					<td>${ item.name }</td>
					<td>${ item.price }</td>
					<td><fmt:formatDate value="${ item.createTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${ item.detail }</td>
					<td><a href="${ ctx }/items/editItems.action?id=${ item.id }">修改</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">共 ${ page.totalElements } 条记录 共 ${ page.totalPages }
					页 当前 ${ page.number + 1 } 页 <a
					href="${ ctx }/items/queryItems.action?pageNo=${ page.number + 1 - 1 }">上一页</a>
					<a
					href="${ ctx }/items/queryItems.action?pageNo=${ page.number + 1 + 1 }">下一页</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>