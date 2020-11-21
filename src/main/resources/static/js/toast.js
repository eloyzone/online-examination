

$(".toast-container-dismissible").fadeTo(4000, 100).slideUp(500, function () {
    $(".toast-container-dismissible").css('display', 'none');
});

$('.toast-close-button').on('click', function () {
    let parentDiv = $(this).parent();
    parentDiv.remove();
});