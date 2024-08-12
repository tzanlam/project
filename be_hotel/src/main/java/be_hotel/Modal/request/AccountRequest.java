package be_hotel.Modal.request;

import be_hotel.Modal.entity.Account;
import be_hotel.Modal.entity.constants.Gender;
import be_hotel.Modal.entity.constants.Role;
import be_hotel.validation.UsernameValidation.UniqueUsername;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static be_hotel.utils.MethodConvert.convertDate;
import static be_hotel.utils.MethodConvert.convertDateTime;

@Data
public class AccountRequest {
    @NotNull(message = "Username không được để trống")
    @Size(min = 4, max = 20, message = "Username phải có độ dài từ 4 đến 20 ký tự")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Username chỉ được chứa chữ cái, số và dấu gạch dưới")
    @UniqueUsername(message = "Username đã tồn tại")
    private String username;
    private String password;
    private String fullname;
    private String birthDate;
    private String idCard;
    private String gender;
    private String phoneNumber;
    private String email;
    private String role;
    private String createdDate;
    private String modifiedDate;

    public Account asAccount(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Account account = new Account();

        account.setUsername(this.username);
        account.setPassword(encoder.encode(this.password));
        account.setFullName(this.fullname);
        account.setBirthDate(convertDate(this.birthDate));
        account.setIdCard(this.idCard);
        account.setGender(Gender.valueOf(this.gender));
        account.setPhoneNumber(this.phoneNumber);
        account.setEmail(this.email);
        account.setRole(Role.USER);

        // Nếu createdDate rỗng hoặc null, thiết lập nó thành ngày hiện tại
        if (this.createdDate == null || this.createdDate.isEmpty()) {
            account.setCreatedDate(LocalDateTime.now());
        } else {
            account.setCreatedDate(convertDateTime(this.createdDate));
        }

        // Nếu modifiedDate rỗng hoặc null, thiết lập nó thành ngày hiện tại
        if (this.modifiedDate == null || this.modifiedDate.isEmpty()) {
            account.setModifiedDate(LocalDateTime.now());
        } else {
            account.setModifiedDate(convertDateTime(this.modifiedDate));
        }
        return account;
    }
}
