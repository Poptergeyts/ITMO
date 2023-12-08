const canva = document.getElementById('graph');
let x, y, r, color;
let points = [];
r = 0;

if (sessionStorage.length !== 0){
    for (let i = 0; i < sessionStorage.length; i++){
        let informationDiv = document.getElementById("table_end");
        let element = sessionStorage.getItem(i);
        let elements = element.split("@");
        informationDiv.innerHTML = informationDiv.innerHTML + elements[0];
        points.push([parseFloat(elements[1]), parseFloat(elements[2]), elements[3]])
    }
}

$(document).ready(function (){
    $("#feedBack").on("reset", function (){
        $.ajax({
            success: function (){
                console.log("Command: clear");
                points = [];
                sessionStorage.clear();
                let informationDiv = document.getElementById("table_end");
                informationDiv.innerHTML = informationDiv.innerHTML.substring(0, 276);
                console.log("Table is clear");
            }
        });
    });
});

$(document).ready(function (){
    $("#feedBack").on("submit", function (){
        let formData = $(this).serialize();
        $.ajax({
            type: "get",
            url: "controller-servlet",
            contentType: false,
            processData: false,
            data: formData,
            success: function (data){
                if (data.length === 0) {
                    $('#error').html("Your request have some problems");
                } else {
                    r = document.getElementById("R").value;
                    y = document.getElementById("Y").value;
                    const checkboxes = document.getElementsByName('check');
                    checkboxes.forEach((item) => {
                        if (item.checked === true) {
                            x = item.value;
                        }
                    })
                    color = getRandomColor()
                    points.push([x, y, color]);


                    clearCanvas();
                    drawPoints(r);

                    console.log("Command: submit");
                    sessionStorage.setItem(sessionStorage.length, data + "@" + x + "@" + y + "@" + color);
                    let informationDiv = document.getElementById("table_end");
                    informationDiv.innerHTML = informationDiv.innerHTML + data;
                    console.log(data + " has added");
                    $('#error').html("");
                }
            }
        });
    });
});

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

canva.addEventListener('click', function(event) {
    if (r === 0) {
        r = 2;
    }
    const rect = canva.getBoundingClientRect();
    const mouse_x = (event.clientX - rect.left);
    const mouse_y = (event.clientY - rect.top);
    x = ((r * ((mouse_x / 300) - (1 / 2))) / 0.36);
    y = parseFloat(((-r * ((mouse_y / 300) - (1 / 2))) / 0.36).toFixed(3));
    if (x < -2) {
        x = -2;
    }
    if (x > 2) {
        x = 2;
    }
    const checkboxes = document.getElementsByName('check');
    checkboxes.forEach((item) => {
        if (item.value - x >= -0.25 && item.value - x <= 0.25) {
            item.checked = true;
            x = item.value;
        } else {
            item.checked = false;
        }
    })

    $("#Y").val(y);
    $("#R").val(r);
    clearCanvas();
    drawPoint(x, y, r);
    drawPoints(r);
});



