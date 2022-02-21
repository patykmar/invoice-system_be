package cz.patyk.invoicesystem_be.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressDtoIn {
    private Long id;
    private Long country;
    private String street;
    private String city;
    private String zipCode;
}
