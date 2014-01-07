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
		<link rel="stylesheet" type="text/css" href="${ctx}/static/uploadify/uploadify.css" />
		<script type="text/javascript" src="${ctx}/static/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="${ctx}/static/uploadify/jquery.uploadify.v2.1.4.js"></script>
		
		<script type="text/javascript">
			$(function(){
				$("#inputForm").validate({
                    rules : {
                        batchId : {
                            required : true
                        }
                    }
                });
				
				$('#batchId').datepicker({
                    format : 'yyyy-mm-dd'
                });
				
				<%// http://www.cnblogs.com/pinnasky/archive/2010/06/02/1750089.html %>
				$("#uploadify").uploadify({
					'uploader' : '${ctx}/static/uploadify/uploadify.swf',
					'script' : '${sc_ctx}/reqBill/uploadFile',
					'scriptData' : {},
					'cancelImg' : '${ctx}/static/uploadify/cancel.png',
					'buttonText' : '',
					'folder' : 'UploadFile',
					'queueID' : 'fileQueue',
					'fileDataName' : 'file',
					'height' : 30,
					'width' : 75,
					'auto' : false,
					'multi' : true,
					'queueSizeLimit' : 30,
					'simUploadLimit' : 1,
					'displayData' : 'percentage', // Set to "speed" to show the upload speed in the default queue item
					'buttonImg' : '${ctx}/static/uploadify/browse.jpg',
					'fileDesc' : 'Ms', //如果配置了以下的'fileExt'属性，那么这个属性是必须的 (.JPG, .GIF, .PNG)
					'fileExt' : '*.xls', //允许的格式 *.jpg;*.gif;*.png;
					'onAllComplete' : function(event, data) {
						
					},
					'onComplete' : function(event, queueID, file,
							serverData, data) { 
						
					},
					'onSelect' : function(event, queueId, fileObj) { //name、size、creationDate、modificationDate、type
			
					}
				});
			
				$('#upload_file').click(function() {
					if(!$("#inputForm").valid()){
						return;
					}
					if ($('.uploadifyQueueItem').length == 0) {
						$('#myModal').modal();
						return;
					}
					if ($("#inputForm").valid()) {
						$('#uploadify').uploadifySettings('scriptData',{'batchId':$('#batchId').val()})
						$('#uploadify').uploadifyUpload();
					}
				});
				
				$('#cleanBtn').click(function() {
					if(!$("#inputForm").valid()){
						return;
					}
					
					$.ajax({
						url: "${sc_ctx}/reqBill/deleteDirectory",
						data: $("#inputForm").serialize(),
					    type:'post',
					    cache:false,
					    dataType:'json',
					    success:function(data) {
					    	$('#myModal1').modal();
							return;
					    }   
					});
				});
			});
		</script>
		<style type="text/css">
		img {
			vertical-align: top;
		}
		.modal {
			width: 360px;
			margin-left: -180px;
		}
		</style>
	</head>
	
	<body>
		<div id="myModal" class="modal hide fade __model37">
			<div class="modal-header">
				<a class="close" data-dismiss="modal" >&times;</a>
				<h4>系统消息</h4>
            </div>
            <div class="modal-body">
				<p class="error">请选择要上传的文件!</p>
            </div>
            <div class="modal-footer">
           		<a href="#" class="btn" data-dismiss="modal" >关闭</a>
            </div>
		</div>
		<div id="myModal1" class="modal hide fade __model37">
			<div class="modal-header">
				<a class="close" data-dismiss="modal" >&times;</a>
				<h4>系统消息</h4>
            </div>
            <div class="modal-body">
				<p class="error">已完成清理处理!</p>
            </div>
            <div class="modal-footer">
           		<a href="#" class="btn" data-dismiss="modal" >关闭</a>
            </div>
		</div>
		<%// 系统菜单  %>
        <page:applyDecorator name="menu" />
        
        <div class="container">
        	<div class="row">
      			<div class="span12">
           			<legend>
                 		<h3>门店要货单</h3>
               		</legend>
              	</div>
              	<div class="span6"	style="margin-top: 10px;">
              		<form class="form-horizontal" id="inputForm">
              			<div class="control-group">
                    		<label class="control-label">批次号 :</label>
                       		<div class="controls">
                                <input type="text" id="batchId" name="batchId">
                            </div>
                        </div>
                        <div class="control-group">
                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        	<input type="file" id="uploadify" name="uploadify" multiple="true">
                        </div>
                        <div class="control-group">
                        	<div id="fileQueue" style="height: 25px;padding: 0 0 20px 120px;"></div>
                        </div>
                        <div class="control-group">
                            <div class="controls">
                            	<a class="btn btn-large btn-danger" href="#" id="cleanBtn"><i class="icon-trash icon-white"></i> 清理</a>
                                <a class="btn btn-large btn-info" href="#" id="upload_file"><i class="icon-inbox icon-white"></i> 上传</a>
                            </div>
                        </div>
              		</form>
              	</div>
			</div>
		</div>
    </body>
</html>