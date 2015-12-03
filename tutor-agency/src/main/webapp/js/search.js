$(document).ready(function() {
    var res = $(location).attr('href').split('#');
    if(res[res.length-1] === 'result') {
        $('#noresult').css('display', 'block');
    }
});