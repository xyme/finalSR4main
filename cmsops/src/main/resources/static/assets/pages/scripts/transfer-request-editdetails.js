var DatePickerFunc = function () {
    
    var handleDatePicker = function () {

        //initialize datepicker
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
    }



    return {
        //main function to initiate the module
        init: function () {

            handleDatePicker();
        }
    };

}();

jQuery(document).ready(function() {
    DatePickerFunc.init();
});