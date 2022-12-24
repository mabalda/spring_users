$(function () {
    $('#request-for-admin-button').click(
        function () {
            const user_id = $('#request-for-admin-button').attr('value');
            const url = "./user/want_to_be_admin/" + user_id;
            const url_to_bot = "./user/bot/" + user_id;

            $.ajax({
                url: url,
                type: 'post',
                success: function () {
                    $('#request-for-admin-button').addClass('invisible');
                }
            });

            $.get(url_to_bot);
        }
    )
});