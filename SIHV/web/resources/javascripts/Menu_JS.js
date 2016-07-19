var menu = $(".menu");
$(window).resize(function(){
    $(".menu-toggle").removeClass("active");
    if($(window).innerWidth() > 600){
            menu.show();
    } else {
            menu.hide();
    }
});
$(".menu-toggle").click(function(){
    $(this).toggleClass("active");
    menu.slideToggle();
})
$(".open-submenu").click(function(){
    $(this).toggleClass("active");
    $(this).next("ul").slideToggle();
    $(this).children(".arrow").toggleClass("down up");
});

/*Menu-toggle: Função para ativar no clique
 * a transformação de 3 linhas em X*/
function myFunction(x) {
    x.classList.toggle("change");
}