const yReg1 = new RegExp("(-3|\\+?5)$");
const yReg2 = new RegExp("-[0-2](\\.([0-9]+)?)?$");
const yReg3 = new RegExp("\\+?[0-4](\\.([0-9]+)?)?$");
const yRegSign = new RegExp("(\\+|-)$")

const rReg1 = new RegExp("(1|\\+?4)$");
const rReg2 = new RegExp("\\+?[1-3](\\.([0-9]+)?)?$");
const rRegSign = new RegExp("(\\+|-)$")

const canvas = document.getElementById("graph");
const context = canvas.getContext("2d");
canvas.width = 300;
canvas.height = 300;
const width = canvas.width;
const height = canvas.height;

let y, r, submit, x;

window.addEventListener("load", () => {
    x = 100;
    y = document.getElementById("Y");
    r = document.getElementById("R");

    submit = document.getElementById("submit")


    y.addEventListener("input", updateStatus)
    r.addEventListener("input", updateStatus)

    draw_graph()
})

function updateStatus() {
    if (validate()) {
        draw_point(x, parseInt(y?.value), parseInt(r?.value));
    }
}

function validate() {
    while ((!validateY(y?.value) && y?.value && y.value.search(yRegSign) !== 0) ||
    (!validateR(r?.value) && r?.value && r.value.search(rRegSign) !== 0)) {
        if ((!validateY(y?.value) && y?.value && y.value.search(rRegSign) !== 0)) {
            y.value = y?.value?.slice(0, -1);
        } else {
            r.value = r?.value?.slice(0, -1);
        }
    }
    return validateY(y?.value) && validateR(r?.value) && x !== 100
}

function validateY(value) {
    if (value === null || value === undefined) return false
    return value.search(yReg1) === 0 || value.search(yReg2) === 0 || value.search(yReg3) === 0;
}

function validateR(value) {
    if (value === null || value === undefined) return false
    return value.search(rReg1) === 0 || value.search(rReg2) === 0;
}

function onlyOne(checkbox) {
    const checkboxes = document.getElementsByName('check');
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false
        else x = item.value
    })
    updateStatus()
}


function draw_point(xPoint, yPoint, r) {
    draw_graph()
    context.fillStyle = "rgba(917, 97, 97)";
    context.strokeStyle = "rgba(0, 0, 0, 0)";

    context.beginPath();
    context.rect(width * .5 - width * 0.01 + xPoint / r * width * .36,
        height * .5 - height * 0.01- yPoint / r * height * .36, width * .02, height * .02);
    context.stroke();
    context.fill();
}


function draw_graph() {
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
