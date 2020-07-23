// let machineID = window.Android.getString(); // 获取设备 ID
//let token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiYWNjb3VudF9pZCI6ImJmZjhhYzRjZmZkMTQxMGNiOTIzZTMzYWMwZWY2OGQwIiwiaXNzIjoieXVhbndlaW1pbiIsImV4cCI6MTU2MTQ1MTEzN30.cF4R5ZI9wy2MbbXy6bu_uVNbqRcarzpNpoE2MoGwS-4';
let token = window.Token.getString();
let container = document.querySelector('#main'); // 获取 container
let charts = echarts.init(container);
let now = new Date(); // 今天
let yesterday = new Date(now - 1000 * 60 * 60 * 24); // 昨天的这个时刻

fetch('http://47.111.134.50:8200/api/status/' + yesterday.getTime() + '/' + now.getTime(), {
    headers: {
      'Content-type': 'application/x-www-form-urlencoded',
      'token': token
    },
    cache: 'no-cache',
    method: 'GET'
  })
  .then(res => res.json())
  .then(data => {
    let date = data.data.map(i => {
      let t = new Date(i.createTime);
      return [t.getHours(), t.getMinutes(), t.getMilliseconds()].join(':');
    });
    let elecTricdata = data.data.map(i => i.electric);
    let humiditydata = data.data.map(i => i.humidity);
    let temperaturedata = data.data.map(i => i.temperature);
    let voltage = data.data.map(i => i.voltage);
    let power = data.data.map(i => i.power);

    charts.setOption(option = {
      tooltip: {
        trigger: 'axis',
        position: function (pt) {
          return [pt[0], '10%'];
        }
      },
      
      legend: {
        data: ['电流', '湿度', '温度', '电压', '功耗']
      },
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: 'none'
          },
          restore: {},
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        data: date
      },
      yAxis: {
        type: 'value',
        scale: true
      },
      dataZoom: [{
        type: 'inside',
        start: 0,
        end: 10
      }, {
        start: 0,
        end: 10,
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        handleStyle: {
          color: '#fff',
          shadowBlur: 3,
          shadowColor: 'rgba(0, 0, 0, 0.6)',
          shadowOffsetX: 2,
          shadowOffsetY: 2
        }
      }],
      series: [{
          name: '电流',
          type: 'line',
          smooth: true,
          data: elecTricdata
        },
        {
          name: '湿度',
          type: 'line',
          smooth: true,
          data: humiditydata
        },
        {
          name: '温度',
          type: 'line',
          smooth: true,
          data: temperaturedata
        },
        {
          name: '电压',
          type: 'line',
          smooth: true,
          data: voltage
        },
        {
          name: '功耗',
          type: 'line',
          smooth: true,
          data: power
        }
      ]
    });
  })
  .catch(err => console.log(err));