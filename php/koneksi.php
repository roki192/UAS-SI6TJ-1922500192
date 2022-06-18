<?php
$con = new mysqli("localhost", "root", "", "campuss");

if ($con->connect_errno) {
    echo "Failed to connect to MYSQL: " . $con->connect_error;
    exit();
}
