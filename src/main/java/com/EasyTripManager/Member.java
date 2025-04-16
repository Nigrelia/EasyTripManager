package com.EasyTripManager;

import java.time.LocalDateTime;
import java.util.Random;

public class Member {
    private String accountId;
    private String fullName;
    private String email;
    private String password;
    private String companyName;
    private String accountType;
    private LocalDateTime signupDate;

    public Member(String fullName, String email, String password, String companyName, String accountType) {
        this.accountId = generateAccountId();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.accountType = accountType;
        this.signupDate = LocalDateTime.now();
    }

    private String generateAccountId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        // Generate first digit (1-9 to ensure 8 digits)
        sb.append(random.nextInt(9) + 1);
        
        // Generate remaining 7 digits
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        
        return sb.toString();
    }

    // Getters
    public String getAccountId() { return accountId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCompanyName() { return companyName; }
    public String getAccountType() { return accountType; }
    public LocalDateTime getSignupDate() { return signupDate; }
}