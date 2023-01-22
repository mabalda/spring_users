$(function () {
    $('#request-for-admin-button').click(
        function () {
            const user_id = $('#request-for-admin-button').attr('value');
            const url = "./user/request";
            const url_to_bot = "./discordbot/" + user_id;
            const url_to_email = "./email/" + user_id;
            const url_to_vk = "./vkbot/" + user_id;

            $.ajax({
                url: url,
                type: 'post',
                success: function () {
                    $('#request-for-admin-button').addClass('invisible');
                }
            });

            $.get(url_to_bot);
            $.get(url_to_email);
            $.get(url_to_vk);
        }
    )
});