package com.sudagoarth.sudanyallapay.Users.Dtos;

import com.sudagoarth.sudanyallapay.Documents.Dtos.DocumentResponse;
import com.sudagoarth.sudanyallapay.Documents.Entities.Document;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import jakarta.validation.constraints.NotNull;

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

    private List<DocumentResponse> documents;

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

    public UserResponse(User savedUser, List<Document> userDocuments) {
        this.id = savedUser.getId();
        this.fullName = savedUser.getFullName();
        this.email = savedUser.getEmail();
        this.phoneNumber = savedUser.getPhoneNumber();
        this.nationalId = savedUser.getNationalId();
        this.dateOfBirth = (savedUser.getDateOfBirth() != null)
                ? savedUser.getDateOfBirth().toString()
                : null;

        // Convert documents to DTOs
        this.documents = DocumentResponse.fromDocuments(userDocuments);
    }

    // ✅ Convert a list of Users
    public static List<UserResponse> fromUsers(List<User> users) {
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public static @NotNull(message = "User ID is required") UserResponse fromUser(User user) {
        return new UserResponse(user);
    }
}
