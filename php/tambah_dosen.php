<?php
include 'koneksi.php';
$nidn    = $_POST['nidn'];
$nama_dosen    = $_POST['nama_dosen'];
$jabatan = $_POST['jabatan'];
$gol_pang  = $_POST['gol_pang'];
$keahlian        = $_POST['keahlian'];
$program_studi = $_POST['program_studi'];

//pembuatan Syntax SQL
if ($nidn != '' && $nama_dosen != '') {
$sql = mysqli_query($con, "INSERT INTO lecturer(nidn,nama_dosen,jabatan,gol_pang,keahlian,program_studi) 
VALUES('$nidn','$nama_dosen','$jabatan','$gol_pang','$keahlian','$program_studi')");
}
if ($sql) {
    // code...
    echo "berhasil";
} else {
    echo "gagal";
}
mysqli_close($con);
