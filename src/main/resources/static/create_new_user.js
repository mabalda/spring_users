$(function () {
    $('#create-button').click(
        function () {
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
            var i = 0;

            $.each($("#newUser-role").val(), function (index, value) {
                if (value == "ROLE_ADMIN") {
                    rolesArray[i++] = jsonAdmin;
                }
                if (value == "ROLE_USER") {
                    rolesArray[i++] = jsonUser;
                }
            });

            let formData = {
                "name": $("#newUser-name").val(),
                "username": $("#newUser-username").val(),
                "email": $("#newUser-email").val(),
                "age": $("#newUser-age").val(),
                "password": $("#newUser-password").val(),
                "roles": rolesArray,
            }

            $.ajax({
                url: "./admin/users/new",
                type: 'post',
                data: JSON.stringify(formData),
                contentType: "application/json;charset=UTF-8",
                processData: false,
                success: function() {
                    $('.users-table-row').remove();
                    all_users();
                    $('#table-tab').addClass('active');
                    $('#new-user-tab').removeClass('active');
                    $('#table-tab-pane').addClass('active show');
                    $('#new-user-tab-pane').removeClass('active show');
                    $('#new-user-form')[0].reset();
                }
            });
            event.preventDefault();
        });
});

