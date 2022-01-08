package com.app.sctea;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class ScteaApplication {
	@RequestMapping("/error")
	public ModelAndView home()
	{
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}
	
	public static void main(String[] args) throws IOException {
//		ClassLoader classLoader = ScteaApplication.class.getClassLoader();
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());
//		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
//		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
//		FirebaseOptions options = new FirebaseOptions.Builder()
//		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//		.setDatabaseUrl("https://sctea-9f2b4-default-rtdb.firebaseio.com")
//		.build();
//		FirebaseApp.initializeApp(options);

		FileInputStream serviceAccount =
		  new FileInputStream("serviceAccount.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .setDatabaseUrl("https://sctea-9f2b4-default-rtdb.firebaseio.com")
		  .build();
		
		FirebaseApp.initializeApp(options);

		SpringApplication.run(ScteaApplication.class, args);
	}

}
