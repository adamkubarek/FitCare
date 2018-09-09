package pl.javastyle.fitcare.startup;

import org.springframework.stereotype.Component;
import pl.javastyle.fitcare.domain.User;
import pl.javastyle.fitcare.domain.enums.ActivityRate;
import pl.javastyle.fitcare.domain.enums.DietGoal;
import pl.javastyle.fitcare.domain.enums.Gender;
import pl.javastyle.fitcare.repositories.interfaces.UserDAO;

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
