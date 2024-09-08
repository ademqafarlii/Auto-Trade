package org.adem.autotrade.controller;

import lombok.RequiredArgsConstructor;
import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.model.User;
import org.adem.autotrade.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @PutMapping("/update-credentials")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCredentials(@RequestParam String email, @RequestParam String password,@RequestBody User user) {
        userService.updateCredentials(email, password, user);
    }

    @PatchMapping("/partial-update-credentials")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void partialUpdateCredentials(@RequestParam String email, @RequestParam String password,@RequestBody User user) {
        userService.updateCredentials(email, password, user);
    }

    @GetMapping("/get-all-announcements-of-user-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Announcement> getAllAnnouncementsOfUser(@PathVariable Integer id) {
        return userService.getAllAnnouncementsOfUser(id);
    }

    @DeleteMapping("/delete-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@RequestParam String email,@RequestParam String password) {
        userService.deleteAccount(email, password);
    }
}
