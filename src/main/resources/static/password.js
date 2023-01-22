$(function () {
    $('#telegramUsernameButton').click(
        function () {
            let telegramUsername = $("#telegramUsernameInput").val();
            let data = {
                "telegramUsername": telegramUsername
            }

            $.ajax({
                url: "./code",
                type: 'get',
                data: data,
                success: function () {
                    $("#telegramUsernameForCode").attr("value", telegramUsername);
                    $("#telegramUsernameForPassword").attr("value", telegramUsername);
                    $("#telegramUsernameModal").removeClass("show");
                    $("#telegramUsernameModal").attr("style", "display: none;");
                    $("#telegramCodeModal").addClass("show");
                    $("#telegramCodeModal").attr("style", "display: block;");
                },
                error: function () {
                    $("#telegramUsernameForm").append("<div style=\"color: red\">Пользователь с таким никнеймом в Telegram не зарегистрирован</div>");
                }
            });
        }
    );
});

$(function () {
    $('#telegramCodeButton').click(
        function () {
            let telegramUsername = $("#telegramUsernameForCode").val();
            let telegramCode = $("#telegramCode").val();
            let data = {
                "telegramUsername": telegramUsername,
                "telegramCode": telegramCode
            };

            $.ajax({
                url: "./code",
                type: 'post',
                data: JSON.stringify(data),
                contentType: "application/json;charset=UTF-8",
                processData: false,
                success: function () {
                    $("#telegramCodeModal").removeClass("show");
                    $("#telegramCodeModal").attr("style", "display: none;");
                    $("#resetPasswordModal").addClass("show");
                    $("#resetPasswordModal").attr("style", "display: block;");
                },
                error: function () {
                    $("#telegramCodeForm").append("<div style=\"color: red\">Неправильный код</div>");
                }
            });
        }
    );
});

$(function () {
    $('#resetPasswordButton').click(
        function () {
            const url = "./password";
            let telegramUsername = $("#telegramUsernameForPassword").val();
            let newPassword = $("#newPassword").val();
            let data = {
                "telegramUsername": telegramUsername,
                "password": newPassword
            };

            $.ajax({
                url: url,
                type: 'post',
                data: JSON.stringify(data),
                contentType: "application/json;charset=UTF-8",
                processData: false,
                success: function () {
                    location.reload();
                }
            });
        }
    );
});