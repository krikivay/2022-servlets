function onBeforeDeleteUser(userId) {
    var shouldProceed = confirm('Are you sure to delete user with id ' + userId);

    if (shouldProceed) {
        fetch('http://localhost:8080/Servlets_war/deleteuser?deleteId=' + userId, {
            method: 'DELETE'
        });
    }
}
