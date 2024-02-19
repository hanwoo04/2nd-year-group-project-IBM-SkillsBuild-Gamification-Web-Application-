package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.repo.UserStreakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * Service class for managing user streaks.
 */
@Service
public class StreakService {

    @Autowired
    private UserStreakRepository streakRepository;

    /**
     * Updates the streak count for the user identified by the given userId.
     *
     * @param userId The ID of the user whose streak is to be updated.
     */
    public void updateStreak(Long userId) {
        UserStreak userStreak = streakRepository.findById(userId).orElse(new UserStreak());
        LocalDate today = LocalDate.now();

        // Check if last login date is null or not equal to yesterday's date
        if (userStreak.getLastLoginDate() == null || !userStreak.getLastLoginDate().plusDays(1).equals(today)) {
            userStreak.setStreakCount(0); // Reset streak count if not logged in yesterday
        }

        userStreak.setStreakCount(userStreak.getStreakCount() + 1); // Increment streak count
        userStreak.setLastLoginDate(today); // Update last login date

        streakRepository.save(userStreak); // Save the updated user streak
    }

    /**
     * Retrieves the streak information for the user identified by the given userId.
     *
     * @param userId The ID of the user.
     * @return The UserStreak object representing the user's streak information.
     */
    public UserStreak getUserStreak(Long userId) {
        return streakRepository.findById(userId).orElse(new UserStreak());
    }
}
