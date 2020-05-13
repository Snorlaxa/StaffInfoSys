var abilityChart = echarts.init(document.getElementById('ability'));
var id = $('#staffId').html();

function initchart(tecName, jobData, staffData){
    var nameArray = new Array();
    for(var i=0;i<tecName.length;i++){
        nameArray.push({name:tecName[i],max:100});
    }
    optiondata = {
        tooltip: {},
        legend: {
            data: ['人才能力', '岗位要求']
        },
        radar: {
            // shape: 'circle',
            indicator: nameArray
        },
        series: [{
            name: '人才能力 vs 岗位要求',
            type: 'radar',
            // areaStyle: {normal: {}},
            data : [
                {
                    value : staffData,
                    name : '人才能力',
                    itemStyle: {
                        normal: {
                            color: 'rgba(5, 128, 242, 0.8)'
                        }
                    },
                    areaStyle: {
                        normal: {
                            color: '#37A2DA'
                        }
                    }
                },
                {
                    value : jobData,
                    name : '岗位要求',
                    itemStyle: {
                        normal: {
                            color: 'rgba(195,7,0,0.8)'
                        }
                    },
                }
            ]
        }]
    };

    abilityChart.setOption(optiondata);
}
$.ajax({
    url: '/api/score/allInfo',
    data: {"id": id},
    dataType: 'json',
    type: 'GET',
    success: function (data) {
        if (data["success"]) {
            var res = data["data"];
            initchart(res['name'], res['job'], res['staff']);
        }
    }
});

