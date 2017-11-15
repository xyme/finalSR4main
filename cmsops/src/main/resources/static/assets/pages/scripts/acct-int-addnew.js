
var FormValidation = function () {
    
    var handleValidation0 = function () {
        var form0 = $('#acct_int_addnew_form');
        var error0 = $('.alert-danger', form0);
        var success0 = $('.alert-success', form0);

        form0.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",  // validate all fields including form hidden input
            messages: {
                roles: {
                    minlength: jQuery.validator.format("At least {0} items must be selected")
                },
                radio: {
                    required: "Please select an option"
                },
                service: {
                    required: "Please select  at least 2 types of Service",
                    minlength: jQuery.validator.format("Please select  at least {0} types of Service")
                }
            },
            rules: {
                password: {
                    minlength: 8,
                    required: true
                },
                pwd: {
                    minlength: 8,
                    required: true
                },
                username: {
                    required: true
                },
                name: {
                    required: true
                },
                usergroup: {
                    required: true
                },
                usergrp: {
                    required: true
                },
                selectedStaff: {
                    required: true,
                },
                staffiddrop: {
                    required: true,
                },
                url: {
                    required: true,
                    url: true
                },
                number: {
                    required: true,
                    number: true
                },
                digits: {
                    required: true,
                    digits: true
                },
                creditcard: {
                    required: true,
                    creditcard: true
                },
                shortword: {
                    minlength: 5,
                },
                purchasedate: {
                    required: true
                },
                eoldate: {
                    required: true
                },
                radio: {
                    required: true
                },
                checklist: {
                    required: true,
                    minlength: 2
                },
                usergroup: {
                    required: true
                },
                select: {
                    required: true
                },
                select2tags: {
                    required: true
                },
                select_multi: {
                    required: true,
                    minlength: 1,
                    maxlength: 3
                },
                membership: {
                    required: true
                },
                service: {
                    required: true,
                    minlength: 2
                },
                roles: {
                	required: true,
                	minlength: 1
                }
               
                
            },

            invalidHandler: function (event, validator) { //display error alert on form submit              
                success0.hide();
                error0.show();
                App.scrollTo(error0, -200);
            },

            errorPlacement: function (error, element) { // render error placement for each input typeW
                    if (element.parents('.mt-radio-list').size() > 0 || element.parents('.mt-checkbox-list').size() > 0) {
                        if (element.parents('.mt-radio-list').size() > 0) {
                            error.appendTo(element.parents('.mt-radio-list')[0]);
                        }
                        if (element.parents('.mt-checkbox-list').size() > 0) {
                            error.appendTo(element.parents('.mt-checkbox-list')[0]);
                        }
                    } else if (element.parents('.mt-radio-inline').size() > 0 || element.parents('.mt-checkbox-inline').size() > 0) {
                        if (element.parents('.mt-radio-inline').size() > 0) {
                            error.appendTo(element.parents('.mt-radio-inline')[0]);
                        }
                        if (element.parents('.mt-checkbox-inline').size() > 0) {
                            error.appendTo(element.parents('.mt-checkbox-inline')[0]);
                        }
                    } else if (element.parent(".input-group").size() > 0) {
                        error.insertAfter(element.parent(".input-group"));
                    } 
                    //else if (element.attr("data-error-container")) { 
                        //error.appendTo(element.attr("data-error-container"));
                    //} 
                    else {
                        error.insertAfter(element); // for other inputs, just perform default behavior
                        var icon = $(element).parent('.input-icon').children('i');
                        icon.removeClass('fa-check').addClass("fa-warning");  
                        icon.attr("data-original-title", error.text()).tooltip({'container': 'body'});
                    }
                },


            highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').removeClass("has-success").addClass('has-error'); // set error class to the control group   
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label, element) {
                    var icon = $(element).parent('.input-icon').children('i');
                    $(element).closest('.form-group').removeClass('has-error').addClass('has-success'); // set success class to the control group
                    icon.removeClass("fa-warning").addClass("fa-check");
                },

            submitHandler: function (form) {
                success0.show();
                error0.hide();
                form[0].submit(); // submit the form
            }

        });


        //initialize datepicker
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
        $('.date-picker .form-control').change(function() {
            form0.validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input 
        });
    }



    var handleWysihtml5 = function() {
        if (!jQuery().wysihtml5) {
            
            return;
        }

    }

    return {
        //main function to initiate the module
        init: function () {

            handleWysihtml5();
            handleValidation0();

        }

    };

}();

jQuery(document).ready(function() {
    FormValidation.init();
});