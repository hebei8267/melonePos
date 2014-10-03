/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.messages, {
    required : "请填写此字段",
    remote : "请修正此字段",
    email : "请输入正确格式的电子邮件",
    url : "请输入合法的网址",
    date : "请输入合法的日期",
    dateISO : "请输入合法的日期 (ISO).",
    number : "请输入合法的数字",
    digits : "只能输入整数",
    creditcard : "请输入合法的信用卡号",
    equalTo : "请再次输入相同的值",
    accept : "请输入拥有合法后缀名的字符串",
    maxlength : jQuery.validator.format("输入长度最多为 {0} 的字符串"),
    minlength : jQuery.validator.format("输入长度最小为 {0} 的字符串"),
    rangelength : jQuery.validator.format("输入长度必须介于 {0} 和 {1} 之间的字符串"),
    range : jQuery.validator.format("输入值必须介于 {0} 和 {1} 之间的值"),
    max : jQuery.validator.format("输入值不能大于 {0} "),
    min : jQuery.validator.format("输入值不能小于 {0} ")
});

//固定长度效验验证
jQuery.validator.addMethod("length11", function(value, element) {
    var length = value.length;
    return (length == 11);
}, "请输入长度为 11 的字符串");
//固定长度效验验证
jQuery.validator.addMethod("length7", function(value, element) {
    var length = value.length;
    return (length == 7);
}, "请输入长度为 7 的字符串");
//固定长度效验验证
jQuery.validator.addMethod("length18", function(value, element) {
    var length = value.length;
    return (length == 18);
}, "请输入长度为 18 的字符串");

// 列表删除,必选
jQuery.validator.addMethod("requiredSelect", function(value, element, param) {
	 return $("input[name='"+ param +"']:checked").length > 0;
}, "请选择至少一个操作对象");
// 列表单选,必选
jQuery.validator.addMethod("requiredOneSelect", function(value, element, param) {
	 return $("input[name='"+ param +"']:checked").length > 0;
}, "请选择一个操作对象");

//手机号码验证       
jQuery.validator.addMethod("isMobile", function(value, element) {       
	var length = value.length;   
	var mobile = /^(\d{11})$/;
	return this.optional(element) || (mobile.test(value));       
}, "请输入正确的手机号码");
    
// 电话号码验证       
jQuery.validator.addMethod("isTel", function(value, element) {
	//电话号码格式010-12345678
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;  
	return this.optional(element) || (tel.test(value));       
}, "请输入正确的电话号码");
  
// 联系电话(手机/电话皆可)验证   
jQuery.validator.addMethod("isPhone", function(value,element) {   
	var length = value.length;
	var mobile = /^(\d{11})$/;
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value) || (mobile.test(value)));   
}, "请输入正确的电话号码");

//金额验证
jQuery.validator.addMethod("money", function(value, element) {
	return this.optional(element) || /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/.test(value);
}, "请输入正确的金额");
//金额验证
jQuery.validator.addMethod("negativeMoney", function(value, element) {
	return this.optional(element) || /^-?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/.test(value);
}, "请输入正确的金额");

//汇率验证
jQuery.validator.addMethod("rate", function(value, element) {
	return this.optional(element) || /^[0-9]+.?[0-9]*$/.test(value);
}, "请输入正确的金额");

//大于零的整数验证
jQuery.validator.addMethod("digits1", function(value, element) {
	return this.optional(element) || /^([1-9]{1}\d*)?$/.test(value);
}, "请输入合法的数字");

jQuery.validator.addMethod("dateLessThan", function(value, element, param) {
	if(value == ''){
		return true
	} else {
    	return value < param;
    }
}, '输入日期必须早于 {0}');

jQuery.validator.addMethod("dateGreaterThan", function(value, element, param) {
	if(value == ''){
		return true
	} else {
    	return value > param;
    }
}, '输入日期必须晚于 {0}');

jQuery.validator.addMethod("compareDate", function(value, element, param) {
    var startDate = jQuery(param).val();
    var d1Arr = startDate.split('-');
    var d2Arr = value.split('-');
    var v1 = new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
    var v2 = new Date(d2Arr[0],d2Arr[1],d2Arr[2]);
    return v1 <= v2;
}, '结束日期必须大于开始日期');

jQuery.validator.addMethod("myRequired", function(value, element, param) {
	var target = $(param)
	if(target.val() > 0){
		if ( element.nodeName.toLowerCase() === "select" ) {
			// could be an array for select-multiple or a string, both are fine this way
			var val = $(element).val();
			return val && val.length > 0;
		}
		return $.trim(value).length > 0;
	}
	return true;
}, "请填写此字段");

/**
 * 加法运算，避免数据相加小数点后产生多位数和计算精度损失。
 * 
 * @param num1加数1 | num2加数2
 */
function numAdd(num1, num2) {
	var baseNum, baseNum1, baseNum2;
	try {
		baseNum1 = num1.toString().split(".")[1].length;
	} catch (e) {
		baseNum1 = 0;
	}
	try {
		baseNum2 = num2.toString().split(".")[1].length;
	} catch (e) {
		baseNum2 = 0;
	}
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	return (num1 * baseNum + num2 * baseNum) / baseNum;
};
/**
 * 加法运算，避免数据相减小数点后产生多位数和计算精度损失。
 * 
 * @param num1被减数  |  num2减数
 */
function numSub(num1, num2) {
	var baseNum, baseNum1, baseNum2;
	var precision;// 精度
	try {
		baseNum1 = num1.toString().split(".")[1].length;
	} catch (e) {
		baseNum1 = 0;
	}
	try {
		baseNum2 = num2.toString().split(".")[1].length;
	} catch (e) {
		baseNum2 = 0;
	}
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
	precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
	return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
};

