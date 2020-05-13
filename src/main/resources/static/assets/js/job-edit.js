var abilityChart = echarts.init(document.getElementById('ability'));
var id = $('#jobId').html();
var names = new Array();
var job = new Array();
var staff = new Array();
var ids = new Array();

// console.log(id);
$.ajax({
    url: '/api/score/allJobInfo',
    data: {"id": id},
    dataType: 'json',
    type: 'GET',
    success: function (data) {
        if (data["success"]) {
            var res = data["data"];
            names = res['name'];
            job = res['job'];
            ids = res['ids'];
            var score = document.getElementById('score');
            for (var i = 0; i < names.length; i++) {
                score.innerHTML += "<li id='"+ids[i]+"'>\n" +
                    "                                    <strong>"+names[i]+"</strong>\n" +
                    "                                    <a href='javascript:deleteAbility(\""+ids[i]+"\");' class='fa fa-trash-o fa-2x' style='color: #0078b9; float:right'></a>\n" +
                    "                                    <input type=\"text\" name=\""+names[i]+"\" value=\""+job[i]+"\" />\n" +
                    "                                </li>"
            }
            initSlider();
            initChart(names, job);
        }
    }
});

function deleteAbility(index){
    $('#score').find("li").each(function () {
        if(this.id==index){
            $(this).remove();
            var i = ids.indexOf(index);
            ids.splice(i,1);
            job.splice(i,1);
            names.splice(i,1);
            initChart(names,job);
        }
    })
}

function submitData() {
    var requirement = $("#requirement").val();
    var number = $("#number").val();
    var salary =$("#salary").val();
    var datas={
        "id":id,
        "requirement":requirement,
        "number":number,
        "salary":salary,
        "ids":ids,
        "score":job,
        "names":names
    }
    $.ajax({
        url: '/api/job/job-ability',
        data:JSON.stringify(datas),
        type: 'POST',
        charset: 'UTF-8',
        contentType:'application/json',
        success: function () {
            window.location="/job-single/"+id;
        }
    });
}

function addAbilityElement(){
    var score = document.getElementById('score');
    var abilityName = $('#new-ability-name').val();
    var abilityScore = $('#new-ability-score').val();
    var uuid = getUUID();
    ids.push(uuid);
    names.push(abilityName);
    job.push(abilityScore);
    $('#score').empty();
    for (var i = 0; i < names.length; i++) {
        score.innerHTML += "<li id='"+ids[i]+"'>\n" +
            "                                    <strong>"+names[i]+"</strong>\n" +
            "                                    <a href='javascript:deleteAbility(\""+ids[i]+"\");' class='fa fa-trash-o fa-2x' style='color: #0078b9; float:right'></a>\n" +
            "                                    <input type=\"text\" name=\""+names[i]+"\" value=\""+job[i]+"\" />\n" +
            "                                </li>"
    }
    initSlider();
    initChart(names, job);
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
                job[names.indexOf(n)]=data.from;
                initChart(names,job);
            }
        });
    })
}

function initChart(tecName, jobData) {

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

    abilityChart.setOption(optionData);
}

function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}
function getUUID() {
    return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
}