package ma.youcode.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import ma.youcode.enums.Gender;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ma.youcode.enums.Access;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "Person")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    private String hashPassword;

    private String image;

    @Embedded
    private PersonalInfo personalInfo;

    @Embedded
    private Address address = new Address();

    @Convert(converter = Access.AccessConverter.class)
    private Access access;

    @Override
    public int hashCode() {
        Objects.requireNonNull(personalInfo);
        return personalInfo.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof User other)) return false;
        return Objects.equals(this.personalInfo, other.personalInfo);
    }


    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class PersonalInfo {
        @NotNull(message = "Phone number must be present")
        @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
        @Column(unique = true)
        private String phoneNumber;

        @Email(message = "Email was not provided")
        @Size(max = 80, message = "Email is too long")
        @Column(unique = true)
        private String email;

        @NotNull(message = "FirstName must be present")
        @Size(min = 1, message = "Firstname cannot be empty")
        @Size(max = 30, message = "Firstname is too long")
        private String firstname;

        @Size(max = 30, message = "Firstname is too long")
        private String lastname;

        @Enumerated(EnumType.STRING)
        @JdbcTypeCode(SqlTypes.NAMED_ENUM)
        private Gender gender;

        @Override
        public int hashCode() {
            return Objects.hash(email);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof PersonalInfo other)) return false;
            return Objects.equals(phoneNumber, other.phoneNumber) &&
                    Objects.equals(email, other.email);
        }
    }
}
