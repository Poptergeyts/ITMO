<?php
    $start = microtime(true);
    date_default_timezone_set("Europe/Moscow");
    if (isset($_GET["check"]) and strlen($_GET["inputY"]) != 0
    and strlen($_GET["inputR"]) != 0){

        $_radius = $_GET["inputR"];
        $_y = $_GET["inputY"];
        $_x = $_GET["check"];

        $_point_in_rect = ($_x <= $_radius && $_x >= 0) && ($_y <= $_radius / 2.0 && $_y >=0);
        $_tf1 = -($_radius) * (-$_y);
        $_tf2 = (-$_x) * (-$_radius);
        $_tf3 = (-$_x) * ($_radius) - (-$_radius) * (-$_radius - $_y);
        $_point_in_triangle = ($_tf3 >= 0 && $_tf2 >= 0 && $_tf1 >= 0) || ($_tf3 <= 0 && $_tf2 <= 0 && $_tf1 <= 0);
        $_point_in_circle = ($_x >= 0 && $_y <= 0) && (($_x * $_x) + ($_y * $_y)) <= $_radius * $_radius / 4.0;

        $end = microtime(true);
        $diff = $end - $start;
        if ($_point_in_circle || $_point_in_triangle || $_point_in_rect) {
            echo '<tr>
                      <th>Hit</th>
                      <th>' . $_x . '</th>
                      <th>' . $_y . '</th>
                      <th>' . $_radius . '</th>
                      <th>' . date("d.m.Y H:i:s") . '</th>
                      <th>' . round($diff, 6) * 1000 . " msec" . '</th>
                  </tr>';
        } else {
            echo '<tr>
                      <th>Miss</th>
                      <th>' . $_x . '</th>
                      <th>' . $_y . '</th>
                      <th>' . $_radius . '</th>
                      <th>' . date("d.m.Y H:i:s") . '</th>
                      <th>' . round($diff, 6) * 1000 . " msec" . '</th>
                  </tr>';
        }
    }
    else {
        $end = microtime(true);
        $diff = $end - $start;
        echo '<tr>
                          <th>Input error</th>
                          <th>None</th>
                          <th>None</th>
                          <th>None</th>
                          <th>' . date("d.m.Y H:i:s") . '</th>
                          <th>' . round($diff, 6) * 1000 . " msec" . '</th>
                      </tr>';
    }