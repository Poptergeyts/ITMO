const yReg1 = new RegExp("(-3|\\+?5)$");
const yReg2 = new RegExp("-[0-2](\\.([0-9]+)?)?$");
const yReg3 = new RegExp("\\+?[0-4](\\.([0-9]+)?)?$");
const yRegSign = new RegExp("(\\+|-)$")

const rReg1 = new RegExp("(1|\\+?4)$");
const rReg2 = new RegExp("\\+?[1-3](\\.([0-9]+)?)?$");
const rRegSign = new RegExp("(\\+|-)$")

let y_valid, x_valid, r_valid, submit;

window.addEventListener("load", () => {
    x_valid = 100;
    y_valid = document.getElementById("Y");
    r_valid = document.getElementById("R");

    submit = document.getElementById("submit")


    y_valid.addEventListener("input", validate)
    r_valid.addEventListener("input", validate)

    if (sessionStorage.length !== 0) {
        r = 2;
        clearCanvas();
        drawPoints(r);
    } else {
        draw_graph();
    }
})

function onlyOneX(checkbox) {
    const checkboxes = document.getElementsByName('check');
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false
        else x = item.value
    })
}

function validate() {
    while ((!validateY(y_valid?.value) && y_valid?.value && y_valid.value.search(yRegSign) !== 0) ||
    (!validateR(r_valid?.value) && r_valid?.value && r_valid.value.search(rRegSign) !== 0)) {
        if ((!validateY(y_valid?.value) && y_valid?.value && y_valid.value.search(rRegSign) !== 0)) {
            y_valid.value = y_valid?.value?.slice(0, -1);
        } else {
            r_valid.value = r_valid?.value?.slice(0, -1);
        }
    }
    r = document.getElementById("R").value;
    if (r === '') {
        r = 2;
    }
    clearCanvas();
    drawPoints(r);
}

function validateY(value) {
    if (value === null || value === undefined) return false
    return value.search(yReg1) === 0 || value.search(yReg2) === 0 || value.search(yReg3) === 0;
}

function validateR(value) {
    if (value === null || value === undefined) return false
    return value.search(rReg1) === 0 || value.search(rReg2) === 0;
}