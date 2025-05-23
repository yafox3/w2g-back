package ru.romanov.watchtogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.romanov.watchtogether.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {

    private String roomId;
    private List<User> users = new ArrayList<>();
    private List<Video> videos = new ArrayList<>();
    private String hostUsername;
    private String accessToken;

    public Room() {
    }

    public Room(String id, User user, String hostUsername, String accessToken) {
        this.roomId = id;
        users.add(user);
        this.hostUsername = hostUsername;
        this.accessToken = accessToken;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + roomId + '\'' +
                ", users=" + users +
                ", videoLinks=" + videos +
                '}';
    }
}
