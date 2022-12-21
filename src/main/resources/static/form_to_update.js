$(function(){
    const editModal = document.getElementById('editModal');

    editModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const user_id = button.getAttribute('data-bs-whatever');

        const url = "./admin/update_user/" + user_id;

        $.getJSON(url, function (userData) {
            $('#userForUpdate-id').val(userData.id);
            $('#userForUpdate-name').val(userData.name);
            $('#userForUpdate-username').val(userData.username);
            $('#userForUpdate-email').val(userData.email);
            $('#userForUpdate-age').val(userData.age);
        });

        $('#edit-button').attr('value', user_id);
    });
});