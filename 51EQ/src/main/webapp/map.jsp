<%@	page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>EQ+门店导航</title>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=Rm4w3fkD9Tc9ervhj7BnXcsH"></script>
		<style type="text/css">
		    html,body{margin:0;padding:0;}
		    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
		    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
		</style>
	</head>

	<body>
	  <!--地图容器-->
	  <div id="dituContent"></div>
	</body>
	<script type="text/javascript">
	    //创建和初始化地图函数：
	    function initMap(){
	    	changeSize();
	        createMap();//创建地图
	        setMapEvent();//设置地图事件
	        addMapControl();//向地图添加控件
	        addMarker();//向地图中添加marker
	    }
	    
	    //创建地图函数：
	    function createMap(){
	        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
	        var point = new BMap.Point(114.303487,30.568637);//定义一个中心点坐标
	        map.centerAndZoom(point,13);//设定地图的中心点和坐标并将地图显示在地图容器中
	        window.map = map;//将map变量存储在全局
	    }
	    
	    //地图事件设置函数：
	    function setMapEvent(){
	        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
	        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
	        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
	        map.enableKeyboard();//启用键盘上下左右键移动地图
	    }
	    
	    //地图控件添加函数：
	    function addMapControl(){
	        //向地图中添加缩放控件
			var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
			map.addControl(ctrl_nav);
			map.addControl(new BMap.ScaleControl());    
			map.addControl(new BMap.OverviewMapControl());    
			map.addControl(new BMap.MapTypeControl()); 
	    }
	    
	    //标注点数组
	    var markerArr = [{title:"总部",content:"硚口区古田四路13号江城壹号A区17号楼107室（招商部正门隔壁）<br/>Tel: 027-83800488",point:"114.219367|30.603888",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
		 	 ,{title:"01D",content:"武昌区徐东大街18号销品茂B1层B029号<br/>Tel: 027-68899726",point:"114.350237|30.595649",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"02D",content:"硚口区南国西汇生活广场1层61号<br/>Tel: 027-83998353",point:"114.229273|30.590381",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"03D-10D",content:"(03D)汉阳区摩尔城D座B1层13号<br/>Tel: 027-84459517<br/>(10D)汉阳区摩尔城B座2层220号<br/>Tel: 027-84588808",point:"114.213829|30.565281",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"04D",content:"光谷步行街C区3层03006号<br/>Tel: 027-87417008",point:"114.407674|30.511742",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"05D",content:"武昌区中北路1号洪山家乐福1层<br/>Tel：027-87314800",point:"114.344407|30.554451",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"06D",content:"江汉区唐家墩路菱角湖万达广场室内步行街2层83号<br/>Tel：027-85690686",point:"114.274034|30.615363",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"07D",content:"武昌区南国.南湖城市广场2层20号<br/>Tel：027-88100383",point:"114.331211|30.510506",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"08D-09D",content:"(08D)关山大道光谷天地E区1层129-130号<br/>Tel：027-87159807<br/>(09D)关山大道光谷天地F区2层<br/>Tel：027-87866808",point:"114.417035|30.483993",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"11D",content:"经开万达广场室内步行街2层30号<br/>Tel：027-84468088",point:"114.179613|30.51224",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"13D",content:"武昌区楚河汉街J2-4-29A门牌号62号<br/>Tel：027-87713668",point:"114.349608|30.561938",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"15D",content:"青山区奥山世纪广场2层B42号<br/>Tel：027-86530366",point:"114.367006|30.623753",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"16D",content:"江汉区后湖大道家乐福C区2层72号<br/>Tel：18986093565或18627995808",point:"114.313346|30.658059",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"17D",content:"光谷步行街11号光谷世界城广场B1层30号<br/>Tel：027-87667366",point:"114.41284|30.512046",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"18D",content:"武昌区彭刘杨路汉飞又一城1栋1层105号<br/>Tel：027-88244288",point:"114.302894|30.545881",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"19D",content:"汉阳王家湾人信汇（卷烟厂斜对面）<br/>Tel：027-84818820",point:"114.212149|30.557976",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"20D",content:"江汉区云彩路199号泛海城市广场一期购物中心L4-07号<br/>Tel：027-83737512",point:"114.261439|30.610398",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"21D",content:"武汉市硚口区解放大道387号南国西汇城市广场2层V212号<br/>Tel：027-83636387",point:"114.228638|30.590815",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ,{title:"22D",content:"武汉市武昌区徐东大街群星城2层WL-12号<br/>Tel：027-86603790",point:"114.356471|30.590512",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
			 ];
	    
	    //创建marker
	    function addMarker(){
	        for(var i=0;i<markerArr.length;i++){
	            var json = markerArr[i];
	            var p0 = json.point.split("|")[0];
	            var p1 = json.point.split("|")[1];
	            var point = new BMap.Point(p0,p1);
				var iconImg = createIcon(json.icon);
	            var marker = new BMap.Marker(point,{icon:iconImg});
				var iw = createInfoWindow(i);
				var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
				marker.setLabel(label);
	            map.addOverlay(marker);
	            label.setStyle({
	                        borderColor:"#808080",
	                        color:"#333",
	                        cursor:"pointer"
	            });
				
				(function(){
					var index = i;
					var _iw = createInfoWindow(i);
					var _marker = marker;
					_marker.addEventListener("click",function(){
					    this.openInfoWindow(_iw);
				    });
				    _iw.addEventListener("open",function(){
					    _marker.getLabel().hide();
				    })
				    _iw.addEventListener("close",function(){
					    _marker.getLabel().show();
				    })
					label.addEventListener("click",function(){
					    _marker.openInfoWindow(_iw);
				    })
					if(!!json.isOpen){
						label.hide();
						_marker.openInfoWindow(_iw);
					}
				})()
	        }
	    }
	    //创建InfoWindow
	    function createInfoWindow(i){
	        var json = markerArr[i];
	        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
	        return iw;
	    }
	    //创建一个Icon
	    function createIcon(json){
	        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
	        return icon;
	    }
	    
	    function changeSize(){
	    	var showMap = document.getElementById("dituContent"); 
	    	showMap.style.width = (document.documentElement.clientWidth - 2) + "px"; 
	    	showMap.style.height = (document.documentElement.clientHeight - 2)+ "px";
	    }
	    
	    initMap();//创建和初始化地图
	</script>
</html>