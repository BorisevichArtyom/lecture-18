package by.itacademy.javaenterprise.borisevich.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Table(name = "Diary_user", indexes = {
        @Index(name = "UNIQUE", columnList = "email", unique = true),
        @Index(name = "user_role_idx", columnList = "user_role_id")
})
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SecondaryTable(name = "User_role")
public class DiaryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance_amount", nullable = false)
    private Long balanceAmount;

    @Column(name = "user_role_id", nullable = false)
    private Long userRoleId;

    @OneToMany(mappedBy = "user")
    private Set<Training> trainingSet;


    public String getBalanceAmount() {
        return DiaryUser.convertToBYN(balanceAmount);
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = (long) (balanceAmount * 100);
    }

    private static String convertToBYN(Long balanceAmount){
        return balanceAmount / 100 + "." + balanceAmount / 10 % 10 + balanceAmount % 10;
    }

    @Override
    public String toString() {
        return "DiaryUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balanceAmount=" + balanceAmount +
                ", userRoleId=" + userRoleId +
                '}';
    }
}