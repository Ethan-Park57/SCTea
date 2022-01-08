package com.app.sctea.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.ServerValue;
import com.app.sctea.model.Post;
import com.app.sctea.model.User;
import com.github.javafaker.Faker;

@RestController
public class PostController {
	@GetMapping("/getAllPosts/sort=latest")
	public Post getLatestPosts() throws IOException, InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("posts").orderBy("timestamp").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		System.out.println(documents.size());
		for (int i = documents.size()-1; i >= 0; i--) {
			QueryDocumentSnapshot document = documents.get(i);
			System.out.println(document.toObject(Post.class));
		}
		return null;
	}
	@GetMapping("/getAllPosts/sort=upvote")
	public Post getMostLikedPosts(@RequestParam(value = "email", defaultValue = "usc.edu") String email) throws IOException, InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("posts").orderBy("upvoteNum").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		System.out.println(documents.size());
		for (int i = documents.size()-1; i >= 0; i--) {
			QueryDocumentSnapshot document = documents.get(i);
			System.out.println(document.toObject(Post.class));
		}
		return null;
	}
	@GetMapping("/createPost")
	public String createPost(@RequestParam(value = "content") String content, @RequestParam(value = "userID") String userID) throws ExecutionException {
		Faker faker = new Faker();
		String displayedName = faker.name().firstName();
		Post post = new Post(userID, content, displayedName, Timestamp.now(), 0, 0);
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection("posts").add(post);
		return collectionApiFuture.toString();
	}
}
