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
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.app.sctea.model.User;

@RestController
public class UserController {
	@GetMapping("/verifyUser")
	public User verifyUser(@RequestParam(value = "email", defaultValue = "usc.edu") String email, @RequestParam(value="pwd") String password) throws IOException, InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		System.out.println(documents.size());
		for (QueryDocumentSnapshot document : documents) {
			  System.out.println(document.getId() + " => " + document.toObject(User.class) + " \ncurrent email: " + document.get("email"));
			  if (document.get("email").equals(email) && document.get("password").equals(password)) {
				  System.out.print("yeaahhh!!" + document.get("name"));
				  return document.toObject(User.class);
			  }
		}
		System.out.println("User not found");
		return null;
	}
	@GetMapping("/getUser")
	public User getUser(@RequestParam(value = "email", defaultValue = "usc.edu") String email) throws IOException, InterruptedException, ExecutionException {
		System.out.print("Input: ");
		System.out.println(email);
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		System.out.println(documents.size());
		for (QueryDocumentSnapshot document : documents) {
			  System.out.println(document.getId() + " => " + document.toObject(User.class) + " \ncurrent email: " + document.get("email"));
			  if (document.get("email").equals(email)) {
				  System.out.print("yeaahhh!!" + document.get("name"));
				  return document.toObject(User.class);
			  }
		}
		return null;
	}
	@GetMapping("/createUser")
	public String createUser(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) throws IOException, InterruptedException, ExecutionException {
		User user = new User(email, name, password);
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection("users").add(user);
		return collectionApiFuture.toString();
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam(value = "email") String email) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		
		String id = null;

		// get userID with email
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("users").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		System.out.println(documents.size());
		for (QueryDocumentSnapshot document : documents) {
			  System.out.println(document.getId() + " => " + document.toObject(User.class));
			  if (document.get("email").equals(email)) {
				  id = document.getId();
			  }
		}
		
		ApiFuture<WriteResult> writeResult = dbFirestore.collection("users").document(id).delete();
		System.out.println("Update time : " + writeResult.get().getUpdateTime());
		return "Deleted!!";
	}
}
