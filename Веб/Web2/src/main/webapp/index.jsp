<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Лабораторная работа №2</title>
    <link rel="stylesheet" href="CSS/header.css">
    <link rel="stylesheet" href="CSS/main.css">
    </head>

<body>
    <header>
        <div class="author">Шубин Илья P3233</div>
        <div class="title">Лабораторная работа №2</div>
        <div class="variant">Вариант 1898</div>
    </header>

    <main>
        <div class="graph">
            <canvas id="graph">This browser does not support graphics</canvas>
        </div>

        <form action="index.php" method="GET" id="feedBack" onsubmit="return false">
        <div class="input_box">
            <div>
                <label>Enter X:
                    <label>-2<input value="-2" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>-1.5<input value="-1.5" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>-1<input value="-1" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>-0.5<input value="-0.5" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>0<input value="0" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>0.5<input value="0.5" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>1<input value="1" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>1.5<input value="1.5" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)"></label>
                    <label>2<input value="2" class="input_element" type="checkbox" name="check" onclick="onlyOneX(this)" id="X"></label>
                </label>
            </div>

            <div>
                <label>Enter Y: <input class="input_element" type="text" name="inputY" placeholder="от -3 до 5" autocomplete="off" id="Y"></label>
            </div>

            <div>
                <label>Enter R: <input class="input_element" type="text" name="inputR" placeholder="от 1 до 4" autocomplete="off" id="R"></label>
            </div>

            <div>
                <input type="submit" class = "submit" value="Submit" name="submit" id="submit">
                <input type="reset" class = "reset" value="Clear" name="reset" onclick="draw_graph()">
            </div>
        </div>
        </form>

        <div class = "table">
            <table id="table_end">
                <tr>
                    <th>Result</th>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Time</th>
                    <th>Execute Time</th>
                </tr>
            </table>
        </div>
    </main>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="JS/graph.js"></script>
<script src="JS/main.js"></script>
<script src="JS/validator.js"></script>
