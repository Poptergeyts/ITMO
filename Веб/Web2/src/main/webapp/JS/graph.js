const canvas = document.getElementById("graph");
const context = canvas.getContext("2d");
canvas.width = 300;
canvas.height = 300;
const width = canvas.width;
const height = canvas.height;

function draw_graph() {
    const canvas = document.getElementById("graph");
    const context = canvas.getContext("2d");
    canvas.width = 300;
    canvas.height = 300;
    const width = canvas.width;
    const height = canvas.height;

// прямоугольник
    context.fillStyle = "rgba(33, 33, 33)";
    context.beginPath();
    context.rect(0, 0, width, height);
    context.stroke();
    context.fill();

    context.fillStyle = "rgba(97, 97, 97)";
    context.strokeStyle = "rgba(0, 0, 0, 0)";

    context.beginPath();
    context.rect(width * .5, height * .32, width * .36, height * .18);
    context.stroke();
    context.fill();

// треугольник
    context.beginPath();
    context.moveTo(width * .5, height * .5);
    context.lineTo(width * .14, height * .5);
    context.lineTo(width * .5, height * .868);
    context.lineTo(width * .5, height * .5);
    context.stroke();
    context.fill();

// четверть круга
    context.beginPath();
    context.arc(width * .5, height * .5, width * .18, 0, Math.PI / 2, false);
    context.stroke();
    context.fill();
    context.beginPath();
    context.moveTo(width * .5, height * .5);
    context.lineTo(width * .683, height * .5);
    context.lineTo(width * .5, height * .683);
    context.lineTo(width * .5, height * .5);
    context.stroke();
    context.fill();

// Текст
    context.fillStyle = "rgba(255, 255, 255)";
    context.strokeStyle = "rgba(255, 255, 255)";

    context.font = "0.8rem mono";
    context.fillText("y", width * .53, height * .025);
    context.fillText("x", width * .975, height * .47);
    context.fillText("R", width * .53, height * .152);
    context.fillText("R/2", width * .53, height * .33);
    context.fillText("-R/2", width * .53, height * .687);
    context.fillText("-R", width * .53, height * .868);
    context.fillText("R", width * .85, height * .47);
    context.fillText("R/2", width * .653, height * .47);
    context.fillText("-R/2", width * .281, height * .47);
    context.fillText("-R", width * .118, height * .47);

// Ось Y
    context.beginPath();
    context.moveTo(width * .5, 0);
    context.lineTo(width * .5, height);
    context.stroke();

// Ось X
    context.beginPath();
    context.moveTo(0, height * .5);
    context.lineTo(width, height * .5);
    context.stroke();

// Засечки
    context.beginPath();
    context.moveTo(width * .485, height * .14);
    context.lineTo(width * .515, height * .14);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .32);
    context.lineTo(width * .515, height * .32);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .68);
    context.lineTo(width * .515, height * .68);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .86);
    context.lineTo(width * .515, height * .86);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .14, height * 0.485);
    context.lineTo(width * .14, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .32, height * 0.485);
    context.lineTo(width * .32, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .68, height * 0.485);
    context.lineTo(width * .68, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .86, height * 0.485);
    context.lineTo(width * .86, height * .515);
    context.stroke();
}

function clearCanvas() {
    const canvas = document.getElementById("graph");
    const context = canvas.getContext("2d");
    context.clearRect(0, 0, canvas.width, canvas.height);
    canvas.width = 300;
    canvas.height = 300;
    const width = canvas.width;
    const height = canvas.height;

    context.fillStyle = "rgba(33, 33, 33)";
    context.beginPath();
    context.rect(0, 0, width, height);
    context.stroke();
    context.fill();

    context.fillStyle = "rgba(97, 97, 97)";
    context.strokeStyle = "rgba(0, 0, 0, 0)";

    context.beginPath();
    context.rect(width * .5, height * .32, width * .36, height * .18);
    context.stroke();
    context.fill();

// треугольник
    context.beginPath();
    context.moveTo(width * .5, height * .5);
    context.lineTo(width * .14, height * .5);
    context.lineTo(width * .5, height * .868);
    context.lineTo(width * .5, height * .5);
    context.stroke();
    context.fill();

// четверть круга
    context.beginPath();
    context.arc(width * .5, height * .5, width * .18, 0, Math.PI / 2, false);
    context.stroke();
    context.fill();
    context.beginPath();
    context.moveTo(width * .5, height * .5);
    context.lineTo(width * .683, height * .5);
    context.lineTo(width * .5, height * .683);
    context.lineTo(width * .5, height * .5);
    context.stroke();
    context.fill();

// Текст
    context.fillStyle = "rgba(255, 255, 255)";
    context.strokeStyle = "rgba(255, 255, 255)";

    context.font = "0.8rem mono";
    context.fillText("y", width * .53, height * .025);
    context.fillText("x", width * .975, height * .47);
    context.fillText(parseFloat((r * 1).toFixed(3)), width * .53, height * .152);
    context.fillText(parseFloat((r/2).toFixed(3)), width * .53, height * .33);
    context.fillText(parseFloat((-r/2).toFixed(3)), width * .53, height * .687);
    context.fillText(parseFloat((-r * 1).toFixed(3)), width * .53, height * .868);
    context.fillText(parseFloat((r * 1).toFixed(3)), width * .85, height * .47);
    context.fillText(parseFloat((r/2).toFixed(3)), width * .653, height * .47);
    context.fillText(parseFloat((-r/2).toFixed(3)), width * .281, height * .47);
    context.fillText(parseFloat((-r * 1).toFixed(3)), width * .118, height * .47);

// Ось Y
    context.beginPath();
    context.moveTo(width * .5, 0);
    context.lineTo(width * .5, height);
    context.stroke();

// Ось X
    context.beginPath();
    context.moveTo(0, height * .5);
    context.lineTo(width, height * .5);
    context.stroke();

// Засечки
    context.beginPath();
    context.moveTo(width * .485, height * .14);
    context.lineTo(width * .515, height * .14);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .32);
    context.lineTo(width * .515, height * .32);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .68);
    context.lineTo(width * .515, height * .68);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .485, height * .86);
    context.lineTo(width * .515, height * .86);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .14, height * 0.485);
    context.lineTo(width * .14, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .32, height * 0.485);
    context.lineTo(width * .32, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .68, height * 0.485);
    context.lineTo(width * .68, height * .515);
    context.stroke();
    context.beginPath();
    context.moveTo(width * .86, height * 0.485);
    context.lineTo(width * .86, height * .515);
    context.stroke();
}

function drawPoint(_x, _y, rad) {
    const canvas = document.getElementById("graph");
    const context = canvas.getContext("2d");
    const r = 2.5;
    x_point = (0.5 + (0.36 * ( _x / rad ))) * 300;
    y_point = (0.5 + (0.36 * ( (-1) * _y / rad ))) * 300;
    context.beginPath();
    context.arc(x_point, y_point, r, 0, 2 * Math.PI);
    context.fillStyle = "rgba(255, 255, 0)";
    context.fill();
}

function drawPoints(rad) {
    const canvas = document.getElementById("graph");
    const context = canvas.getContext("2d");
    const r = 2.5;
    for (let i = 0; i < points.length; i++) {
        x_point = (0.5 + (0.36 * (points[i][0] / rad))) * 300;
        y_point = (0.5 + (0.36 * ((-1) * points[i][1] / rad))) * 300;
        context.beginPath();
        context.arc(x_point, y_point, r, 0, 2 * Math.PI);
        context.fillStyle = points[i][2];
        context.fill();
    }
}