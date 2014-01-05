(function($){
    $(document).ready(function()
    {
        var s_Header = $('header');
        var s_MainNav = s_Header.find('nav').eq(0);
        var s_SideNav = $('nav#side-nav');

        $(window).scroll(function()
        {
            var s_ScrollTop = $(document).scrollTop();

            // Parallax scrolling on header background
            s_Header.css('background-position', 'center -' + (s_ScrollTop * 0.7) + 'px');

            if (s_ScrollTop > 200)
            {
                if (s_SideNav.length > 0)
                    s_SideNav.css({ position: 'fixed', top: '81px' });

                s_MainNav.css({ position: 'fixed', top: '0', bottom: 'auto' });
            }
            else
            {
                if (s_SideNav.length > 0)
                    s_SideNav.css({ position: 'static', top: 'auto' });

                s_MainNav.css({ position: 'absolute', top: 'auto', bottom: '0' });
            }
        });


        $('*[data-modal-id]').each(function(_, p_Element)
        {
            var s_ModalElement = $('#' + $(p_Element).attr('data-modal-id'));

            if (s_ModalElement.length == 0)
                return;

            s_ModalElement.addClass('modal');

            $(p_Element).attr('href', '#' + $(p_Element).attr('data-modal-id'));

            $(p_Element).leanModal({ closeButton: '.btn-close' });
        });
    });
})(jQuery);