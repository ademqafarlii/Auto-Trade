package org.adem.autotrade.service;

import org.adem.autotrade.model.Announcement;
import org.adem.autotrade.model.User;

import java.util.Set;

public interface UserService {

    void register(User user);

    void updateCredentials(String email, String password, User user);

    Set<Announcement> getAllAnnouncementsOfUser(Integer id);

    void deleteAccount(String email, String password);
}
