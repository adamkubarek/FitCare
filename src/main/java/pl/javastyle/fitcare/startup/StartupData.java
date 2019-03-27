package pl.javastyle.fitcare.startup;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.user.User;
import pl.javastyle.fitcare.user.UserDAO;
import pl.javastyle.fitcare.user.enums.ActivityRate;
import pl.javastyle.fitcare.user.enums.DietGoal;
import pl.javastyle.fitcare.user.enums.Gender;

@Component
public class StartupData {
    private final UserDAO userDAO;

    public StartupData(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    void loadSampleUser() {
        User user = new User();
        user.setName("Alvaro");
        user.setAge(25);
        user.setHeight(185);
        user.setWeight(84.0);
        user.setActivityRate(ActivityRate.HIGH);
        user.setGender(Gender.MALE);
        user.setDietGoal(DietGoal.LOOSE_WEIGHT);

        userDAO.save(user);
    }
}
