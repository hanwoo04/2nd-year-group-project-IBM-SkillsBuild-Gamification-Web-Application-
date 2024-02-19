package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.example.ibmskillsbuildapp.repo.UserStreakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StreakServiceTest {

    @InjectMocks
    private StreakService streakService;

    @Mock
    private UserStreakRepository streakRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateStreak_FirstLogin() {
        Long userId = 1L;
        UserStreak userStreak = new UserStreak(); // Create a new UserStreak
        when(streakRepository.findById(userId)).thenReturn(Optional.of(userStreak));

        streakService.updateStreak(userId);

        assertEquals(1, userStreak.getStreakCount()); // Streak should be 1
        assertEquals(LocalDate.now(), userStreak.getLastLoginDate()); // Last login date should be today
    }

    // Add more test cases as needed
}
