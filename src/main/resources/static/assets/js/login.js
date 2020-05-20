

function editPassword(){
    var currentPassword = $("#current-password").val();
    var newPsw = $("#new-password").val();
    var newPswRepeat=$("#new-password-repeat").val();
    var username = $("#username").html();
    if(currentPassword.length==0||newPsw.length==0||newPswRepeat.length==0){
        alert("参数不全");
        return;
    }
    if(newPswRepeat!=newPsw){
        alert("两次输入的密码不一致");
        return;
    }
    datas = {
        "username":username,
        "password":currentPassword,
        "newPassword":newPsw
    };
    $.ajax({
        url: '/api/security/edit-password',
        data:JSON.stringify(datas),
        type: 'POST',
        charset: 'UTF-8',
        contentType:'application/json',
        success: function (data) {
            if(data["success"]){
                alert("修改成功");
                window.location="/index";
            }else {
                alert(data["msg"]);
            }
        }
    });
}