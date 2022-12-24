window.users_for_admin = users_for_admin;

function users_for_admin() {
    $.ajax({
        url: "./admin/users/request_for_admin",
        type: 'get',
        success: function (data) {
            $.each(data, function (index, user) {
                let role_text = "";

                $.each(user.roles, function (index, value) {
                    role_text += value.role.substring(5) + " ";
                });

                $('#users_for_admin_table').append('<tr class="users-table-row">\n' +
                    '                  <td id="id">' + user.id + '</td>\n' +
                    '                  <td id="name">' + user.name + '</td>\n' +
                    '                  <td id="username">' + user.username + '</td>\n' +
                    '                  <td id="email">' + user.email + '</td>\n' +
                    '                  <td id="age">' + user.age + '</td>\n' +
                    '                  <td id="roles">' + role_text.trim() + '</td>\n' +
                    '                  <td id="confirm"><button type="button" class="btn btn-success" id="confirm-button" role="button" onclick="confirm_admin()" value="' + user.id + '">Confirm</button></td>\n' +
                    '                  <td id="dismiss"><button type="button" class="btn btn-danger" id="dismiss-button" role="button" onclick="dismiss_admin()" value="' + user.id + '">Dismiss</button></td>\n' +
                    '                  </tr>')
            });
        },
        error: function () {
            $('#user-for-admin-table').replaceWith('<li class="list-group-item h3 text-center">Никто не хочет быть админом</li>');
        }
    });
}

$(users_for_admin());

function confirm_admin () {
    const user_id = $('#confirm-button').attr('value');
            const url = "./admin/update_user/" + user_id;

            $.getJSON(url, function (user) {

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

                let rolesArray = [jsonUser, jsonAdmin];

                let formData = {
                    "id": user.id,
                    "name": user.name,
                    "username": user.username,
                    "email": user.email,
                    "age": user.age,
                    "roles": rolesArray
                }

                $.ajax({
                    url: url,
                    type: 'put',
                    data: JSON.stringify(formData),
                    contentType: "application/json;charset=UTF-8",
                    processData: false
                }).then(function () {
                    $.ajax({
                        url: "./user/want_to_be_admin/" + user_id,
                        type: 'post',
                    }).then(function () {
                        $('.users-table-row').remove();
                        all_users();
                        users_for_admin();
                    });
                });

            });
}

function dismiss_admin () {
            const user_id = $('#dismiss-button').attr('value');
            const url = "./user/want_to_be_admin/" + user_id;

            $.ajax({
                url: url,
                type: 'post'
            }).then(function () {
                $('.users-table-row').remove();
                all_users();
                users_for_admin();
            });
}