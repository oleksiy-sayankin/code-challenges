package net.javacogito.suggestgroups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

  private static final String EMPTY = "";

  public static void main(String[] args) throws IOException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    Set<String> rawUsers = new HashSet<>();
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        rawUsers.add(inputLine);
      }
    }
    SortedSet<User> users = parseNetwork(rawUsers);
    for(User user : users){
      Set<Group> suggestedGroups = user.suggestGroups();
      if(!suggestedGroups.isEmpty()){
        System.out.println(user + ":" + toString(suggestedGroups));
      }
    }
  }

  private static SortedSet<User> parseNetwork(Set<String> rawUsers){
    SortedSet<User> result = new TreeSet<>();
    for(String rawUser : rawUsers){
      User user = new User(parseUserName(rawUser));
      result.add(user);
    }

    for(String rawUser : rawUsers) {
      String[] rawData = rawUser.split(":");
      User user = getUserByName(result, parseUserName(rawUser));

      if(hasRawFriends(rawUser)){
        String[] rawFriends = rawData[1].split(",");
        for(String rawFriend : rawFriends){
          user.addFriend(getUserByName(result, rawFriend));
        }
      }

      if(hasRawGroups(rawUser)) {
        String[] rawGroupNames = rawData[2].split(",");
        for (String groupName : rawGroupNames) {
          user.addGroup(new Group(groupName));
        }
      }
    }
    return result;
  }

  private static boolean hasRawFriends(String rawUser){
    int firstColumnIndex = rawUser.indexOf(":");
    int secondColumnIndex = rawUser.indexOf(":", firstColumnIndex + 1);
    return secondColumnIndex - firstColumnIndex > 1;
  }

  private static boolean hasRawGroups(String rawUser){
    int firstColumnIndex = rawUser.indexOf(":");
    int secondColumnIndex = rawUser.indexOf(":", firstColumnIndex + 1);
    return rawUser.length() - secondColumnIndex - 1 > 0;
  }


  private static String parseUserName(String rawUser){
    return rawUser.split(":")[0];
  }

  private static User getUserByName(Set<User> users, String name){
    for(User user : users){
      if(user.hasName(name)){
        return user;
      }
    }
    return new User(name); // never happens
  }

  private static String toString(Set<Group> groups){
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for(Group group : groups){
      if(first){
        sb.append(group);
        first = false;
        continue;
      }
      sb.append(",");
      sb.append(group);
    }
    return sb.toString();
  }


  static class User implements Comparable<User>{
    private final String name;
    User(String name) {
      this.name = name;
    }
    private final Set<User> friends = new HashSet<>();
    private final Set<Group> groups = new HashSet<>();

    boolean hasName(String name) {
      return this.name.equals(name);
    }

    Set<Group> suggestGroups(){
      Set<Group> result = new TreeSet<>();
      List<Group> friendsGroup = findFriendsGroup();
      Map<Group, Integer> rates = new HashMap<>();
      for(Group group : friendsGroup){
        if(rates.containsKey(group)){
          rates.put(group, rates.get(group) + 1);
        } else {
          rates.put(group, 1);
        }
      }
      int numFriends = friends.size();
      for(Map.Entry<Group, Integer> rate : rates.entrySet()){
        Group group =  rate.getKey();
        if((double) rate.getValue() / (double) numFriends >= 0.5 && !groups.contains(group)){
          result.add(group);
        }
      }
      return result;
    }

    private List<Group> findFriendsGroup(){
      List<Group> result = new ArrayList<>();
      for(User friend : friends){
        result.addAll(friend.getGroups());
      }
      return result;
    }

    void addGroup(Group group){
      groups.add(group);
    }

    void addFriend(User user){
      friends.add(user);
    }

    Set<Group> getGroups() {
      return groups;
    }

    @Override
    public String toString() {
      return  name;
    }

    @Override
    public int compareTo(User o) {
      return this.name.compareTo(o.name);
    }
  }

  static class Group implements Comparable<Group>{
    private final String name;
    Group(String name) {
      this.name = name;
    }

    @Override
    public String toString(){
      return name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Group group = (Group) o;
      return name.equals(group.name);
    }

    @Override
    public int hashCode() {
      return name.hashCode();
    }

    @Override
    public int compareTo(Group o) {
      return this.name.compareTo(o.name);
    }
  }
}