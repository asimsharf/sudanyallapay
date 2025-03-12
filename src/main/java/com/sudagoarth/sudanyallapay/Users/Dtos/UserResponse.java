package com.sudagoarth.sudanyallapay.Users.Dtos;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String nationalId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Auto-formats LocalDate
    private String dateOfBirth;

    public UserResponse(User savedUser) {
        this.id = savedUser.getId();
        this.fullName = savedUser.getFullName();
        this.email = savedUser.getEmail();
        this.phoneNumber = savedUser.getPhoneNumber();
        this.nationalId = savedUser.getNationalId();

        // ✅ Automatically format LocalDate safely
        this.dateOfBirth = (savedUser.getDateOfBirth() != null)
                ? savedUser.getDateOfBirth().toString() // LocalDate's toString() outputs YYYY-MM-DD
                : null;
    }

    // ✅ Convert a list of Users
    public static List<UserResponse> fromUsers(List<User> users) {
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
