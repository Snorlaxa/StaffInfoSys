function signup() {
    var user = $('#user').val();
    var ps1 = $("#ps1").val();
    var ps2 = $('#ps2').val();
    var res = {
        "name": user,
        "password":ps1
    }
    if (ps1 != ps2) {
        return;
    }
    $.ajax({
        url: '/api/security/signup',
        data: JSON.stringify(res),
        dataType: 'json',
        type: 'POST',
        charset: 'UTF-8',
        contentType:'application/json',
        success: function (data) {
            if (data["success"]) {
                console.log(data);
                window.location="/login";
            }
        }
    });
}