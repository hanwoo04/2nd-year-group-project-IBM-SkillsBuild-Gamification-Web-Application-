package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.repo.UserStreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class   StreakService {

    @Autowired
    private UserStreakRepository streakRepository;

    public void updateStreak(Long userId) {
        UserStreak userStreak = streakRepository.findById(userId).orElse(new UserStreak());
        LocalDate today = LocalDate.now();

        if (userStreak.getLastLoginDate() == null || !userStreak.getLastLoginDate().plusDays(1).equals(today)) {
            userStreak.setStreakCount(0);
        }

        userStreak.setStreakCount(userStreak.getStreakCount() + 1);
        userStreak.setLastLoginDate(today);

        streakRepository.save(userStreak);
    }

    public UserStreak getUserStreak(Long userId) {
        return streakRepository.findById(userId).orElse(new UserStreak());
    }
}
