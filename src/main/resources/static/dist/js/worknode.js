$(function () {

    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    $("#jqGrid").jqGrid({
        // 设置API
        url: '/worknode/list',
        datatype: "json",
        colModel: [
            // 设置列表表头
            {label: 'ID', name: 'id', index: 'id', width: 20, key: true, hidden: false},
            {label: 'Type', name: 'type', index: 'type', width: 30, editable: true, formatter: typeFormatter},
            {label: 'Coordinate X', name: 'coordinateX', index: 'coordinateX', width: 30},
            {label: 'coordinate Y', name: 'coordinateY', index: 'coordinateY', width: 30},
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
    1. Station
    2. Center
    3. Airport
 */
function typeFormatter(cellValue) {
    if (cellValue === 1) {
        return "Station";
    } else if (cellValue === 2) {
        return "Center";
    } else if (cellValue === 3) {
        return "Airport";
    }
    return cellValue;
}

