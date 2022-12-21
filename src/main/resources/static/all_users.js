window.all_users = all_users;

function all_users(){

    $.getJSON("./admin/users", function(data) {
        $.each(data, function (index, user) {
            let role_text = "";

            $.each(user.roles, function (index, value) {
                role_text += value.role.substring(5) + " ";
            });

            $('#users_table').append('<tr class="users-table-row">\n' +
                '                  <td id="id">' + user.id + '</td>\n' +
                '                  <td id="name">' + user.name + '</td>\n' +
                '                  <td id="username">' + user.username + '</td>\n' +
                '                  <td id="email">' + user.email + '</td>\n' +
                '                  <td id="age">' + user.age + '</td>\n' +
                '                  <td id="roles">' + role_text.trim() + '</td>\n' +
                '                  <td id="edit"><button type="button" class="btn btn-primary" role="button" data-bs-toggle="modal" data-bs-target="#editModal" data-bs-whatever="' + user.id + '">Edit</button></td>\n' +
                '                  <td id="delete"><button type="button" class="btn btn-danger" role="button" data-bs-toggle="modal" data-bs-target="#deleteModal" data-bs-whatever="' + user.id + '">Delete</button></td>\n' +
                '                  </tr>')
        });
    });
}

$(all_users());