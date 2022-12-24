window.get_user = get_user;

function get_user(){
    $.getJSON("./user", function(result) {
        var roles = result.roles;

        var role_text = "";

        $.each(roles, function (index, value) {
            role_text += value.role.substring(5) + " ";
            if (value.role === "ROLE_ADMIN") {
                isAdmin = true;
            }
        });

        $('#user-id').append(result.id);
        $('#user-name').append(result.name);
        $('#user-username').append(result.username);
        $('#user-email').append(result.email);
        $('#user-age').append(result.age);
        $('#user-roles').append(role_text.trim());

        if (result.requestForAdmin == 0) {
            $('#request-for-admin-button').removeClass('invisible');
            $('#request-for-admin-button').attr('value', result.id);
        }
    });
}

$(get_user());