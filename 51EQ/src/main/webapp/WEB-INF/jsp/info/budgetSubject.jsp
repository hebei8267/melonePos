<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<c:set var="em_ctx">
    ${ctx}/em
</c:set>
<!DOCTYPE html>
<html>
    <head>
    	<link href="${ctx}/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
        <link href="${ctx}/assets/global/plugins/jstree/dist/themes/default/style.min.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/assets/global/plugins/jstree/dist/jstree.js" type="text/javascript"></script>
        <link href="${ctx}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
		<script src="${ctx}/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
        <style type="text/css">
            .form-horizontal .control-label {
                width: 130px;
            }
            .form-horizontal .controls {
                margin-left: 145px;
            }
        </style>
        <script>
        	var subjectTreeData = ${rootNodeJson};
			var selectNode = null;
			
            $().ready(function() {
                $('#subjectTree').jstree({
					'core' : {
						"themes" : {
							"responsive" : false
						},
						'data' : subjectTreeData
					}
				}).bind("select_node.jstree", function(e, data) {
					selectNode = data;
					//nodeSelected();
				}).bind("ready.jstree", function() {
					$('#subjectTree').jstree("open_all");
				});
				
				initSubAddWin();
            });
            
            function initSubAddWin(){

				// general settings
				$.fn.modal.defaults.spinner = $.fn.modalmanager.defaults.spinner = '<div class="loading-spinner" style="width: 200px; margin-left: -100px;">' + '<div class="progress progress-striped active">' + '<div class="progress-bar" style="width: 100%;"></div>' + '</div>' + '</div>';
				$.fn.modalmanager.defaults.resize = true;

				var $modal = $('#sub-add-modal');

				$('#sub-add-btn').on('click', function() {
					$('body').modalmanager('loading');
		
					setTimeout(function() {
						$modal.load('${em_ctx}/budgetSubject/subAdd', '', function() {
							$modal.modal();
						});
					}, 200);
				});
            }
        </script>
    </head>
    <body>
        <%// 系统菜单  %>
        <page:applyDecorator name="menu" />

        <div class="container">
            
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>预算科目管理</h3>
                    </legend>
                </div>
              	
              	<form method="post"	class="form-horizontal"	id="listForm">
                    <div class="span12">
                        <a href="#" id="sub-add-btn" class="btn btn-primary">新增</a>
                        <input id="delBtn" name="delBtn" type="button" class="btn btn-danger" value="删除"/>
                    </div>
                    
					<div class="span5" style="margin-top:15px;">
						<div class="portlet box grey-cascade">
							<div class="portlet-title">
								<div class="caption">
								预算科目
								</div>
							</div>
							<div class="portlet-body">
								<div id="subjectTree" class="tree-demo">

								</div>
							</div>
						</div>
					</div>
				</form>
					
				<div class="span5" style="margin-top:15px;">
					<div class="portlet box green ">
						<div class="portlet-title">
							<div class="caption">
								预算科目信息
							</div>
						</div>
						<div class="portlet-body">
							<form class="form-horizontal" id="org-edit-form" action="${ctx}/struct/orgManage.jspx">
								<div class="form-body">
									<div class="control-group">
										<label class="control-label">上层科目</label>
										<div class="controls">
											<input type="text" id="parentOrgName" name="parentOrgName" readonly/>
											<input type="hidden" id="parentOrgId" name="parentOrgId" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">科目名称<span class="required">※</span></label>
										<div class="controls">
											<input type="text" class="form-control" id="orgName" name="orgName"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">排序(显示)</label>
										<div class="controls">
											<input type="text" class="form-control input-inline input-mini" id="sortIndex" name="sortIndex"/>
											<span class="help-inline">1-99</span>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="button" class="btn btn-success" id="org-save-btn">保存</button>
											<button type="button" class="btn" id="org-reset-btn" onclick="OrgManage.nodeSelectReset()">重置</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				
				<div class="span12">
					<div id="sub-add-modal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false"></div>
				</div>
					
            </div>
            
        </div>
    </body>
</html>