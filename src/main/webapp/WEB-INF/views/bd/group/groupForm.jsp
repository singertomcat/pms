<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
	<head>
		<%@ include file="/common/global.jsp"%>
		<title>编辑分组</title>
		<%@ include file="/common/meta.jsp"%>
		<%@ include file="/common/include-base-styles.jsp"%>
		<%@ include file="/common/include-jquery-ui-theme.jsp"%>
		<%@ include file="/common/include-custom-styles.jsp" %>		

		<script src="${ctx }/js/common/jquery-1.8.3.js" type="text/javascript"></script>
		<script	src="${ctx }/js/common/plugins/jui/jquery-ui-${themeVersion }.min.js" type="text/javascript"></script>

	</head>


	<body>

		<div class="container showgrid">
			<form:form id="inputForm" action="${ctx}/bd/group/${action}"
				method="post" >
				<input type="hidden" name="id" id="id" value="${group.id}" />
				<fieldset>
					<legend>
						<small>编辑分组</small>
					</legend>
					<table border="1">
						<tr>
							<td>
								编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:
							</td>
							<td>
								<input type="text" id="code" name="code" value="${group.code}" />
							</td>
						</tr>
						<tr>
							<td>
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:
							</td>
							<td>
								<input type="text" id="name" name="name" value="${group.name}" />
							</td>
						</tr>
						<tr>
							<td>
								直接上级:
							</td>
							<td>
								<input type="button" id="choose" name="choose" value="选择"></input>
								<span id="chooseRow">
									<c:if test="${group.parent.id != null}">编码:${group.parent.code}；名称:${group.parent.name}</c:if>
								</span>
								
								<input type="hidden" id="parent.id" name="parent.id"  value="${group.parent.id}" />
								
							</td>
						</tr>
						<tr>
							<td>
								描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:
							</td>
							<td>
								<textarea id="description" name="description"
									class="input-large">${group.description}</textarea>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								<a class="startup-process" href="javascript:submit()">提交</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="startup-process" href="javascript:history.back()">返回</a>
							</td>
						</tr>
					</table>
				</fieldset>
			</form:form>
		</div>

		<div id="chooseParent" title="选择直接上级"  style="display: none">
				<table>
					<tr>
						<th>序号</th>
						<th>编码</th>
						<th>名称</th>
						<th>描述</th>
					</tr>
					<c:forEach items="${groupList}" var="group"  varStatus="nowcount">
						<tr ondblclick="setValue(this)" id="${group.id}_${group.code}_${group.name}">
							<td>
							${nowcount.count}</td>							
							<td>
								${group.code}
							</td>
							<td>
								${group.name}
							</td>
							<td>
								${group.description}
							</td>
						</tr>
					</c:forEach>
				</table>
		</div>
	</body>
	<script type="text/javascript">
	
	function setValue(obj){
		var editId = document.getElementById("id").value;
		var id = obj.id;
		
		var str = id.split("_");
		
		if (str[0] == editId) {
			alert("选择的直接上级不能和当前编辑的组相同");
			return;
		}
		
		//document.getElementById("pid").value=str[0];
		document.getElementById("parent.id").value=str[0];
		document.getElementById("chooseRow").innerHTML="编码:"+str[1] + "；名称:" + str[2];
		$('#chooseParent').dialog("close");
	}
	
	
$(function() {
	$('.startup-process').button( {
		icons : {
			primary : 'ui-icon-play'
		}
	});
	
});
function submit() {
	$('#inputForm').submit();
}

$(function() {
	$('#choose').button( {
		icons : {
			primary : 'ui-icon-plus'
		}
	}).click(function() {
		$('#chooseParent').dialog({
    			modal: true,
    			width: 500,
    			height: 800
    		});
	});
});
</script>
</html>
