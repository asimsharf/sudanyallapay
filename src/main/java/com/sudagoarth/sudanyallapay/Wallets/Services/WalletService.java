package com.sudagoarth.sudanyallapay.Wallets.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Enums.WalletStatus;
import com.sudagoarth.sudanyallapay.Users.Entities.User;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletRequest;
import com.sudagoarth.sudanyallapay.Wallets.Dtos.WalletResponse;
import com.sudagoarth.sudanyallapay.Wallets.Entities.Wallet;
import com.sudagoarth.sudanyallapay.Wallets.Interfaces.WalletInterface;
import com.sudagoarth.sudanyallapay.Wallets.Repositories.WalletRepository;
import com.sudagoarth.sudanyallapay.exceptions.DuplicateException;
import com.sudagoarth.sudanyallapay.exceptions.NotFoundException;

@Service
public class WalletService implements WalletInterface {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<WalletResponse> getWallets() {
        List<Wallet> wallet = walletRepository.findAll();
        return WalletResponse.fromWallets(wallet);
    }

    @Override
    public WalletResponse getWallet(Long id) throws NotFoundException{
        Wallet wallet = walletRepository.findById(id).orElseThrow(()-> new NotFoundException("Wallet not found"));
        return WalletResponse.fromWallet(wallet);
    }

    @Override
    public WalletResponse createWallet(WalletRequest walletRequest) throws DuplicateException {
        if (walletRepository.existsByUserId(walletRequest.getUserId())) {
            throw new DuplicateException("Wallet already exists");
        }
        Wallet wallet = new Wallet();
        wallet.setBalance(walletRequest.getBalance());
        wallet.setCurrency(walletRequest.getCurrency());
        wallet.setStatus(walletRequest.getStatus());
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());

        User user = userRepository.findById(walletRequest.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));
        
        wallet.setUser(user);
        walletRepository.save(wallet);
        return WalletResponse.fromWallet(wallet);

    }

    @Override
    public WalletResponse updateWallet(Long id, WalletRequest walletRequest) throws NotFoundException {
        Wallet existingWallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        existingWallet.setBalance(walletRequest.getBalance());
        existingWallet.setCurrency(walletRequest.getCurrency());
        existingWallet.setStatus(walletRequest.getStatus());
        existingWallet.setUpdatedAt(LocalDateTime.now());

        walletRepository.save(existingWallet);
        return WalletResponse.fromWallet(existingWallet);
    }

    @Override
    public void deleteWallet(Long id) throws NotFoundException {
        walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));

        walletRepository.deleteById(id);
    }

    @Override
    public WalletResponse statusWallet(Long id, WalletStatus status) throws NotFoundException {
        Wallet existingWallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
        existingWallet.setStatus(status);
        walletRepository.save(existingWallet);
        return WalletResponse.fromWallet(existingWallet);
    }

    @Override
    public WalletResponse depositWallet(Long id, BigDecimal amount) throws NotFoundException {

        Wallet existingWallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
        existingWallet.setBalance(existingWallet.getBalance().add(amount));
        walletRepository.save(existingWallet);
        return WalletResponse.fromWallet(existingWallet);
    }

    @Override
    public WalletResponse withdrawWallet(Long id, BigDecimal amount) throws NotFoundException {

        Wallet existingWallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
        existingWallet.setBalance(existingWallet.getBalance().subtract(amount));
        walletRepository.save(existingWallet);
        return WalletResponse.fromWallet(existingWallet);
    }

    @Override
    public WalletResponse transferWallet(Long id, Long targetId, BigDecimal amount) {

        Wallet existingWallet = walletRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));
        Wallet targetWallet = walletRepository.findById(targetId)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        existingWallet.setBalance(existingWallet.getBalance().subtract(amount));
        targetWallet.setBalance(targetWallet.getBalance().add(amount));

        walletRepository.save(existingWallet);
        walletRepository.save(targetWallet);

        return WalletResponse.fromWallet(existingWallet);
    }

    @Override
    public WalletResponse getBalance(Long id) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));
        return WalletResponse.fromWallet(wallet);
    }

    @Override
    public WalletResponse getTransactions(Long id) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));
        return WalletResponse.fromWallet(wallet);
    }

    @Override
    public WalletResponse getTransaction(Long id, Long transactionId) throws NotFoundException {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));
        return WalletResponse.fromWallet(wallet);
    }

}
