package cz.patyk.invoicesystem_be.dto.out;

import cz.patyk.invoicesystem_be.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CompanyDtoOut {
    private Long id;
    private String name;
    private String description;
    private String companyId;
    private String vatNumber;
    private String accountNumber;
    private String iban;
    private AddressDto addressDto;
    private int created;
    private int modify;
}