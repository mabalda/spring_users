$(function () {
    $('#delete-button').click(
        function () {
            const user_id = $('#delete-button').attr('value');
            const url = "./admin/delete_user/" + user_id;

            $.ajax({
                type: 'delete',
                url: url,
                success: function () {
                    $('.users-table-row').remove();
                    all_users();
                    users_for_admin();
                }
            })
        }
    )
});
