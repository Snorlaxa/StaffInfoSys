var abilityChart = echarts.init(document.getElementById('ability'));
var id = $('#staffId').html();
var names = new Array();
var job = new Array();
var staff = new Array();

console.log(id);
$.ajax({
    url: '/api/score/allInfo',
    data: {"id": id},
    dataType: 'json',
    type: 'GET',
    success: function (data) {
        if (data["success"]) {
            var res = data["data"];
            names = res['name'];
            staff = res['staff'];
            job = res['job'];
            // for(var i=0;i<res['name'].length;i++){
            //     name.push(name01[i]);
            //     job.push(job01[i]);
            //     staff.push(staff01[i]);
            // }
            console.log(names);
            var score = document.getElementById('score');
            for (var i = 0; i < names.length; i++) {
                score.innerHTML += " <li>\n" +
                    "                                    <strong>"+names[i]+"</strong>\n" +
                    "                                    <br>\n" +
                    "                                    <input type=\"text\" name=\""+names[i]+"\" value=\""+staff[i]+"\" />\n" +
                    "                                </li>"
            }
            initSlider();
            // var jobs = document.getElementById("jobs");
            // $("#jobs").empty();
            // if(department.length == 0){
            //     jobs.options.add(new Option("选择岗位",""));
            // }
            // for(var i=0;i<res.length;i++){
            //     jobs.options.add(new Option(res[i]["name"],res[i]["id"]));
            // }
            initChart(names, job, staff);
        }
    }
});


function initSlider() {

    $('#score').find("input").each(function (index,element) {
        $(this).ionRangeSlider({
            type:"single",
            min: 0,
            max: 100,
            hide_min_max: true,
            grid: true,
            grid_num:1,//网格间隔
            onChange: function (data) {
                var n = data.input[0].name;
                staff[names.indexOf(n)]=data.from;
                initChart(names,job,staff);
            }
        });
    })
}

function initChart(tecName, jobData, staffData) {

    var optionData = {
        title: {
            text: '职业技能评分',
            subtext: '百分制'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['人员能力', '岗位需求']
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
                name: '人员能力',
                type: 'bar',
                color: '#2fc604',
                data: staffData
            },
            {
                name: '岗位需求',
                color: '#009dff',
                type: 'bar',
                data: jobData
            }
        ], tooltip: {
            trigger: 'axis',
            formatter(params) {
                console.log(params[0].data - params[1].data);
                var color = params[0].color;
                var color2 = params[1].color;
                var htmlStr = '<div>';
                htmlStr += params[0].name + '<br/>';
                htmlStr += '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:' + color + ';"></span>';

                htmlStr += params[0].seriesName + ': ' + params[0].value + '分 <br/>';
                htmlStr += '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:' + color2 + ';"></span>';
                htmlStr += params[1].seriesName + ': ' + params[1].value + '分 <br/>';
                htmlStr += '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:#c30700;"></span>';
                htmlStr += '得分：' + (params[0].value - params[1].value);
                htmlStr += '</div>';
                return htmlStr;
            }
        }
    };

    abilityChart.setOption(optionData);
}