$(function(){
    $.getJSON("./user", function(result) {
        var authorized_user = result;

        var roles = authorized_user.roles;
        var isAdmin = false;

        var role_text = "";

        $.each(roles, function (index, value) {
            role_text += value.role.substring(5) + " ";
            if (value.role === "ROLE_ADMIN") {
                isAdmin = true;
            }
        });

        if (isAdmin == true) {
            $('#request-for-admin-button').addClass('invisible');
            $('#sidebar-items').append('<li class="nav-item">\n' +
                '                        <a href="/admin_page" class="nav-link" aria-current="page">\n' +
                '                            Admin\n' +
                '                        </a>\n' +
                '                    </li>');
            $('#sidebar-items').append('<li class="nav-item">\n' +
                '                        <a href="#" class="nav-link active" aria-current="page">\n' +
                '                            User\n' +
                '                        </a>\n' +
                '                    </li>');
        }
        else {
            $('#sidebar-items').append('<li class="nav-item">\n' +
                '                        <a href="#" class="nav-link active" aria-current="page">\n' +
                '                            User\n' +
                '                        </a>\n' +
                '                    </li>');
            $('#sidebar-items').append('<li class="nav-item">\n' +
                '                        <a href="/payment_page" class="nav-link" aria-current="page">\n' +
                '                            Pay\n' +
                '                        </a>\n' +
                '                    </li>');
        }

        $('#navbar-email').append('<b>' + authorized_user.email + '</b> with roles ' + role_text.trim())
    });
});