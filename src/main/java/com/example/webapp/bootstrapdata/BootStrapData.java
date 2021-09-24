package com.example.webapp.bootstrapdata;


import com.example.webapp.entities.Call;
import com.example.webapp.entities.User;
import com.example.webapp.repository.CallRepository;
import com.example.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


@Component
public class BootStrapData implements CommandLineRunner {
    Random r = new Random();

    String generateData(){
        return "" + (r.nextInt(29) + 1) + "-" + (r.nextInt(12) + 1) + "-" + (r.nextInt(22) + 2000) + " " + r.nextInt(24) + ":" + r.nextInt(60) + ":" + r.nextInt(60);
    }

    private final CallRepository callRepository;
    private final UserRepository userRepository;

    @Autowired
    public BootStrapData(UserRepository userRepository, CallRepository callRepository) {
        this.userRepository = userRepository;
        this.callRepository = callRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        ArrayList<User> listOfUsers = new ArrayList<>();
        ArrayList<Call> listOfCalls = new ArrayList<>();
        ArrayList<String> listOfNumbers = new ArrayList<>();
        int numberOfUsers = 10;
        int numberOfCalls = 50;
        int maxTransferUsed = 4000;
        int maxTalkTime = 1000;
        String[] tableNames = {"Marek", "Jacek", "Paweł", "Karol", "Anna", "Filip", "Antoni", "Szymon", "Franciszek", "Mikołaj", "Kacper", "Wiktor", "Leon", "Zuzanna", "Julia", "Zofia", "Hanna", "Maja", "Lena", "Alicja", "Oliwia",};
        String[] tableSurnames = {"Ogórek", "Cukinia", "Ziemniak", "Sałata", "Kalafior", "Woźniak", "Janko", "Mazur", "Kwiatek", "Wojciech", "Krawczyk", "Karczmarek"};
        User firstUser = new User("Marek", "Jarek", "123456789");
        User secondUser = new User("Wiesław", "Kowalski", "987654321");
        firstUser.setTransferUsed(r.nextInt(maxTransferUsed));
        secondUser.setTransferUsed(r.nextInt(maxTransferUsed));
        String firstCallDate = generateData();
        Call call1 = new Call("123456789", "987654321", 120, firstCallDate);
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        callRepository.save(call1);
        listOfUsers.add(firstUser);
        listOfUsers.add(secondUser);
        for (int i = 0; i < numberOfUsers; i++) {
            StringBuilder genNumber = new StringBuilder();
            for (int j = 0; j < 9; j++) {
                genNumber.append(r.nextInt(10));
            }
            if (listOfNumbers.contains(genNumber.toString())) {
                i--;
                continue;
            }
            User newUser = new User(tableNames[r.nextInt(tableNames.length)], tableSurnames[r.nextInt(tableSurnames.length)], genNumber.toString());
            newUser.setTransferUsed(r.nextInt(maxTransferUsed));
            userRepository.save(newUser);
            listOfUsers.add(newUser);
            listOfNumbers.add(genNumber.toString());
        }
        for (int i = 0; i < numberOfCalls; i++) {
            String callDate = generateData();
            int talkTime = r.nextInt(maxTalkTime);
            String firstNumber = listOfUsers.get(r.nextInt(listOfUsers.size())).getMainNumber();
            String secondNumber = listOfUsers.get(r.nextInt(listOfUsers.size())).getMainNumber();
            while (Objects.equals(firstNumber, secondNumber)) {
                secondNumber = listOfUsers.get(r.nextInt(listOfUsers.size())).getMainNumber();
            }

            Call newCall = new Call(firstNumber, secondNumber, talkTime, callDate);
            callRepository.save(newCall);
            listOfCalls.add(newCall);

            for (User user : listOfUsers) {
                for (Call call : listOfCalls) {
                    if (Objects.equals(user.getMainNumber(), call.getMainNumber()) || Objects.equals(user.getMainNumber(), call.getTargetNumber())) {
                        user.addCall(call);
                    }
                }
            }
        }

        System.out.println("BootStrapData started!");
        System.out.println("calls " + callRepository.count());
        System.out.println("users " + userRepository.count());
    }
}
