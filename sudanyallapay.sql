-- Create the database
CREATE DATABASE IF NOT EXISTS sudan_yalla_pay;
USE sudan_yalla_pay;

-- Create Users Table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    national_id VARCHAR(50) UNIQUE NULL,
    date_of_birth DATE NULL,
    profile_image_url VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create Wallets Table
CREATE TABLE wallets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    currency VARCHAR(10) DEFAULT 'SDG',
    status ENUM('active', 'suspended') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Transactions Table
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_wallet_id BIGINT NULL,
    receiver_wallet_id BIGINT NULL,
    transaction_type ENUM('deposit', 'withdrawal', 'transfer', 'bill_payment', 'topup') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    status ENUM('pending', 'completed', 'failed') DEFAULT 'pending',
    reference_number VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_wallet_id) REFERENCES wallets(id) ON DELETE SET NULL,
    FOREIGN KEY (receiver_wallet_id) REFERENCES wallets(id) ON DELETE SET NULL
);

-- Create Bank Accounts Table
CREATE TABLE bank_accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    iban VARCHAR(50) UNIQUE NOT NULL,
    swift_code VARCHAR(20) NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Cards Table
CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    card_number VARCHAR(20) UNIQUE NOT NULL,
    card_type ENUM('debit', 'credit', 'prepaid') NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(5) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Bills Table
CREATE TABLE bills (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    biller_name VARCHAR(100) NOT NULL,
    bill_number VARCHAR(50) UNIQUE NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    status ENUM('pending', 'paid', 'failed') DEFAULT 'pending',
    due_date DATE NOT NULL,
    paid_at TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Transaction Logs Table
CREATE TABLE transaction_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_id BIGINT NOT NULL,
    status ENUM('pending', 'completed', 'failed') NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id) ON DELETE CASCADE
);

-- Create Notifications Table
CREATE TABLE notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Security & Authentication Table
CREATE TABLE auth_security (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    otp_code VARCHAR(10) NOT NULL,
    otp_expires_at TIMESTAMP NOT NULL,
    is_used BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Merchants Table
CREATE TABLE merchants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    business_name VARCHAR(255) NOT NULL,
    owner_id BIGINT NOT NULL,
    wallet_id BIGINT NOT NULL,
    registration_number VARCHAR(50) UNIQUE NOT NULL,
    contact_email VARCHAR(255) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (wallet_id) REFERENCES wallets(id) ON DELETE CASCADE
);

-- Create Document Requirements Table
CREATE TABLE document_requirements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    entity_type ENUM('user', 'transaction', 'wallet', 'merchant', 'bank_account', 'card', 'bill') NOT NULL,
    document_name VARCHAR(255) NOT NULL,
    is_required BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Documents Table
CREATE TABLE documents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reference_id BIGINT NOT NULL,
    entity_type ENUM('user', 'transaction', 'wallet', 'merchant', 'bank_account', 'card', 'bill') NOT NULL,
    document_name VARCHAR(255) NOT NULL,
    document_url VARCHAR(255) NOT NULL,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
