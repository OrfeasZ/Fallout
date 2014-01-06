function InitDropdown($)
{
    $('select.gDropdown').each(function(_, p_Element)
    {
        var s_Element = $(p_Element);

        s_Element.removeClass('gDropdown').hide();

        var s_Options = [];

        var s_DropdownActive = false;

        var s_SelectElement = $('<div class="select"></div>');

        var s_ActiveItem = $('<span class="text" data-id="0"></span><span class="icon-caret-down right"></span>');

        var s_ActiveItemItem = s_ActiveItem.eq(0);

        console.log(s_ActiveItemItem);

        var s_Choices = $('<div class="select-choices"><ul></ul></div>');

        if (s_Element.hasClass('small'))
        {
            s_SelectElement.addClass('small');
            s_Choices.addClass('small');
        }

        var s_ChoicesList = s_Choices.find('ul');

        var s_IndexCounter = 0;
        s_Element.find('option').each(function(_, p_Option)
        {
            (function(p_Index)
            {
                var s_Option = $('<li data-id="' + p_Index + '">' + $(p_Option).html() + '</li>');
                s_Options.push(s_Option);

                s_ChoicesList.append(s_Option);

                s_Option.click(function()
                {
                    // Select the actual selectbox option
                    $(p_Option).prop('selected', true);

                    var s_OldItemIndex = s_ActiveItemItem.attr('data-id');
                    s_ActiveItemItem.attr('data-id', p_Index).html(s_Option.html());
                    s_Option.hide();
                    s_Options[s_OldItemIndex].show();

                    // Collapse the choice selector
                    s_DropdownActive = false;
                    s_SelectElement.removeClass('expanded');
                    s_Choices.hide();
                });

            })(s_IndexCounter);

            ++s_IndexCounter;
        });

        s_Options[0].hide();
        s_ActiveItemItem.html(s_Options[0].html());

        s_SelectElement.append(s_ActiveItem);

        s_Element.after(s_SelectElement);
        s_SelectElement.after(s_Choices);

        s_Choices.css('position', 'absolute').css('display', 'none').css('margin-top', '-11px');

        s_SelectElement.click(function()
        {
            if (s_Element.hasClass('small'))
            {
                s_Choices.css('position', 'absolute').css('left', s_SelectElement.position().left).css('top', s_SelectElement.position().top + s_SelectElement.height() + 12);
            }

            if (!s_DropdownActive)
            {
                s_DropdownActive = true;

                s_Choices.show();
                s_SelectElement.addClass('expanded');

                return false;
            }

            s_DropdownActive = false;

            s_SelectElement.removeClass('expanded');
            s_Choices.hide();

            return false;
        });
    });
}

(function($)
{
    $(document).ready(function()
    {
        InitDropdown($);
    });
})(jQuery);