$(document).ready(function () {
    var res = $(location).attr('href').split('#');
    if(res[res.length-1] === 'offers') {
        $('#offer-seg').addClass('.notransition');
        $('#checkbox-offer').prop('checked', true);
        $('html, body').animate({
            scrollTop: $("#offers").offset().top
        }, 800);
        $('offer-seg').removeClass('.notransitions');
    }
});