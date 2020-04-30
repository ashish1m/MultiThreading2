import model.*;

import java.util.*;
import java.util.concurrent.*;

public class RemoteService {
    private static int cores = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService = Executors.newFixedThreadPool(cores + 1);

    public void stop() {
        executorService.shutdown();
    }

    public void getUserRecentActivities(ResultCallback callback) {
        executorService.execute(() -> {
            List<Like> likes = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();
            List<Post> posts = new ArrayList<>();
            List<Friend> friends = new ArrayList<>();

            Future<List<Like>> futureLikes = executorService.submit(getLikes("https://dummy.com/likes"));
            Future<List<Comment>> futureComments = executorService.submit(getComments("https://dummy.com/comments"));
            Future<List<Post>> futurePosts = executorService.submit(getPosts("https://dummy.com/posts"));
            Future<List<Friend>> futureFriends = executorService.submit(getFriends("https://dummy.com/friends"));

            try {
                likes = futureLikes.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                comments = futureComments.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                posts = futurePosts.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            try {
                friends = futureFriends.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            List<Activity> activities = new ArrayList<>();
            activities.addAll(likes);
            activities.addAll(comments);
            activities.addAll(posts);
            activities.addAll(friends);

            Collections.sort(activities,
                    (activity1, activity2) -> activity1.getCreatedAt().compareTo(activity2.getCreatedAt()));

            callback.onResult(activities);
        });
    }

    public Callable<List<Like>> getLikes(String url) {
        return () -> {
            System.out.println("getLikes");
            Thread.sleep(3000);
            return Arrays.asList(new Like(new Date(1583150760068L)), new Like(new Date(1588150791068L)));
        };
    }

    public Callable<List<Comment>> getComments(String url) {
        return () -> {
            System.out.println("getComments");
            Thread.sleep(800);
            return Arrays.asList(new Comment(new Date(1583189760068L)), new Comment(new Date(1588122791068L)));
        };
    }

    public Callable<List<Post>> getPosts(String url) {
        return () -> {
            System.out.println("getPosts");
            Thread.sleep(1500);
            return Arrays.asList(new Post(new Date(1523150760068L)), new Post(new Date(1588157791068L)));
        };
    }

    public Callable<List<Friend>> getFriends(String url) {
        return () -> {
            System.out.println("getFriends");
            Thread.sleep(5000);
            return Arrays.asList(new Friend(new Date(1583550760068L)), new Friend(new Date(1586150791068L)));
        };
    }
}
