var abilityChart = echarts.init(document.getElementById('ability'));
var id = $('#jobId').html();
function initchart(tecName, jobData){
    var nameArray = new Array();
    for(var i=0;i<tecName.length;i++){
        nameArray.push({name:tecName[i],max:100});
    }
    optiondata = {
        title: {
            text: '岗位技能需求图',
            subtext: '百分制'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['岗位需求']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: tecName
        },
        series: [
            {
                name: '岗位需求',
                color: '#009dff',
                type: 'bar',
                data: jobData
            }
        ], tooltip: {
            trigger: 'axis',
            formatter(params) {
                var color = params[0].color;
                var htmlStr = '<div>';
                htmlStr += params[0].name + '<br/>';
                htmlStr += '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:' + color + ';"></span>';
                htmlStr += params[0].seriesName + ': ' + params[0].value + '分 <br/>';
                htmlStr += '</div>';
                return htmlStr;
            }
        }
    };

    abilityChart.setOption(optiondata);
}
$.ajax({
    url: '/api/score/allJobInfo',
    data: {"id": id},
    dataType: 'json',
    type: 'GET',
    success: function (data) {
        if (data["success"]) {
            var res = data["data"];
            console.log(res);
            initchart(res['name'], res['job']);
        }
    }
});

