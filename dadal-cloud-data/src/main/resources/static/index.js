let MAX_LENGTH = 20; // 最大数量
let temperature = [];
let voltage = [];
let temperatureCharts = echarts.init(document.querySelector('#temperature'));
let voltageCharts = echarts.init(document.querySelector('#voltage'));

/**
 * 初始化图示
 * @param {Echarts} charts 图示
 * @param {String[]} signalData 设备名数组
 * @param {String} title 图示标题
 * @param {Number} num 最大显示数量
 */
let initChart = (charts, signalData, title, num = 20) => {
  let xData = [];
  MAX_LENGTH = num; // 修改最大数据量
  for (let i = num; i >= 0; i--) xData.push(i);
  charts.setOption({
    title: {
      text: title
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: signalData
    },
    grid: {
      top: 50,
      left: 50,
      bottom: 50
    },
    xAxis: [{
      type: 'time',
    }, ],
    yAxis: [{
      type: 'value',
      scale: true
    }],
  });
}

let pushData = (data, arr) => {
  if (arr.length == MAX_LENGTH) {
    arr.shift();
    arr.push(data);
  } else {
    arr.push(data);
  }
}

initChart(temperatureCharts, ['设备1', '设备2', '设备3'], '温度');
initChart(voltageCharts, ['设备1', '设备2', '设备3'], '电压');

let sendConnected = event => {
  // 订阅队列
  sendClient.subscribe("/topic/msg", function (payload) {
    let obj = JSON.parse(payload.body);
    let now = new Date();
    console.log(payload.body);
    pushData({
      name: now.toString(),
      value: [
        now,
        parseFloat(obj.temperature)
      ]
    }, temperature);
    pushData({
      name: now.toString(),
      value: [
        now,
        parseFloat(obj.voltage)
      ]
    }, voltage)
    temperatureCharts.setOption({
      series: [{
          name: '设备1',
          type: 'line',
          smooth: true,
          data: temperature,
        },
      ]
    });
    voltageCharts.setOption({
      series: [{
        name: '设备1',
        type: 'line',
        smooth: true,
        data: voltage
      }]
    })
  });
};

let sendError = err => {
  console.log("sendError: Connect to server failed " + err);
}

let connect = () => {
  sendClient = Stomp.over(new SockJS("http://47.111.134.50:8200/ws"));
  sendClient.connect('guest', 'guest', sendConnected, sendError, '/');
}

connect(); // 连接