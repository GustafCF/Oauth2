$(document).ready(function () {
    $('#login-form').off('submit').submit(function (event) {
        event.preventDefault();

        const loginData = {
            username: $('#username').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/auth/login',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Login realizado com sucesso!',
                    text: 'Você será redirecionado em breve.',
                    showConfirmButton: false,
                    timer: 2000
                }).then(() => {
                    window.location.href = '/dashboard';
                });
            },
            error: function (xhr) {
                let errorMessage = 'Erro ao realizar o login. Tente novamente.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }

                Swal.fire({
                    icon: 'error',
                    title: 'Erro!',
                    text: errorMessage,
                    confirmButtonText: 'OK'
                });
            }
        });
    });
});
