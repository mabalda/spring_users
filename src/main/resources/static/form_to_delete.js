$(function(){
    const deleteModal = document.getElementById('deleteModal');

    deleteModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget;
        const user_id = button.getAttribute('data-bs-whatever');

        const url = "./admin/users/" + user_id;

        $.getJSON(url, function (userData) {
            $('#userForDelete-id').val(userData.id);
            $('#userForDelete-name').val(userData.name);
            $('#userForDelete-username').val(userData.username);
            $('#userForDelete-email').val(userData.email);
            $('#userForDelete-age').val(userData.age);
        });

        $('#delete-button').attr('value', user_id);
    });
});