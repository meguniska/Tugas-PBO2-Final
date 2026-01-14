-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jan 2026 pada 16.59
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_hukum_perkara`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori_perkara`
--

CREATE TABLE `kategori_perkara` (
  `id` varchar(20) NOT NULL,
  `nama_perkara` varchar(45) DEFAULT NULL,
  `jenis_perkara` varchar(45) DEFAULT NULL,
  `deskripsi` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kategori_perkara`
--

INSERT INTO `kategori_perkara` (`id`, `nama_perkara`, `jenis_perkara`, `deskripsi`) VALUES
('KAT01', 'Pencurian', 'Pidana', 'Perkara mengenai pengambilan properti milik orang lain secara ilegal.'),
('KAT02', 'Wanprestasi', 'Perdata', 'Perkara mengenai kegagalan memenuhi janji atau kewajiban dalam kontrak.'),
('KAT03', 'Sengketa Lahan', 'Perdata', 'Perkara mengenai perebutan hak kepemilikan atas tanah atau bangunan.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `klien`
--

CREATE TABLE `klien` (
  `id` varchar(20) NOT NULL,
  `nama_klien` varchar(45) DEFAULT NULL,
  `tempat_lahir` varchar(45) DEFAULT NULL,
  `tanggal_lahir` varchar(50) DEFAULT NULL,
  `ktp` varchar(45) DEFAULT NULL,
  `jenis_kelamin` varchar(20) DEFAULT NULL,
  `pekerjaan` varchar(45) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(12) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `klien`
--

INSERT INTO `klien` (`id`, `nama_klien`, `tempat_lahir`, `tanggal_lahir`, `ktp`, `jenis_kelamin`, `pekerjaan`, `alamat`, `telepon`, `email`) VALUES
('KLI01', 'Ahmad Pratama', 'Jakarta', '1985-05-20', '3171012345670001', 'Laki-laki', 'Wiraswasta', 'Jl. Merdeka No. 10, Jakarta Pusat', '081234567890', 'ahmad@email.com'),
('KLI02', 'Siti Aminah', 'Bandung', '1992-11-12', '3273012345670002', 'Perempuan', 'Pegawai Swasta', 'Jl. Dago No. 45, Bandung', '081345678901', 'siti@email.com'),
('KLI03', 'Budi Santoso', 'Surabaya', '1978-02-28', '3578012345670003', 'Laki-laki', 'Guru', 'Jl. Pahlawan No. 5, Surabaya', '081456789012', 'budi@email.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengacara`
--

CREATE TABLE `pengacara` (
  `id` varchar(20) NOT NULL,
  `nama_pengacara` varchar(45) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telepon` varchar(12) DEFAULT NULL,
  `instansi` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengacara`
--

INSERT INTO `pengacara` (`id`, `nama_pengacara`, `alamat`, `telepon`, `instansi`) VALUES
('PENG01', 'Dewi Fortuna, S.H.', 'Jl. Sudirman No. 100, Jakarta', '081122334455', 'LBH Jakarta'),
('PENG02', 'Hadi Wijaya, S.H., M.H.', 'Jl. Gatot Subroto No. 20, Jakarta', '081222334455', 'Wijaya & Partners'),
('PENG03', 'Rina Sari, S.H.', 'Jl. Diponegoro No. 15, Bandung', '081322334455', 'Kantor Hukum Rina');

-- --------------------------------------------------------

--
-- Struktur dari tabel `perkara`
--

CREATE TABLE `perkara` (
  `id` varchar(20) NOT NULL,
  `jenis_perkara` varchar(50) DEFAULT NULL,
  `no_perkara` varchar(100) DEFAULT NULL,
  `kategori_perkara_id_kategori` varchar(20) DEFAULT NULL,
  `tgl_mulai` varchar(50) DEFAULT NULL,
  `tgl_selesai` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `klien_id_klien` varchar(20) DEFAULT NULL,
  `nama_terdakwa` varchar(45) DEFAULT NULL,
  `pengacara_id_pengacara` varchar(20) DEFAULT NULL,
  `keterangan` varchar(45) DEFAULT NULL,
  `dokumen` varchar(45) DEFAULT NULL,
  `surat_kuasa` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `perkara`
--

INSERT INTO `perkara` (`id`, `jenis_perkara`, `no_perkara`, `kategori_perkara_id_kategori`, `tgl_mulai`, `tgl_selesai`, `status`, `klien_id_klien`, `nama_terdakwa`, `pengacara_id_pengacara`, `keterangan`, `dokumen`, `surat_kuasa`) VALUES
('PER01', 'Pidana', '123/Pid.B/2025/PN JKT', 'KAT01', '2025-01-10', NULL, 'Proses Persidangan', 'KLI01', 'Terdakwa X', 'PENG01', 'Kasus pencurian di gudang logistik', 'berkas_pencurian.pdf', 'sk_per01.pdf'),
('PER02', 'Perdata', '456/Pdt.G/2025/PN BDG', 'KAT02', '2025-02-15', '2025-06-20', 'Selesai / Putusan', 'KLI02', 'Terdakwa Y', 'PENG03', 'Gugatan wanprestasi jual beli rumah', 'berkas_wanprestasi.pdf', 'sk_per02.pdf'),
('PER03', 'Perdata', '789/Pdt.G/2025/PN SBY', 'KAT03', '2025-03-05', NULL, 'Mediasi', 'KLI03', 'Terdakwa Z', 'PENG02', 'Sengketa batas wilayah tanah warisan', 'berkas_sengketa.pdf', 'sk_per03.pdf');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `kategori_perkara`
--
ALTER TABLE `kategori_perkara`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `klien`
--
ALTER TABLE `klien`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `pengacara`
--
ALTER TABLE `pengacara`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `perkara`
--
ALTER TABLE `perkara`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
