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
    	<style type="text/css">
    	._tip {
			color: #FF4000;
		}
    	</style>
    	<script>
            $(function() {
            	$("#listForm").validate({
                    rules : {
                    	orgId : {
                    		required : true                		
                        }
                    }
                });
            	
                $("#searchBtn").click(function() {
                    $("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });

                    $("#listForm").attr("action", "${sc_ctx}/cashCounterDaily/search");
                    $("#listForm").submit();
                });
                
                $("#confirmBtn").click(function() {
                	$("input[type='text'],textarea").each(function(i) {
                        this.value = $.trim(this.value);
                    });
                	
                    $("#listForm").attr("action", "${sc_ctx}/cashCounterDaily/confirm");
                    $("#listForm").submit();
                });
            });
            
            function cashCounterDaily() {
                $('#__cashCounterDaily_confirm').modal({
                    backdrop : true,
                    keyboard : true,
                    show : true
                });
            }
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
                            <h3>反日结(销售信息)</h3>
                        </legend>
                    </div>
                    <div class="span12">
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
                        <button	id="searchBtn" class="btn	btn-primary" type="button">查询</button>
                    </div>
                    
                    <c:if test="${!empty cashDaily.optDate}" >
                    <center>
                    <div class="span12" style="padding-top: 90px">
						<h1>反日结 - 机构 : <span class="_tip">${cashDaily.orgName }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
					</div>
					<div class="span12" style="padding-top: 10px">
						<h1>反日结 - 日期 : <span class="_tip">${cashDaily.optDate }</span></h1>
						<input type="hidden" id="optDate" name="optDate" value="${cashDaily.optDate }">
					</div>
					
					<div class="span12" style="padding-top: 50px">
						<a href="javascript:cashCounterDaily()" class="btn-large btn-danger">反日结</a>
					</div>
					</c:if>
					</center>
                </div>
         	</form>
     	</div>
     	
     	<div class="modal hide fade  __model37" id="__cashCounterDaily_confirm">
            <div class="modal-header">
                <a class="close" data-dismiss="modal">×</a>
                <h4>系统消息</h4>
            </div>
            <div class="modal-body">
                <center>
                    <p class="error">
                        反日结该笔销售流水信息吗？
                    </p>
                </center>
            </div>
            <div class="modal-footer">
                <a href="#" id="confirmBtn" class="btn btn-primary">确定</a>
                <a href="#" class="btn" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </body>
</html>