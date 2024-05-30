function submitButtonClick() {

    // 获取表单数据
    let senderName = $("#senderName").val();
    let senderPhone = $("#senderPhone").val();
    let senderWorkNodeId = $("#senderWorkNodeId").val();

    let receiverName = $("#receiverName").val();
    let receiverPhone = $("#receiverPhone").val();
    let receiverWorkNodeId = $("#receiverWorkNodeId").val();

    let packType = $("#type").val();


    // 将即将发送数据封装为Json, 和 Pojo 对应
    let data = {
        "senderName": senderName,
        "senderPhone": senderPhone,
        "senderWorkNodeId": senderWorkNodeId,
        "receiverName": receiverName,
        "receiverPhone": receiverPhone,
        "receiverWorkNodeId": receiverWorkNodeId,
        "packType": packType
    };

    let url = "/pack/create";
    let method = "POST";

    document.getElementById("submitButton").disabled = true;
    document.getElementById("submitButton").innerHTML = "Submitting...";

    // 执行方法
    $.ajax({
        type: method,           //方法类型
        dataType: "json",       //预期服务器返回的数据类型
        url: url,               //url
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (r) {
            if (r.code === 0) {
                alert("Submitted successfully!");
                window.location.href = "/pack-list.html";
            } else {
                swal(r.msg, {
                    icon: "error",
                });
                document.getElementById("submitButton").disabled = false;
                document.getElementById("submitButton").innerHTML = "Submit";
            }
        },
        error: function () {
            swal("Operation failure, please contact the support!", {
                icon: "error",
            });
            document.getElementById("submitButton").disabled = false;
            document.getElementById("submitButton").innerHTML = "Submit";
        }
    });
}

