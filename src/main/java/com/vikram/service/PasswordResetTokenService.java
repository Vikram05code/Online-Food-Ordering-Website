package com.vikram.service;

import com.vikram.model.PasswordResetToken;

public interface PasswordResetTokenService {

    public PasswordResetToken findByToken(String token);

    public void delete(PasswordResetToken resetToken);

}