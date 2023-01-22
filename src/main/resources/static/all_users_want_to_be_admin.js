window.users_for_admin = users_for_admin;

function users_for_admin() {
    $.ajax({
        url: "./admin/users/requests",
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
        }
    });
}

$(users_for_admin());

function confirm_admin () {
    const user_id = $('#confirm-button').attr('value');
    const url = "./admin/users/requests";

    let requestData = {
        "id": user_id,
        "applied": true
    }

    $.ajax({
        url: url,
        type: 'post',
        data: JSON.stringify(requestData),
        contentType: "application/json;charset=UTF-8",
        processData: false
    }).then(function () {
        $('.users-table-row').remove();
        all_users();
        users_for_admin();
    });
}

function dismiss_admin () {
            const user_id = $('#dismiss-button').attr('value');
            const url = "./admin/users/requests";

            let requestData = {
                "id": user_id,
                "applied": false
            }

            $.ajax({
                url: url,
                type: 'post',
                data: JSON.stringify(requestData),
                contentType: "application/json;charset=UTF-8",
                processData: false
            }).then(function () {
                $('.users-table-row').remove();
                all_users();
                users_for_admin();
            });
}