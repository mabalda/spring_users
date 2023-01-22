$(function () {
    $('#payment-button').click(
        function () {
            const value = $("#amountValue").val();
            const description = $("#description").val();

            let data = {
                "value": value,
                "description": description
            }

            $.ajax({
                url: './payment',
                type: 'get',
                data: data,
                success: function (result) {
                    const confirmation = result.confirmation;
                    const confirmation_token = confirmation.confirmation_token;

                    const checkout = new window.YooMoneyCheckoutWidget({
                        confirmation_token: confirmation_token,
                        return_url: 'http://localhost:8080/user_page',
                        error_callback: function(error) {
                            console.log(error)
                        }
                    });

                    checkout.render('payment-form');
                    $('#requestForPaymentForm').hide();

                }
            });
        }
    )})