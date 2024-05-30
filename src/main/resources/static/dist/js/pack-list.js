$(function () {

    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    $("#jqGrid").jqGrid({
        // 设置API
        url: '/pack/list',
        datatype: "json",
        colModel: [
            // 设置列表表头
            {label: 'ID', name: 'id', index: 'id', width: 20, key: true, hidden: false},
            {label: 'Sender Phone', name: 'senderPhone', index: 'senderPhone', width: 30},
            {label: 'Receiver Phone', name: 'receiverPhone', index: 'receiverPhone', width: 30},
            {label: 'Type', name: 'packType', index: 'packType', width: 30, editable: true, formatter: typeFormatter},
            {label: 'Status', name: 'status', index: 'status', width: 30, editable: true, formatter: statusFormatter},
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
        onSelectRow: function () {
            //返回选中的id
            let selectedRowIndex = $("#" + this.id).getGridParam('selrow');
            //返回点击这行xlmc的值
            window.location.href="/pack-details.html?id=" + selectedRowIndex;
        },
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

});

/**
 * jqGrid 重新加载
 */
function reload() {
    let page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
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
