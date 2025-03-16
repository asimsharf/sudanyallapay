-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 16, 2025 at 07:28 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sudanyallapay`
--

-- --------------------------------------------------------

--
-- Table structure for table `auth_security`
--

CREATE TABLE `auth_security` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_used` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `expires_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `auth_security`
--

INSERT INTO `auth_security` (`id`, `created_at`, `is_used`, `user_id`, `code`, `expires_at`) VALUES
(2, '2025-03-10 22:29:31.000000', b'1', 8, '273691', '2025-03-10 22:39:31.000000'),
(3, '2025-03-15 11:28:40.000000', b'1', 9, '844468', '2025-03-15 11:38:40.000000');

-- --------------------------------------------------------

--
-- Table structure for table `bank_accounts`
--

CREATE TABLE `bank_accounts` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `swift_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `bill_number` varchar(255) DEFAULT NULL,
  `biller_name` varchar(255) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `paid_at` date DEFAULT NULL,
  `status` enum('FAILED','PAID','PENDING') DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` bigint(20) NOT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `card_type` enum('CREDIT','DEBIT','PREPAID') DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `documents`
--

CREATE TABLE `documents` (
  `id` bigint(20) NOT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `document_url` varchar(255) DEFAULT NULL,
  `entity_type` enum('BANK_ACCOUNT','BILL','CARD','MERCHANT','TRANSACTION','USER','WALLET') DEFAULT NULL,
  `reference_id` bigint(20) DEFAULT NULL,
  `status` enum('APPROVED','PENDING','REJECTED') DEFAULT NULL,
  `uploaded_at` datetime(6) DEFAULT NULL,
  `reference_id_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `documents`
--

INSERT INTO `documents` (`id`, `document_name`, `document_url`, `entity_type`, `reference_id`, `status`, `uploaded_at`, `reference_id_id`) VALUES
(5, 'Profile Image1', 'http://localhost:4000/api/v1/documents/view/ec3c9b76-ba52-48c9-bfdc-b31f9abdcfc1.png', 'USER', 1, 'PENDING', '2025-03-13 02:11:02.000000', NULL),
(6, 'Profile Image1', 'http://localhost:4000/api/v1/documents/view/914a4d4c-e489-46c3-9e5e-d571aacaf9af.png', 'USER', 1, 'PENDING', '2025-03-13 02:11:14.000000', NULL),
(8, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/b071145f-35c4-4abc-8cf0-0a2f172f7f63.png', 'USER', 1, 'APPROVED', '2025-03-13 02:12:33.000000', NULL),
(9, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/2cc0c554-0556-42b1-a8d0-71931dbdbec9.png', 'USER', 1, 'PENDING', '2025-03-13 02:12:56.000000', NULL),
(10, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/8de7e85b-17b4-441b-93c1-101ec6ad1e66.png', 'USER', 1, 'PENDING', '2025-03-13 02:13:57.000000', NULL),
(11, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/65967983-cbb4-4ed2-a6ed-9428aff93d05.png', 'USER', 1, 'PENDING', '2025-03-13 02:13:58.000000', NULL),
(12, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/ef9d609c-735b-4e1c-85be-03ab8fe6b8b2.png', 'USER', 1, 'PENDING', '2025-03-13 02:13:59.000000', NULL),
(13, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/c5ff7737-76f3-4c2a-a768-04ab55d2e9cd.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:00.000000', NULL),
(14, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/1c6a7b42-7bac-4507-8c37-bba04edb83c9.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:00.000000', NULL),
(15, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/32c01ea3-4288-4019-9dfa-8da838967664.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:01.000000', NULL),
(16, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/c0354b75-be7e-4da7-bd8d-96c19e7ea81f.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:02.000000', NULL),
(17, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/4d1b0a60-7a1e-4f6b-a861-670b26c6d7b3.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:02.000000', NULL),
(18, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/ed445209-9296-4916-ba37-0c9bf94fe507.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:03.000000', NULL),
(19, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/0974058e-1a58-4e42-8043-c43b26db1747.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:04.000000', NULL),
(20, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/63efd017-002a-4c2d-8c4c-7c8e03a8820f.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:04.000000', NULL),
(21, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/15a8f4f8-dace-4c3f-97b2-4a9cbfb30ac9.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:05.000000', NULL),
(22, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/681c8e14-b50b-4784-b78d-2308e78515f5.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:06.000000', NULL),
(23, 'Profile Image', 'http://localhost:4000/api/v1/documents/view/8aff31e5-0c34-4bcd-933e-1b24c25b847c.png', 'USER', 1, 'PENDING', '2025-03-13 02:14:07.000000', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `document_requirements`
--

CREATE TABLE `document_requirements` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `document_name` varchar(255) DEFAULT NULL,
  `entity_type` enum('BANK_ACCOUNT','BILL','CARD','MERCHANT','TRANSACTION','USER','WALLET') DEFAULT NULL,
  `is_required` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `document_requirements`
--

INSERT INTO `document_requirements` (`id`, `created_at`, `document_name`, `entity_type`, `is_required`) VALUES
(1, '2025-03-11 00:06:37.000000', 'Profile', 'USER', b'1'),
(3, '2025-03-12 21:19:00.000000', 'Passport', 'USER', b'1'),
(4, '2025-03-12 21:19:00.000000', 'Iqama', 'USER', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `reference_number` varchar(255) DEFAULT NULL,
  `status` enum('COMPLETED','FAILED','PENDING') DEFAULT NULL,
  `transaction_type` enum('BILL_PAYMENT','DEPOSIT','TOPUP','TRANSFER','WITHDRAWAL') DEFAULT NULL,
  `receiver_wallet_id` bigint(20) DEFAULT NULL,
  `sender_wallet_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `amount`, `created_at`, `description`, `reference_number`, `status`, `transaction_type`, `receiver_wallet_id`, `sender_wallet_id`) VALUES
(1, 10000.00, '2025-03-14 20:41:57.000000', 'Deposit', 'TXN-1741974117099', 'COMPLETED', 'DEPOSIT', 9, NULL),
(2, 20000.00, '2025-03-14 20:42:58.000000', 'Deposit', 'TXN-1741974178685', 'COMPLETED', 'DEPOSIT', 9, NULL),
(3, 20000.00, '2025-03-14 20:46:36.000000', 'Deposit', 'TXN-1741974396643', 'COMPLETED', 'DEPOSIT', 10, NULL),
(4, 20000.00, '2025-03-14 20:47:42.000000', 'Withdrawal', 'TXN-1741974462703', 'COMPLETED', 'WITHDRAWAL', NULL, 10),
(5, 20000.00, '2025-03-14 20:51:09.000000', 'Withdrawal', 'TXN-1741974669870', 'COMPLETED', 'WITHDRAWAL', NULL, 9),
(6, 500.00, '2025-03-14 20:54:09.000000', 'Transfer completed', 'TXN-1741974849543', 'COMPLETED', 'TRANSFER', 10, 9),
(7, 500.00, '2025-03-14 20:58:19.000000', 'Transfer completed', 'TXN-1741975099263', 'COMPLETED', 'TRANSFER', 10, 9),
(8, 500.00, '2025-03-14 20:58:20.000000', 'Transfer completed', 'TXN-1741975100687', 'COMPLETED', 'TRANSFER', 10, 9),
(9, 500.00, '2025-03-14 20:58:27.000000', 'Transfer completed', 'TXN-1741975107019', 'COMPLETED', 'TRANSFER', 10, 9),
(10, 500.00, '2025-03-14 20:58:28.000000', 'Transfer completed', 'TXN-1741975108382', 'COMPLETED', 'TRANSFER', 10, 9),
(11, 500.00, '2025-03-14 20:58:29.000000', 'Transfer completed', 'TXN-1741975109920', 'COMPLETED', 'TRANSFER', 10, 9),
(12, 500.00, '2025-03-14 20:58:31.000000', 'Transfer completed', 'TXN-1741975111226', 'COMPLETED', 'TRANSFER', 10, 9),
(13, 500.00, '2025-03-14 20:58:32.000000', 'Transfer completed', 'TXN-1741975112723', 'COMPLETED', 'TRANSFER', 10, 9),
(14, 500.00, '2025-03-14 20:58:34.000000', 'Transfer completed', 'TXN-1741975114048', 'COMPLETED', 'TRANSFER', 10, 9),
(15, 500.00, '2025-03-14 20:58:35.000000', 'Transfer completed', 'TXN-1741975115771', 'COMPLETED', 'TRANSFER', 10, 9),
(16, 500.00, '2025-03-15 11:23:15.000000', 'Transfer completed', 'TXN-1742026995160', 'COMPLETED', 'TRANSFER', 10, 9),
(17, 500.00, '2025-03-15 11:23:17.000000', 'Transfer completed', 'TXN-1742026997225', 'COMPLETED', 'TRANSFER', 10, 9),
(18, 500.00, '2025-03-15 11:23:18.000000', 'Transfer completed', 'TXN-1742026998433', 'COMPLETED', 'TRANSFER', 10, 9),
(19, 500.00, '2025-03-15 11:23:19.000000', 'Transfer completed', 'TXN-1742026999591', 'COMPLETED', 'TRANSFER', 10, 9),
(20, 500.00, '2025-03-15 11:23:20.000000', 'Transfer completed', 'TXN-1742027000778', 'COMPLETED', 'TRANSFER', 10, 9),
(21, 500.00, '2025-03-15 11:23:21.000000', 'Transfer completed', 'TXN-1742027001945', 'COMPLETED', 'TRANSFER', 10, 9),
(22, 500.00, '2025-03-15 11:23:22.000000', 'Transfer completed', 'TXN-1742027002960', 'COMPLETED', 'TRANSFER', 10, 9),
(23, 500.00, '2025-03-15 11:23:24.000000', 'Transfer completed', 'TXN-1742027004403', 'COMPLETED', 'TRANSFER', 10, 9),
(24, 500.00, '2025-03-15 11:23:25.000000', 'Transfer completed', 'TXN-1742027005941', 'COMPLETED', 'TRANSFER', 10, 9),
(25, 500.00, '2025-03-15 11:23:27.000000', 'Transfer completed', 'TXN-1742027007711', 'COMPLETED', 'TRANSFER', 10, 9),
(26, 100000000.00, '2025-03-15 11:23:55.000000', 'Deposit', 'TXN-1742027035143', 'COMPLETED', 'DEPOSIT', 10, NULL),
(27, 200000000.00, '2025-03-15 11:24:24.000000', 'Deposit', 'TXN-1742027064497', 'COMPLETED', 'DEPOSIT', 9, NULL),
(28, 100.00, '2025-03-15 11:26:00.000000', 'Transfer completed', 'TXN-1742027160493', 'COMPLETED', 'TRANSFER', 10, 9),
(29, 100.00, '2025-03-15 11:26:01.000000', 'Transfer completed', 'TXN-1742027161964', 'COMPLETED', 'TRANSFER', 10, 9),
(30, 100.00, '2025-03-15 11:26:02.000000', 'Transfer completed', 'TXN-1742027162841', 'COMPLETED', 'TRANSFER', 10, 9),
(31, 100.00, '2025-03-15 11:26:05.000000', 'Transfer completed', 'TXN-1742027165247', 'COMPLETED', 'TRANSFER', 10, 9),
(32, 100.00, '2025-03-15 11:26:06.000000', 'Transfer completed', 'TXN-1742027166522', 'COMPLETED', 'TRANSFER', 10, 9),
(33, 100.00, '2025-03-15 11:26:07.000000', 'Transfer completed', 'TXN-1742027167740', 'COMPLETED', 'TRANSFER', 10, 9),
(34, 100.00, '2025-03-15 11:26:08.000000', 'Transfer completed', 'TXN-1742027168958', 'COMPLETED', 'TRANSFER', 10, 9),
(35, 100.00, '2025-03-15 11:26:11.000000', 'Transfer completed', 'TXN-1742027171124', 'COMPLETED', 'TRANSFER', 10, 9);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_logs`
--

CREATE TABLE `transaction_logs` (
  `id` bigint(20) NOT NULL,
  `status` enum('COMPLETED','FAILED','PENDING') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `transaction_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `transaction_logs`
--

INSERT INTO `transaction_logs` (`id`, `status`, `updated_at`, `transaction_id`) VALUES
(1, 'COMPLETED', '2025-03-14 20:41:57.000000', 1),
(2, 'COMPLETED', '2025-03-14 20:42:58.000000', 2),
(3, 'COMPLETED', '2025-03-14 20:46:36.000000', 3),
(4, 'COMPLETED', '2025-03-14 20:47:42.000000', 4),
(5, 'COMPLETED', '2025-03-14 20:51:09.000000', 5),
(6, 'PENDING', '2025-03-14 20:54:09.000000', 6),
(7, 'COMPLETED', '2025-03-14 20:54:09.000000', 6),
(8, 'PENDING', '2025-03-14 20:58:19.000000', 7),
(9, 'COMPLETED', '2025-03-14 20:58:19.000000', 7),
(10, 'PENDING', '2025-03-14 20:58:20.000000', 8),
(11, 'COMPLETED', '2025-03-14 20:58:20.000000', 8),
(12, 'PENDING', '2025-03-14 20:58:27.000000', 9),
(13, 'COMPLETED', '2025-03-14 20:58:27.000000', 9),
(14, 'PENDING', '2025-03-14 20:58:28.000000', 10),
(15, 'COMPLETED', '2025-03-14 20:58:28.000000', 10),
(16, 'PENDING', '2025-03-14 20:58:29.000000', 11),
(17, 'COMPLETED', '2025-03-14 20:58:29.000000', 11),
(18, 'PENDING', '2025-03-14 20:58:31.000000', 12),
(19, 'COMPLETED', '2025-03-14 20:58:31.000000', 12),
(20, 'PENDING', '2025-03-14 20:58:32.000000', 13),
(21, 'COMPLETED', '2025-03-14 20:58:32.000000', 13),
(22, 'PENDING', '2025-03-14 20:58:34.000000', 14),
(23, 'COMPLETED', '2025-03-14 20:58:34.000000', 14),
(24, 'PENDING', '2025-03-14 20:58:35.000000', 15),
(25, 'COMPLETED', '2025-03-14 20:58:35.000000', 15),
(26, 'PENDING', '2025-03-15 11:23:15.000000', 16),
(27, 'COMPLETED', '2025-03-15 11:23:15.000000', 16),
(28, 'PENDING', '2025-03-15 11:23:17.000000', 17),
(29, 'COMPLETED', '2025-03-15 11:23:17.000000', 17),
(30, 'PENDING', '2025-03-15 11:23:18.000000', 18),
(31, 'COMPLETED', '2025-03-15 11:23:18.000000', 18),
(32, 'PENDING', '2025-03-15 11:23:19.000000', 19),
(33, 'COMPLETED', '2025-03-15 11:23:19.000000', 19),
(34, 'PENDING', '2025-03-15 11:23:20.000000', 20),
(35, 'COMPLETED', '2025-03-15 11:23:20.000000', 20),
(36, 'PENDING', '2025-03-15 11:23:21.000000', 21),
(37, 'COMPLETED', '2025-03-15 11:23:21.000000', 21),
(38, 'PENDING', '2025-03-15 11:23:22.000000', 22),
(39, 'COMPLETED', '2025-03-15 11:23:22.000000', 22),
(40, 'PENDING', '2025-03-15 11:23:24.000000', 23),
(41, 'COMPLETED', '2025-03-15 11:23:24.000000', 23),
(42, 'PENDING', '2025-03-15 11:23:25.000000', 24),
(43, 'COMPLETED', '2025-03-15 11:23:25.000000', 24),
(44, 'PENDING', '2025-03-15 11:23:27.000000', 25),
(45, 'COMPLETED', '2025-03-15 11:23:27.000000', 25),
(46, 'COMPLETED', '2025-03-15 11:23:55.000000', 26),
(47, 'COMPLETED', '2025-03-15 11:24:24.000000', 27),
(48, 'PENDING', '2025-03-15 11:26:00.000000', 28),
(49, 'COMPLETED', '2025-03-15 11:26:00.000000', 28),
(50, 'PENDING', '2025-03-15 11:26:01.000000', 29),
(51, 'COMPLETED', '2025-03-15 11:26:01.000000', 29),
(52, 'PENDING', '2025-03-15 11:26:02.000000', 30),
(53, 'COMPLETED', '2025-03-15 11:26:02.000000', 30),
(54, 'PENDING', '2025-03-15 11:26:05.000000', 31),
(55, 'COMPLETED', '2025-03-15 11:26:05.000000', 31),
(56, 'PENDING', '2025-03-15 11:26:06.000000', 32),
(57, 'COMPLETED', '2025-03-15 11:26:06.000000', 32),
(58, 'PENDING', '2025-03-15 11:26:07.000000', 33),
(59, 'COMPLETED', '2025-03-15 11:26:07.000000', 33),
(60, 'PENDING', '2025-03-15 11:26:08.000000', 34),
(61, 'COMPLETED', '2025-03-15 11:26:08.000000', 34),
(62, 'PENDING', '2025-03-15 11:26:11.000000', 35),
(63, 'COMPLETED', '2025-03-15 11:26:11.000000', 35);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `national_id` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `created_at`, `date_of_birth`, `email`, `full_name`, `national_id`, `phone_number`, `updated_at`, `password`) VALUES
(8, '2025-03-10 00:36:29.000000', '1993-01-01', 'Ibtihal1@gmail.com', 'Ibtihal almakki1', '123256751', '0927654331', '2025-03-10 00:36:29.000000', '12342641'),
(9, '2025-03-14 13:26:23.000000', '1993-01-01', 'asimsharf@gmail.com', 'Asim Abdelgadir', '123256751', '0927354331', '2025-03-14 13:26:23.000000', '12342641');

-- --------------------------------------------------------

--
-- Table structure for table `wallets`
--

CREATE TABLE `wallets` (
  `id` bigint(20) NOT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `status` enum('ACTIVE','SUSPENDED') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `wallets`
--

INSERT INTO `wallets` (`id`, `balance`, `created_at`, `currency`, `status`, `updated_at`, `user_id`) VALUES
(9, 199999200.00, '2025-03-14 17:32:10.000000', 'SDG', 'ACTIVE', '2025-03-14 17:32:10.000000', 9),
(10, 100000800.00, '2025-03-14 17:32:59.000000', 'SDG', 'ACTIVE', '2025-03-14 17:32:59.000000', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auth_security`
--
ALTER TABLE `auth_security`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_auth_security_user_id` (`user_id`);

--
-- Indexes for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_bank_accounts_user_id` (`user_id`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_bills_user_id` (`user_id`);

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_cards_user_id` (`user_id`);

--
-- Indexes for table `documents`
--
ALTER TABLE `documents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_documents_reference_id` (`reference_id`),
  ADD KEY `index_documents_entity_type` (`entity_type`),
  ADD KEY `FK5l5wg8s9ocf05fcpui9t0sriw` (`reference_id_id`);

--
-- Indexes for table `document_requirements`
--
ALTER TABLE `document_requirements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_document_requirements_entity_type` (`entity_type`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_notifications_user_id` (`user_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_transactions_sender_wallet_id` (`sender_wallet_id`),
  ADD KEY `index_transactions_receiver_wallet_id` (`receiver_wallet_id`);

--
-- Indexes for table `transaction_logs`
--
ALTER TABLE `transaction_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_transaction_logs_transaction_id` (`transaction_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index_users_email` (`email`),
  ADD KEY `index_users_phone_number` (`phone_number`),
  ADD KEY `index_users_national_id` (`national_id`);

--
-- Indexes for table `wallets`
--
ALTER TABLE `wallets`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsswfdl9fq40xlkove1y5kc7kv` (`user_id`),
  ADD KEY `index_wallets_user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auth_security`
--
ALTER TABLE `auth_security`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `documents`
--
ALTER TABLE `documents`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `document_requirements`
--
ALTER TABLE `document_requirements`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `transaction_logs`
--
ALTER TABLE `transaction_logs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `wallets`
--
ALTER TABLE `wallets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `auth_security`
--
ALTER TABLE `auth_security`
  ADD CONSTRAINT `FKnpt36553lcbjqm9wd2amvqjha` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `bank_accounts`
--
ALTER TABLE `bank_accounts`
  ADD CONSTRAINT `FKahrj5m84hfc167gpma9vcwe0j` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `FKk8vs7ac9xknv5xp18pdiehpp1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `cards`
--
ALTER TABLE `cards`
  ADD CONSTRAINT `FKcmanafgwbibfijy2o5isfk3d5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `documents`
--
ALTER TABLE `documents`
  ADD CONSTRAINT `FK5l5wg8s9ocf05fcpui9t0sriw` FOREIGN KEY (`reference_id_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `FK5tr9f7tf144wrpoomtqyguilu` FOREIGN KEY (`receiver_wallet_id`) REFERENCES `wallets` (`id`),
  ADD CONSTRAINT `FKe0ppnh35227iut4l5u6bgghyw` FOREIGN KEY (`sender_wallet_id`) REFERENCES `wallets` (`id`);

--
-- Constraints for table `transaction_logs`
--
ALTER TABLE `transaction_logs`
  ADD CONSTRAINT `FKevg1hops6077gc61w0fcsldjo` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`);

--
-- Constraints for table `wallets`
--
ALTER TABLE `wallets`
  ADD CONSTRAINT `FKc1foyisidw7wqqrkamafuwn4e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
