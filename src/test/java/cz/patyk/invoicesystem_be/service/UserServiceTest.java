package cz.patyk.invoicesystem_be.service;

import cz.patyk.invoicesystem_be.dto.in.UserPasswordChangeIn;
import cz.patyk.invoicesystem_be.exceptions.ApplicationException;
import cz.patyk.invoicesystem_be.mapper.UserMapper;
import cz.patyk.invoicesystem_be.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static cz.patyk.invoicesystem_be.common.TestConstants.USER_TEST_PASSWORD;
import static cz.patyk.invoicesystem_be.common.TestEntityConstants.USER_ENTITY;
import static cz.patyk.invoicesystem_be.service.ServiceConstants.USER_INCORRECT_OLD_PASSWORD;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class UserServiceTest {
    private static BCryptPasswordEncoder bCryptPasswordEncoder;
    private static UserService userService;
    private static UserRepository userRepository;

    @BeforeAll
    static void initTest() {
        userRepository = Mockito.mock(UserRepository.class);
        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ErrorHandleService errorHandleService = new ErrorHandleService();

        userService = new UserService(userRepository, userMapper, bCryptPasswordEncoder, errorHandleService);
    }

    @Test
    void passwordEncode() {
        String passwordEncode = userService.passwordEncode(USER_TEST_PASSWORD);
        log.debug("Password encode: {}", passwordEncode);
        assertTrue(bCryptPasswordEncoder.matches(USER_TEST_PASSWORD, passwordEncode));
    }

    @Test
    void passwordChangeWithWrongOldPassword() {
        //TODO moved builder to constants class
        UserPasswordChangeIn userPasswordChangeIn = UserPasswordChangeIn.builder().oldPassword("OldPassword").newPassword("NewPassword").reTypedPassword("NewPassword").build();
        Mockito
                .when(userRepository.findById(NumberUtils.LONG_ONE))
                .thenReturn(Optional.ofNullable(USER_ENTITY));

        assertThatThrownBy(() -> userService.passwordChange(userPasswordChangeIn, NumberUtils.LONG_ONE))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(USER_INCORRECT_OLD_PASSWORD);
    }
}