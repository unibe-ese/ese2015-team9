$(document).ready(function () {
    var spl = $(location).attr('href').split('#');
    if(spl[spl.length-1] === 'offers') {
        $('#checkbox-offer').prop('checked', true);
    }
});