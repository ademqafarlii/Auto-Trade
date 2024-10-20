package org.adem.autotrade.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.adem.autotrade.dto.request.UserRequestDto;
import org.adem.autotrade.dto.response.AnnouncementResponseDto;
import org.adem.autotrade.dto.response.UserResponseDto;
import org.adem.autotrade.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid UserRequestDto user) {
        userService.register(user);
    }

    @PutMapping("/update-credentials")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCredentials(@RequestParam String email, @RequestParam String password,@RequestBody @Valid UserRequestDto user) {
        userService.updateCredentials(email, password, user);
    }

    @PatchMapping("/partial-update-credentials")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCredentials(@RequestParam String email, @RequestParam String password,@RequestBody @Valid UserRequestDto user) {
        userService.updateCredentials(email, password, user);
    }

    @GetMapping("/get-all-announcements-of-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<AnnouncementResponseDto> getAllAnnouncementsOfUser(@PathVariable Integer id) {
        return userService.getAllAnnouncementsOfUser(id);
    }

    @DeleteMapping("/delete-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@RequestParam String email,@RequestParam String password) {
        userService.deleteAccount(email, password);
    }

    @GetMapping("/find-by-spec")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponseDto> findBySpecification(@RequestParam(required = false) String name,
                                                     @RequestParam(required = false) String surname,
                                                     @RequestParam(required = false) String email,
                                                     @RequestParam(required = false) String phoneNumber,
                                                     @PageableDefault(value = 12) Pageable pageable) {
        return userService.findBySpecification(name, surname, email, phoneNumber, pageable);
    }
}
