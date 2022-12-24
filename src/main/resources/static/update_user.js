$(function () {
    $('#edit-button').click(
        function () {
            const user_id = $('#edit-button').attr('value');
            const url = "./admin/update_user/" + user_id;

            let jsonAdmin = {
                "id": 2,
                "role": "ROLE_ADMIN",
                "users": null,
                "authority": "ROLE_ADMIN"
            };
            let jsonUser = {
                "id": 1,
                "role": "ROLE_USER",
                "users": null,
                "authority": "ROLE_USER"
            };
            let rolesArray = [];
            let i = 0;

            $.each($("#userForUpdate-role").val(), function (index, value) {
                if (value == "ROLE_ADMIN") {
                    rolesArray[i++] = jsonAdmin;
                }
                if (value == "ROLE_USER") {
                    rolesArray[i++] = jsonUser;
                }
            });

            let formData = {
                "id": $("#userForUpdate-id").val(),
                "name": $("#userForUpdate-name").val(),
                "username": $("#userForUpdate-username").val(),
                "email": $("#userForUpdate-email").val(),
                "age": $("#userForUpdate-age").val()
            }

            if (rolesArray.length > 0) {
                formData["roles"] = rolesArray;
            }

            $.ajax({
                url: url,
                type: 'put',
                // method: 'patch',
                data: JSON.stringify(formData),
                contentType: "application/json;charset=UTF-8",
                processData: false,
                success: function () {
                    $('.users-table-row').remove();
                    all_users();
                    users_for_admin();
                }
            });
        }
    )
});