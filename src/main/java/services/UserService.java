package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import Response.Payload;
import Response.UpdatePayload;
import model.Habits;
import model.Hobbies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import repository.HabitsRepo;
import repository.HobbiesRepo;
import repository.UserRepo;
import model.User;

@Service
public class UserService {

	@Autowired(required = false)
	private UserRepo userRepo;

	@Autowired(required = false)
	private HobbiesRepo hobbiesRepo;

	@Autowired(required = false)
	private HabitsRepo habitsRepo;

	@Autowired
	private JavaMailSender mailSender;

	private final List<Integer> weights = Arrays.asList(5,3,3,6,1,2,4);



	public User updateUser(UpdatePayload user, Integer id){
		Optional<User> optional = userRepo.findById(id);
		User self= optional.get();
		if(user.getName()!=null){
			self.setName(user.getName());
		}
		if(user.getBudgetMin()!=null){
			self.setBudgetMin(user.getBudgetMin());
			self.setBudgetMax(user.getBudgetMax());
		}
		if(user.getUsername()!=null){
			self.setUsername(user.getUsername());
		}
		if(user.getLocation()!=null){
			self.setLocation(user.getLocation());
		}
		if(user.getHabits()!=null){
			Habits habits = habitsRepo.findById(self.getHabits().getId()).get();
			habitsRepo.delete(habits);
			habits = habitsRepo.save(user.getHabits());
			self.setHabits(habits);
		}
		if(user.getHobbies()!=null){
			Hobbies hobby = hobbiesRepo.findById(self.getHobbies().getId()).get();
			hobbiesRepo.delete(hobby);
			hobby = hobbiesRepo.save(user.getHobbies());
			self.setHobbies(hobby);
		}
		StringBuilder hobby= new StringBuilder("");

		hobby.append(self.getHobbies().getMovies()?"1":"0");
		hobby.append(self.getHobbies().getGaming()?"1":"0");
		hobby.append(self.getHobbies().getSports()?"1":"0");
		hobby.append(self.getHobbies().getReading()?"1":"0");
		hobby.append(self.getHobbies().getWriting()?"1":"0");
		hobby.append(self.getHobbies().getPhotography()?"1":"0");
		hobby.append(self.getHobbies().getHighVolumeMusic()?"1":"0");
		self.setHobbyList(hobby.toString());

		StringBuilder habit = new StringBuilder("");


		habit.append(self.getHabits().getSuperClean()?"1":"0");
		habit.append(self.getHabits().getNightOwl()?"1":"0");
		habit.append(self.getHabits().getEarlyBird()?"1":"0");
		habit.append(self.getHabits().getSmoking()?"1":"0");
		habit.append(self.getHabits().getDrinking()?"1":"0");
		habit.append(self.getHabits().getVegetarian()?"1":"0");
		habit.append(self.getHabits().getStudent()?"1":"0");

		self.setHabitList(habit.toString());
		userRepo.save(self);

		return self;
	}

	public User submitDetails(Payload payload,Integer id){
		Hobbies hobbies = hobbiesRepo.save(payload.getHobbies());
		Habits habits = habitsRepo.save(payload.getHabits());

		Optional<User> optional = userRepo.findById(id);
		User user= optional.get();
		user.setHobbies(hobbies);

		user.setHabits(habits);
		user.setBudgetMin(payload.getBudgetMin());
		user.setBudgetMax(payload.getBudgetMax());
		user.setLocation(payload.getLocation());
		StringBuilder hobby= new StringBuilder("");

		hobby.append(hobbies.getMovies()?"1":"0");
		hobby.append(hobbies.getGaming()?"1":"0");
		hobby.append(hobbies.getSports()?"1":"0");
		hobby.append(hobbies.getReading()?"1":"0");
		hobby.append(hobbies.getWriting()?"1":"0");
		hobby.append(hobbies.getPhotography()?"1":"0");
		hobby.append(hobbies.getHighVolumeMusic()?"1":"0");
		user.setHobbyList(hobby.toString());

		StringBuilder habit = new StringBuilder("");


		habit.append(habits.getSuperClean()?"1":"0");
		habit.append(habits.getNightOwl()?"1":"0");
		habit.append(habits.getEarlyBird()?"1":"0");
		habit.append(habits.getSmoking()?"1":"0");
		habit.append(habits.getDrinking()?"1":"0");
		habit.append(habits.getVegetarian()?"1":"0");
		habit.append(habits.getStudent()?"1":"0");

		user.setHabitList(habit.toString());
		userRepo.save(user);

		return user;
	}

	public User submitMetaDataOfUsers(User user) {

		userRepo.save(user);
		return user;
	}


	public User displayUserMetaData(Integer id) {
		Optional<User> optional = userRepo.findById(id);
		User user= optional.get();
		return user;

	}

	public boolean cosineHobbyResult(String[] self,String[] other){
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < self.length; i++) {
			dotProduct += self[i].equals(other[i])?1:0;
			if(self[i].equals(other[i]) && self[i].equals("0")){
				normA+=1.0;
				normB+= 1.0;
			}
			else {
				normA += Math.pow(Double.parseDouble(self[i]), 2);
				normB += Math.pow(Double.parseDouble(other[i]), 2);
			}
		}
		System.out.println("hobby "+dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));
		return  dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)) >= 0.3;
	}

	public boolean cosineHabitResult(String[] self,String[] other){
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < self.length; i++) {
			dotProduct += self[i].equals(other[i])? weights.get(i)*weights.get(i) :0;
			if(self[i].equals(other[i]) && self[i].equals("0")){
				normA+=weights.get(i)*weights.get(i);
				normB+= weights.get(i)*weights.get(i);
			}
			else {
				normA += Math.pow(Double.parseDouble(self[i])*weights.get(i), 2);
				normB += Math.pow(Double.parseDouble(other[i])*weights.get(i), 2);
			}
		}

		System.out.println("habit "+dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));
		return  dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)) >= 0.5;
	}

	public List<User> getEachUsers(Integer bMin, Integer bMax, String loc ){
		List <User> userList = (List<User>) userRepo.findAll();
		List<User> result = new ArrayList<User>();
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getBudgetMin()!=null) {
				if (userList.get(i).getBudgetMin().equals(bMin) && userList.get(i).getBudgetMax().equals(bMax) && userList.get(i).getLocation().equals(loc)) {
					result.add(userList.get(i));
				}
			}
		}
		return result;
	}

	public List<User> getAllUsers(Integer id,Integer budgetMin,Integer budgetMax,String location){


		Optional<User> optional = userRepo.findById(id);
		User user= optional.get();
		Integer bMin=0,bMax=0;
		String loc="";
		if(budgetMin == null){
			bMin = user.getBudgetMin();
			bMax = user.getBudgetMax();
		}
		else{
			bMin=budgetMin;
			bMax = budgetMax;
		}

		if(location==null){
			loc=user.getLocation();
		}
		else{
			loc=location;
		}
		List <User> userList = new ArrayList<User>();



		String hobby = user.getHobbyList();
		String habit = user.getHabitList();

		String[] hobbyArr = hobby.split("");
		String[] habitArr = habit.split("");

		List <User> allUsers = getEachUsers(bMin,bMax,loc);


		for(int i=0;i<allUsers.size();i++){
			if(user.equals(allUsers.get(i))){
				continue;
			}

			if(cosineHabitResult(habitArr,allUsers.get(i).getHabitList().split(""))&&
					cosineHobbyResult(hobbyArr,allUsers.get(i).getHobbyList().split(""))
			){
				userList.add(allUsers.get(i));
			}
		}
		return userList;
	}

	public User returnUser(String email) {
		return userRepo.findByEmail(email);
	}

	public String sendEmail(String to_email, String to_name, String self) {
		String from = "flatbuddyservice@gmail.com";
		String to = to_email;

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject("FlatBuddy: New message from "+ self);
		String mssg = "Hello, " + to_name + "\n" +
				"\n" +
				"You got a new message from : " + self + "\n" +
				"\n" +
				"Hope you found your flatbuddy\n" +
				"\n" +
				"Best wishes,\n" +
				"FlatBuddy team";
		message.setText(mssg);

		mailSender.send(message);
		return "Email sent";
	}
}
