(function($){
    $(document).ready(function()
    {
        var s_Header = $('header');
        var s_SideNav = $('nav#sideNav');
        var s_MainNav = s_Header.find('nav').eq(0);

        $(window).scroll(function()
        {
            var s_ScrollTop = $(document).scrollTop();

            // Parallax scrolling on header background
            s_Header.css('background-position', 'center -' + (s_ScrollTop * 0.7) + 'px');
        });
    });
})(jQuery);