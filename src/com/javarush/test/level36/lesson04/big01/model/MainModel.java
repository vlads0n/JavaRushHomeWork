package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

/**
 * Created by Влад on 12.05.2016.
 */
public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllActiveUsers(userService.getUsersBetweenLevels(1, 100)));
    }

    @Override
    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        modelData.setUsers(userService.getAllDeletedUsers());
    }

    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long userId) {
        userService.deleteUser(userId);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllActiveUsers(userService.getUsersBetweenLevels(1, 100)));

    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        modelData.setDisplayDeletedUserList(false);
        modelData.setUsers(getAllActiveUsers(userService.getUsersBetweenLevels(1, 100)));
    }

    private List<User> getAllActiveUsers(List<User> users) {
        return userService.filterOnlyActiveUsers(users);
    }
}
