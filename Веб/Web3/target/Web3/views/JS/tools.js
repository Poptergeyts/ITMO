const yReg1 = new RegExp("(-3|\\+?5)$");
const yReg2 = new RegExp("-[0-2](\\.([0-9]+)?)?$");
const yReg3 = new RegExp("\\+?[0-4](\\.([0-9]+)?)?$");
const yRegSign = new RegExp("(\\+|-)$")

const rReg1 = new RegExp("(1|\\+?4)$");
const rReg2 = new RegExp("\\+?[1-3](\\.([0-9]+)?)?$");
const rRegSign = new RegExp("(\\+|-)$")

let y_valid, r_valid

let r = 0
let points = [];

window.addEventListener('load', () => {
    for (let i = 1; i < sessionStorage.length; i++) {
        let element = sessionStorage.getItem(i);
        let elements = element.split("@");
        points.push([parseFloat(elements[0]), parseFloat(elements[1]), elements[2]])
    }
    if (points.length !== 0) {
        r = sessionStorage.getItem("r")
        clearCanvas()
        drawPoints()
    } else {
        r = 2
        sessionStorage.setItem("r", r);
        drawCanvas()
    }

    let r_valid = document.getElementById("t2")
    let y_valid = document.getElementById("t1")
    r_valid.addEventListener("input", validate)
    y_valid.addEventListener("input", validate)
})

function validate() {
    while ((!validateY(y_valid?.value) && y_valid?.value && y_valid.value.search(yRegSign) !== 0) ||
    (!validateR(r_valid?.value) && r_valid?.value && r_valid.value.search(rRegSign) !== 0)) {
        if ((!validateY(y_valid?.value) && y_valid?.value && y_valid.value.search(rRegSign) !== 0)) {
            y_valid.value = y_valid?.value?.slice(0, -1);
        } else {
            r_valid.value = r_valid?.value?.slice(0, -1);
        }
    }
    r = document.getElementById("t2").value;
    if (r === '' || isNaN(r - parseFloat(r))
        || parseFloat(r).toFixed(3) > 4 || parseFloat(r).toFixed(3) < 1) {
        r = 2;
    }
    clearCanvas()
    drawPoints()
}

function validateY(value) {
    if (value === null || value === undefined) return false
    return value.search(yReg1) === 0 || value.search(yReg2) === 0 || value.search(yReg3) === 0;
}

function validateR(value) {
    if (value === null || value === undefined) return false
    return value.search(rReg1) === 0 || value.search(rReg2) === 0;
}

var canva = document.getElementById("canvas");

// Обработчик события клика на холсте
canva.addEventListener("click", function(event) {
    if (r === 0) {
        r = 2;
    }
    var rect = canva.getBoundingClientRect();
    var x = event.clientX - rect.left;
    var y = event.clientY - rect.top;

    var x_number = ((r * ((x / 300) - (1 / 2))) / 0.36);
    var y_number = parseFloat(((-r * ((y / 300) - (1 / 2))) / 0.36).toFixed(3));

    if (x_number < -2) {
        x_number = -2;
    }
    if (x_number > 2) {
        x_number = 2;
    }

    let i = 0;
    while (i < 9){
        var elementX = document.getElementById("t0:" + i);
        if (elementX.value - x_number >= -0.25 && elementX.value - x_number <= 0.25){
            elementX.checked = true;
            x_number = elementX.value
            break;
        }
        i++;
    }

    document.getElementById("t1").value = y_number.toString();
    document.getElementById("t2").value = r;
    clearCanvas()
    if (checkPoint(x_number, y_number, r)){
        drawPoint(x_number, y_number, r, "lime");
    } else{
        drawPoint(x_number, y_number, r, "red");

    }
    drawPoints()
});

function drawPoint(x, y, rad, color) {
    const canvas = document.getElementById("canvas");
    const context = canvas.getContext("2d");
    const r = 2.5; // радиус точки
    x_point = (0.5 + (0.36 * ( x / rad ))) * 300;
    y_point = (0.5 + (0.36 * ( (-1) * y / rad ))) * 300;
    // Отрисовка точки
    context.beginPath();
    context.arc(x_point, y_point, r, 0, 2 * Math.PI);
    context.fillStyle = color;
    context.fill();
}

function checkPoint(x, y, r){
    let rect = (x <= r && x >= 0) && (y <= r / 2.0 && y >=0);
    let tf1 = -(r) * (-y)
    let tf2 = (-x) * (-r)
    let tf3 = (-x) * (r) - (-r) * (-r - y)
    let tri = (tf3 >= 0 && tf2 >= 0 && tf1 >= 0) || (tf3 <= 0 && tf2 <= 0 && tf1 <= 0);
    let circle = (x >= 0 && y <= 0) && ((x * x) + (y * y)) <= r * r / 4.0;
    return rect || tri || circle;
}

function savePoint() {
    let i = 0;
    let x_number;
    while (i < 9) {
        var elementX = document.getElementById("t0:" + i);
        if (elementX.checked === true) {
            x_number = elementX.value
            break;
        }
        i++;
    }
    let y_number = document.getElementById("t1").value
    let color;
    if (checkPoint(x_number, y_number, r)) {
        color = "lime";
    } else {
        color = "red";

    }
    r = document.getElementById("t2").value
    points.push([x_number, y_number, color]);
    sessionStorage.setItem(sessionStorage.length, x_number + "@" + y_number + "@" + color);
    sessionStorage.setItem("r", r)
    clearCanvas()
    drawPoints();
}

function clearPoints() {
    points = []
    sessionStorage.clear()
}

function drawPoints() {
    const context = canva.getContext("2d");
    let x_point;
    let y_point;
    for (let i = 0; i < points.length; i++) {
        x_point = (0.5 + (0.36 * (points[i][0] / r))) * 300;
        y_point = (0.5 + (0.36 * ((-1) * points[i][1] / r))) * 300;
        context.beginPath();
        context.arc(x_point, y_point, 2.5, 0, 2 * Math.PI);
        context.fillStyle = points[i][2];
        context.fill();
    }
}