package cz.patyk.invoicesystem_be.dto.in;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserDtoInTwoPassword extends UserDtoIn {
    @NotBlank
    private String password;
    @NotBlank
    private String retypePassword;
}
