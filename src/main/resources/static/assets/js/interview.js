var abilityChart = echarts.init(document.getElementById('ability'));
var id = $('#staffId').html();
var names = new Array();
var job = new Array();
var staff = new Array();
var ids = new Array();

// console.log(id);
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
            ids = res['ids'];
            var score = document.getElementById('score');
            for (var i = 0; i < names.length; i++) {
                score.innerHTML += " <li>\n" +
                    "                                    <strong>"+names[i]+"</strong>\n" +
                    "                                    <br>\n" +
                    "                                    <input type=\"text\" name=\""+names[i]+"\" value=\""+staff[i]+"\" />\n" +
                    "                                </li>"
            }
            initSlider();
            initChart(names, job, staff);
        }
    }
});

function submitData() {
    var interviewComments = $("#interviewComments").val();
    var speciality = $("#speciality").val();
    var interviewers =$("#interviewers").val();
    var interviewScore = $("#interviewScore").val();
    var probation = $("#probation").val();
    var jobId = '';
    $.ajax({
        url: '/api/staff/'+id,
        dataType: 'json',
        type: 'GET',
        success: function (data) {
            if (data["success"]) {
                var res = data["data"];
                res['interviewComments']=interviewComments;
                res['interviewScore']=interviewScore;
                res['speciality']=speciality;
                res['interviewers']=interviewers;
                jobId = res['jobId'];
                console.log(jobId);
                res['probationScore']=probation.length==0?0:probation;
                var batch={
                    "staffParam":res,
                    "ids":ids,
                    "score":staff
                }
                $.ajax({
                    url: '/api/staff/interview-ability',
                    data:JSON.stringify(batch),
                    type: 'POST',
                    charset: 'UTF-8',
                    contentType:'application/json',
                    success: function () {
                        window.location="/staff-single/"+id;
                    }
                });
            }
        }
    });
}

function submitScore(jobId) {
    $.ajax({
        url: '/api/staff/',
        data:JSON.stringify(res),
        type: 'POST',
        contentType:'application/json',
        success: function () {
            window.location="/staff-list";
        }
    });
}


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