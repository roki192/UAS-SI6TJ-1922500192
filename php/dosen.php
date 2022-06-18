<?php
include 'koneksi.php';
$query = "SELECT * FROM lecturer";
$result = $con->query($query);

// Fetch All
$data_obat = $result->fetch_all(MYSQLI_ASSOC);

echo json_encode($data_obat);
