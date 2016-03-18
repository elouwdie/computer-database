jQuery.validator.addMethod("greaterThan", function(value, element, params) {
		return new Date(value) >= new Date($(params).val()) || value == "";
}, datesCompError);

jQuery.validator.addMethod("date", function(date, element) {
	var dateOkFr = date.match(/^(0\d|[12]\d|3[01])\/(0\d|1[012])\/((?:19|20)\d{2})$/)
	var dateOkEn = date.match(/^(0\d|1[012])\/(0\d|[12]\d|3[01])\/((?:19|20)\d{2})$/)
	
	if (pattern == "jj/MM/aaaa")
		return this.optional(element) || dateOkFr;
	else 
		return this.optional(element) || dateOkEn;
	}, dateError);

$.validator.addMethod("alphan", function(value, element) {
	return this.optional(element) || /^[a-z\d\s\-+&.]+$/i.test(value);
}, nameAlphaError);

$(document).ready(function() {
	$("#add").validate({
		rules : {
			"name" : {
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
			"name" : {
				required : nameError
			}
		}

	});

});