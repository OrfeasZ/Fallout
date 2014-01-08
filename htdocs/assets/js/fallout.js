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

        $('form').ajaxForm({ success: function(p_Response)
        {
            $('body').append($(p_Response));
        }});
    });
})(jQuery);

function ShowErrors(p_Errors)
{
    (function($, p_Errors)
    {
        var s_ErrorModal = $('#error-modal');

        var s_Lines = s_ErrorModal.find('.error-lines');

        s_Lines.empty();

        for (var i = 0; i < p_Errors.length; ++i)
            s_Lines.append($('<div class="error-line">' + p_Errors[i] + '</div>'));

        s_ErrorModal.modal({ zIndex: 90000 });
    })(jQuery, p_Errors);
}