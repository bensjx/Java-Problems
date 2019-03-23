/**
 * Name         : 
 * Matric No.   :
 * PLab Acct.   :
 */

import java.util.*;

public class Website {

    // define your own attributes, constructor, and methods here

    /* Pre-condition: input username as a string, a main list of users
     * and a main list of photos.
     * Post-condition: If no user, create it and add to main user list.
     * Add the new photo into the main photo list. Print respective lines
     */
    private void Upload(String username, int id,HashMap<String,User>
            userlist, HashMap<Integer,Photo> photolist){
        // if there is no such user, create it
        if (!userlist.containsKey(username)){
            User newuser = new User(username,new HashMap<Integer,Photo>());
            //put the user in the main user list
            userlist.put(username,newuser);
            System.out.println("New user " + username + " created.");
        } 
        // since user, if not yet created is created, we can retrieve
        // them fromthe main user list
        User uploader = userlist.get(username);
        Photo newphoto = new Photo(id, uploader);
        photolist.put(id, newphoto);
        System.out.println("Photo " + id + " uploaded successfully "
                + "by " + username + ".");

    }

    /* Pre-condition: An integer id, String username, main list of users
     * and photo.
     * Post-condition: If no user, create it and add to main user list.
     * Update users who are tagged in photos
     */
    private void TagUser(int id, String username, HashMap<String,User>
            userlist, HashMap<Integer,Photo> photolist){
        //create user if no existing user
        if (!userlist.containsKey(username)){
            User newuser = new User(username,new HashMap<Integer,Photo>());
            userlist.put(username,newuser);
            System.out.println("New user " + username + " created.");
        }
        //terminate if no such photo
        if (!photolist.containsKey(id)){
            System.out.println("No photo " + id + " exists.");
            return;
        }
        //check and make sure user is not already tagged in the photo
        if (userlist.get(username).getTaggedPhotos().containsKey(id)){
            System.out.println("User " + username + " is already tagged"
                    + " in photo " + id + ".");
            return;
        }
        User tagger = userlist.get(username);
        Photo phototag = photolist.get(id);
        tagger.Tag(phototag);//making use of method tag in User class
        System.out.println("User " + username + " tagged successfully "
                + "in photo " + id + ".");
    }

    /* Pre-condition: String of username, main user list and photo list
     * Post-condition: Print out how many photos is the user tagged in
     */
    private void TaggedPhotos(String username, HashMap<String,User> userlist,
            HashMap<Integer,Photo> photolist){
        if (!userlist.containsKey(username)){
            System.out.println("No user " + username + " exists.");
            return;
        } else {
            User user = userlist.get(username);
            //making use of method getTaggedCount in User class
            int taggedcount = user.getTaggedCount();
            System.out.println("User " + username + " is tagged " + 
                    "in " + taggedcount + " photo(s).");
        }
    }

    /* Pre-condition: main user list and photo list
     * Post-condition: Print out how many users tagged themselves
     * (photos of the user have username equal to the photo's owner)
     */
    private void SelfTag(HashMap<String,User> userlist, HashMap<Integer,Photo>
            photolist){
        int count = 0;
        for (User users: userlist.values()){
            //making use of method isSelfTagged in User class
            if (users.isSelfTagged()){
                count++;
            }
        }
        System.out.println("There are " + count + " user(s) that " + 
                "have tagged themselves.");
    }

    /* Pre-condition: main user list and photo list
     * Post-condition: Print out the total number of users and photos
     */
    private void ServerStats(HashMap<String,User> userlist,
            HashMap<Integer,Photo> photolist){
        int users = userlist.size();
        int photos = photolist.size();
        System.out.println("Total: " + users + " user(s), " + photos + 
                " photo(s).");
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N is the number of operations
        //create a main hashmap to store all users
        HashMap<String,User> userList = new HashMap<String,User>();
        //create a main hashmap to store all photos
        HashMap<Integer,Photo> photoList = new HashMap<Integer,Photo>();
        //loop through all the operations
        for (int i = 0; i < N; i++){
            String qtype = sc.next(); //get the query type
            if (qtype.equals("UPLOAD")){
                Upload(sc.next(), sc.nextInt(), userList, photoList);
            } else if (qtype.equals("TAG")){
                TagUser(sc.nextInt(), sc.next(), userList, photoList);
            } else if (qtype.equals("TAGGED_PHOTOS")){
                TaggedPhotos(sc.next(), userList, photoList);
            } else if (qtype.equals("SELF_TAG")){
                SelfTag(userList, photoList);
            } else if (qtype.equals("SERVER_STATS")){
                ServerStats(userList, photoList);
            }
        }
    }

    public static void main(String[] args) {
        Website website = new Website();
        website.run();
    }
}

class User {
    // define your own attributes, constructor, and methods here
    private String _username;
    private HashMap<Integer,Photo> _taggedPhotos;

    public User(String username, HashMap<Integer,Photo> taggedPhotos){
        _username = username;
        _taggedPhotos = taggedPhotos;
    }

    public String getName(){
        return _username;
    }

    public int getTaggedCount(){
        return _taggedPhotos.size();
    }

    /* isSelfTagged serves to check if the user tagged themself in the
     * photo. This is done by looping through their _taggedPhotos and
     * return true if 1 photo owner is this owner 
     */
    public boolean isSelfTagged(){
        for (Photo photo: _taggedPhotos.values()){
            if (photo.getOwner().getName().equals(_username)){
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer,Photo> getTaggedPhotos(){
        return _taggedPhotos;
    }

    /* To tag this user in a photo, we add the photo to their list of
     * _taggedphotos
     */
    public void Tag(Photo photo){
        _taggedPhotos.put(photo.getId(),photo);
    }

}

class Photo {
    // define your own attributes, constructor, and methods here
    private int _id;
    private User _owner;

    public Photo(int id,User owner){
        _id = id;
        _owner = owner;
    }

    public int getId(){
        return _id;
    }

    public User getOwner(){
        return _owner;
    }
}
