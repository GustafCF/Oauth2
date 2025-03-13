$(document).ready(function () {
    $('#register-form').submit(function (event) {
        event.preventDefault();

        const registerData = {
            username: $('#username').val(),
            password: $('#password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/us/insert',
            contentType: 'application/json',
            data: JSON.stringify(registerData),
            success: function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Usuário cadastrado com sucesso!',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    window.location.href = '/index';
                });
            },
            error: function (xhr) {
                let errorMessage = 'Erro ao realizar cadastro. Tente novamente.';
                if (xhr.responseJSON && xhr.responseJSON.message) {
                    errorMessage = xhr.responseJSON.message;
                }

                Swal.fire({
                    icon: 'error',
                    title: 'Erro',
                    text: errorMessage,
                    confirmButtonColor: '#00060c',
                    confirmButtonText: 'OK'
                });
            }
        });
    });
});
