<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
	<head>
		<script>
    		$(function() {
    			$('#optDateShow_start').datepicker({
                    format : 'yyyy-mm-dd'
                });
                $('#optDateShow_end').datepicker({
                    format : 'yyyy-mm-dd'
                });
                
                $("#listForm").validate({
                    rules : {
                    	optDateShow_start : {
                    		required : true,
                    		date : true                    		
                        },
                        optDateShow_end : {
                    		required : true,
                    		date : true,
                    		compareDate : "#optDateShow_start"
                        },
                        itemTypeNo : {
                    		required : true
                        }
                    }
                });
                
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/salesItemRankReport/search");
                    $("#listForm").submit();
                });
    		});
    	</script>
	</head>
	<body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
            <form method="post"	class="form-inline" id="listForm">
                <div class="row">
                    <div class="span12">
                        <legend>
                            <h3>销售信息排名(按类别)</h3>
                        </legend>
                    </div>
                    
                
					<div class="span12">
						<form method="post"	class="form-inline" id="listForm">
				    		<div class="row">
				    			<div class="span5">
				    				<label class="control-label">销售日期 :</label>
				    				<input id="optDateShow_start" name="optDateShow_start" type="text" class="input-medium" value="${optDateShow_start }"/>
				                        ～ <input id="optDateShow_end" name="optDateShow_end" type="text" class="input-medium" value="${optDateShow_end }"/>
				    			</div>
				    			<div class="span3">
				    				<label class="control-label">商品类别 :</label>
				    				<select name="itemTypeNo" class="input-medium">
				    				<c:forEach items="${itemTypeList}" var="itemType">
					               		<c:if test="${itemType.key == itemTypeNo}">
					                  		<option value="${itemType.key }" selected>${itemType.value }</option>
					                  	</c:if>
				                      	<c:if test="${itemType.key != itemTypeNo}">
				                       		<option value="${itemType.key }">${itemType.value }</option>
				                    	</c:if>
				                  	</c:forEach>
				                  	</select>
				    			</div>
				    			<div class="span3">
				    				<label class="control-label">机构 :</label>
				    				<select name="orgId" class="input-medium">
				              		<c:forEach items="${orgList}" var="org">
					               		<c:if test="${org.key == orgId}">
					                  		<option value="${org.key }" selected>${org.value }</option>
					                  	</c:if>
				                      	<c:if test="${org.key != orgId}">
				                       		<option value="${org.key }">${org.value }</option>
				                    	</c:if>
				                  	</c:forEach>
				                  	</select>
				                    <button	id="searchBtn" class="btn btn-primary" type="button">查询</button>
				    			</div>
				    		</div>
				    	</form>
            		</div>
                </div>
            </form>
        </div>
    </body>
</html>