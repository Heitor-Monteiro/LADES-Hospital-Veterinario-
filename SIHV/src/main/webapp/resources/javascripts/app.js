$(function () {
    $('.js-toggle').bind('click', function (event) {
        $('.js-sidebar, .js-content').toggleClass('is-toggled');
        event.preventDefault();

        $('.LADES-topbar-mini, .tituloBar').toggleClass('is-toggled');
        event.preventDefault();

        $('.LADES-topbar-mini').toggleClass('is-toggled2');
        event.preventDefault();

        $('.menu-toggle').toggleClass('is-toggled');
        event.preventDefault();

        $('.menu-toggle').toggleClass('is-toggled2');
        event.preventDefault();

        $('.menu-toggle').toggleClass('is-rotacao');
        event.preventDefault();

        $('.menu-toggle').toggleClass('is-rotacao2');
        event.preventDefault();

        $('.icoMenu').toggleClass('is-rotacao');
        event.preventDefault();
    });
});

function dropdownMenu(idMenu,icoMenu) {
    var x = document.getElementById(idMenu);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-green";
    } else {
        x.className = x.className.replace(" w3-show", "");
        x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-green", "");
    }
    icoMenu = '.'+icoMenu;
    $(icoMenu).toggleClass('is-rotacao');
        event.preventDefault();
}