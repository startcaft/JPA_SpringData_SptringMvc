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
	function editItemsAllSubmit() {
		//提交form
		document.itemsForm.action = "${ ctx }/items/editItemsAllSubmit.action";
		document.itemsForm.submit();
	}
	function queryItems() {
		document.itemsForm.action = "${ ctx }/items/queryItems.action";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
	<form name="itemsForm" action="${ ctx }/items/queryItems.action"
		method="post">
		<table width="100%" border=1>
			<tr>
				<td>商品名称:<input type="text" name="name" />
				</td>
				<td><input type="button" value="查询" onclick="queryItems();" />
					&nbsp;&nbsp; <input type="button" value="批量修改提交"
					onclick="editItemsAllSubmit()" /></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${ page.content }" var="item" varStatus="status">
				<tr>
					<td><input name="items[${status.index }].name"
						value="${item.name }" /></td>
					<td><input name="items[${status.index }].price"
						value="${item.price }" /></td>
					<td><input name="items[${status.index }].createTime"
						value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" /></td>
					<td><input name="items[${status.index }].detail"
						value="${item.detail }" /></td>
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