$(document).ready(function () {
    var res = $(location).attr('href').split('#');
    if(res[res.length-1] === 'offers') {
        $('#checkbox-offer').prop('checked', true);
    }
});