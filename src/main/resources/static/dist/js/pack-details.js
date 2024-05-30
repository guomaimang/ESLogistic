let packId = getQueryParam("id");

$(function () {

    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    $("#jqGrid").jqGrid({
        // 设置API
        url: 'pack/records?id=' + packId,
        datatype: "json",
        colModel: [
            // 设置列表表头
            {label: 'Record ID', name: 'id', index: 'id', width: 10, key: true, hidden: false},
            {label: 'Message', name: 'message', index: 'message', width: 50},
            {label: 'Report Time', name: 'createTime', index: 'createTime', width: 20, editable: true, formatter: utcToLocalFormatter},
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: 'Information reading in progress...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.rows",
            records: "data.count",
            page: "data.currentPage",
            total: "data.totalPage",
        },
        prmNames: {
            page: "pageNum",
            rows: "pageSize",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        },
    });

    $("#searchButton").click(function(){
        let keyword = $("#searchInput").val(); //获取输入框的值
        $("#jqGrid").jqGrid('setGridParam',{
            postData: {'keyword': keyword}, //设置postData参数
            page: 1
        }).trigger("reloadGrid"); //重新加载JqGrid
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width() * 2);

    });

});

// reload the content
function reload() {
    let page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
    contentsPreparation();
}

// prepare the page
function contentsPreparation(){

    //请求基本数据
    $.ajax({
        url: "/pack/info",
        type: "GET",
        data: {
            id: packId
        },
        success: function(r) {
            if (r.code === 0 && r.data != null) {
                document.getElementById("ID").innerText = r.data.id;
                document.getElementById("senderName").innerText = r.data.senderName;
                document.getElementById("senderPhone").innerText = r.data.senderPhone;
                document.getElementById("receiverName").innerText = r.data.receiverName;
                document.getElementById("receiverPhone").innerText = r.data.receiverPhone;
                document.getElementById("packType").innerText = typeFormatter(r.data.packType);

                document.getElementById("senderStation").innerText = r.data.senderWorkNode.id;
                document.getElementById("currentNode").innerText = r.data.currentWorkNode.id;
                document.getElementById("receiverStation").innerText = r.data.receiverWorkNode.id;
                document.getElementById("status").innerText = statusFormatter(r.data.status);
            }else {
                swal(r.msg, {
                    icon: "error",
                });
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // handle error
            console.error("AJAX Error: ", textStatus, errorThrown);
        }
    });
}

function utcToLocalFormatter(cellValue) {
    let date = new Date(cellValue);
    return date.toLocaleString();

}

/*
    0: Waiting for pickup
    1: In workNode
    2: In transit
    3. need to be delivered
    4: Delivered
 */
function statusFormatter(cellValue) {
    switch (cellValue) {
        case 0:
            return "Waiting for pickup";
        case 1:
            return "In workNode";
        case 2:
            return "In transit";
        case 3:
            return "need to be delivered";
        case 4:
            return "Delivered";
    }
}

/*
 0: Standard
 1: Fast
 */
function typeFormatter(cellValue) {
    return cellValue == 1 ? "Express" : "Normal";
}

// run
contentsPreparation();

