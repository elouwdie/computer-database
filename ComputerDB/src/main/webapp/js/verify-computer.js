jQuery.validator.addMethod("greaterThan", function(value, element, params) {
		return new Date(value) >= new Date($(params).val()) || value == "";
}, 'Must be greater than {0}.');

jQuery.validator.addMethod("date", function(date, element) {
	return this.optional(element) || date.match(/^\d{4}-((0\d)|(1[012]))-(([012]\d)|3[01])$/);
	}, "Please specify a valid date");

$.validator.addMethod("alphan", function(value, element) {
	return this.optional(element) || /^[a-z\d\s\-+&.]+$/i.test(value);
}, "Letters, numbers, and underscores only please");

$(document).ready(function() {
	$("#add").validate({
		rules : {
			"computerName" : {
				required : true,
				alphan : true
			},
			"introduced" : {
				required : false,
				date : true
			},
			"discontinued" : {
				required : false,
				date : true,
				greaterThan : "#introduced"
			}
		},
		errorElement : "div",
		errorPlacement : function(error, element) {
			offset = element.offset();
			error.insertAfter(element);
			error.css('color', 'red');
		},
		messages : {
			"computerName" : {
				required : 'A name is required.'
			}
		}

	});

});